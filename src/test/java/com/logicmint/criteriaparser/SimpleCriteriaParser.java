package com.logicmint.criteriaparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.io.StringReader;

public class SimpleCriteriaParser {

    public static void main(String[] args) throws IOException {

        CharStream charStream = CharStreams.fromReader(new StringReader("Nick LIKE 'Nick'"));
        CriteriaLexer lexer = new CriteriaLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CriteriaParser parser = new CriteriaParser(tokens);
        CriteriaListener l = new MyCriteriaListener();

        ParseTree tree = parser.expression();
        ParseTreeWalker treeWalker = new ParseTreeWalker();
        MyCriteriaListener listener = new MyCriteriaListener();
        treeWalker.walk(listener, tree);

        System.out.println(tree.toStringTree(parser));
        System.out.println(listener.stringBuilder.toString());
    }

    private static class MyCriteriaListener extends CriteriaBaseListener {

        StringBuilder stringBuilder = new StringBuilder();

        @Override
        public void enterColumnId(CriteriaParser.ColumnIdContext ctx) {
            stringBuilder.append("/");
        }

        @Override
        public void visitTerminal(TerminalNode node) {
            stringBuilder.append(node.getSymbol().getText()).append(" ");
        }
    }
}
