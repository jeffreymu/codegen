package com.cjhxfund.autocode.ast.parser;

import antlr4.CPP14BaseListener;
import antlr4.CPP14Lexer;
import antlr4.CPP14Parser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

class Node implements Serializable {
    public int id;
    public String type;

    public Node(int id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id =" + id +
                ", type ='" + type + '\'' +
                '}';
    }

    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("type", type);
        return object;
    }
}

class Edge implements Serializable {
    public Node node;
    public int parent;

    public Edge(Node node, int parent) {
        this.node = node;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "node=" + node.toString() +
                ", parent=" + parent +
                '}';
    }

    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        object.put("node", node.toJsonObject());
        object.put("parent", parent);
        return object;
    }
}

public class CPP14ToTreeNode extends CPP14BaseListener {

    private ParseTreeProperty<Edge> edges = new ParseTreeProperty<>();
    private ArrayList<ParseTree> nodes = new ArrayList<>();
    private Stack<Node> sta = new Stack<>();
    private static int count = 0;
    private boolean flag;
    private String resultPath;

    public CPP14ToTreeNode() {
        flag = false;
        resultPath = "e:\\test/";
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        Node node = new Node(count, CPP14Parser.ruleNames[ctx.getRuleIndex()]);
        sta.push(node);
        count++;
        nodes.add(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        Node node = sta.peek();
        sta.pop();
        if (!sta.isEmpty()) {
            Node tmt = sta.peek();
            Edge edge = new Edge(node, tmt.id);
            edges.put(ctx, edge);
        } else {
            Edge edge = new Edge(node, -1);
            edges.put(ctx, edge);
        }
    }

    public ArrayList<String> ToTreeForAllProject(String projectPath, ArrayList<String> errorList) throws Exception {
        File dir = new File(projectPath);
        File[] files = dir.listFiles();
        List<File> fileList = Arrays.asList(files);
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                int i = Integer.parseInt(f1.getName().substring(f1.getName().indexOf("__") + 2, f1.getName().indexOf(".cpp")));
                int k = Integer.parseInt(f2.getName().substring(f2.getName().indexOf("__") + 2, f2.getName().indexOf(".cpp")));
                if (i < k) {
                    return -1;
                } else if (i > k) {
                    return 1;
                } else return 0;
            }
        });

        for (File file : fileList
                ) {
            ANTLRFileStream input = new ANTLRFileStream(file.getAbsolutePath());
            CPP14Lexer lexer = new CPP14Lexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CPP14Parser parser = new CPP14Parser(tokens);
            ParseTree tree = parser.translationunit();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(this, tree);
            JSONArray array = new JSONArray();

            if (!flag) {
                for (ParseTree ctx : nodes
                        ) {
                    array.put(edges.get(ctx).toJsonObject());
                }
                edges = new ParseTreeProperty<>();
                nodes = new ArrayList<>();
                sta = new Stack<>();
                count = 0;
                flag = false;

                PrintWriter out = new PrintWriter(new FileOutputStream(resultPath + "json/" + file.getName() + "__tree__" + ".json"));
                out.println(array.toString());
                out.close();
            } else {
                errorList.add(file.getName());
                flag = false;
            }
        }
        return errorList;
    }

    public boolean ToTreeForSingleFile(File file) throws Exception {
        ANTLRFileStream input = new ANTLRFileStream(file.getAbsolutePath());
        CPP14Lexer lexer = new CPP14Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPP14Parser parser = new CPP14Parser(tokens);
        ParseTree tree = parser.translationunit();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        JSONArray array = new JSONArray();
        if (!flag) {
            for (ParseTree ctx : nodes
                    ) {
                array.put(edges.get(ctx).toJsonObject());
            }
            PrintWriter out = new PrintWriter(new FileOutputStream(resultPath + file.getName() + "__tree__" + ".json"));
            out.println(array.toString());
            out.close();
            flag = false;
            return true;
        }
        return false;
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        flag = true;
    }
}
