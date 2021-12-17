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

        parser.addParseListener(l);
        ParseTree tree = parser.expression();
        System.out.println(tree.toStringTree(parser));
    }

    private static class MyCriteriaListener implements CriteriaListener {

        @Override
        public void visitTerminal(TerminalNode node) {

        }

        @Override
        public void visitErrorNode(ErrorNode node) {

        }

        @Override
        public void enterEveryRule(ParserRuleContext ctx) {

        }

        @Override
        public void exitEveryRule(ParserRuleContext ctx) {

        }

        @Override
        public void exitTerm(CriteriaParser.TermContext ctx) {

        }

        @Override
        public void enterTerm(CriteriaParser.TermContext ctx) {

        }

        @Override
        public void exitTermlist(CriteriaParser.TermlistContext ctx) {

        }

        @Override
        public void enterTermlist(CriteriaParser.TermlistContext ctx) {

        }

        @Override
        public void exitComparison_operator(CriteriaParser.Comparison_operatorContext ctx) {

        }

        @Override
        public void enterComparison_operator(CriteriaParser.Comparison_operatorContext ctx) {

        }

        @Override
        public void exitCondition(CriteriaParser.ConditionContext ctx) {

        }

        @Override
        public void enterCondition(CriteriaParser.ConditionContext ctx) {

        }

        @Override
        public void exitExpression(CriteriaParser.ExpressionContext ctx) {

        }

        @Override
        public void enterExpression(CriteriaParser.ExpressionContext ctx) {

        }
    }
}
