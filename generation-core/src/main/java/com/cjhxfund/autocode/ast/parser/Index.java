package com.cjhxfund.autocode.ast.parser;

import antlr4.CPP14Lexer;
import antlr4.CPP14Parser;
import com.cjhxfund.autocode.ast.antlr4.CPP14ValuesListener;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.util.*;

public class Index {

    public static void preprocessingData(File dir) throws Exception {
        long start = System.currentTimeMillis();
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
        ArrayList<String> errorList = new ArrayList<>();
        for (File file : fileList
                ) {
            if (file.isFile() && file.getName().endsWith(".cpp")) {
                ANTLRFileStream input = new ANTLRFileStream(file.getAbsolutePath());
                CPP14Lexer lexer = new CPP14Lexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                CPP14Parser parser = new CPP14Parser(tokens);
                ParseTree tree = parser.translationunit();

                // Listener
                ParseTreeWalker walker = new ParseTreeWalker();
                CPP14ValuesListener lis = new CPP14ValuesListener(tokens, file.getName());

                if (((CPP14Parser.TranslationunitContext) tree).EOF() != null) {
                    walker.walk(lis, tree);
                    if (lis.flag) {
                        errorList.add(file.getName());
                    }
                } else {
                    errorList.add(file.getName());
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Preprocessing  [ " + files.length + " ] files takes a total of [ " + (end - start) / 1000 + " ] S.");

        System.out.println("the number of error file = " + errorList.size());
        System.out.println(errorList.toString());
    }

    public static void main(String[] args) throws Exception {
        CPP14ToTreeNode tree = new CPP14ToTreeNode();

        if (tree.ToTreeForSingleFile(new File("e:/mrtti_trade.cpp"))) {
            System.out.println("Success!!");
        }

//        ArrayList<String> error = new ArrayList<>();
//        error = tree.ToTreeForAllProject("e:\\test", error);
//        if (!error.isEmpty()) {
//            System.out.println(error.toString());
//            FileWriter writer = new FileWriter("D:\\test\\json\\error_total.txt");
//            writer.writeLocal("The number of error is = " + error.size() + "\n");
//            writer.writeLocal(error.toString());
//        }

        //preprocessingData(new File("e:\\test"));
    }

}
