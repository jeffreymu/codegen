package com.cjhxfund.autocode.ast.parser;

import antlr4.CPP14BaseListener;
import antlr4.CPP14Lexer;
import antlr4.CPP14Parser;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class CppParser {

    public static void parseCppFile(String file) throws IOException {
        String p1 = FileUtils.readFileToString(new File(file), Charset.forName("GB2312"));
        // Get our lexer
        CPP14Lexer lexer = new CPP14Lexer(new ANTLRInputStream(p1));
        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Pass the tokens to the parser
        CPP14Parser parser = new CPP14Parser(tokens);
        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        // Specify our entry point
        ParseTree entryPoint = null;//TODO: what is the entry point?
        walker.walk(new CPP14BaseListener(), entryPoint);
        log.info("--------");
    }

    public static void main(String[] args) throws IOException {
        CppParser.parseCppFile("e:/mrtti_trade.cpp");
    }
}
