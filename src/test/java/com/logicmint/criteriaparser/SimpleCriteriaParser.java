package com.logicmint.criteriaparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.io.StringReader;

public class SimpleCriteriaParser {

    public static void main(String[] args) throws IOException {

        CharStream charStream = CharStreams.fromReader(new StringReader("'Nick' LIKE 'Nick'"));
        CriteriaLexer lexer = new CriteriaLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CriteriaParser parser = new CriteriaParser(tokens);


        CriteriaListener l = new CriteriaListener() {


            @Override
            public void enterExpression(CriteriaParser.ExpressionContext ctx) {

            }

            @Override
            public void exitExpression(CriteriaParser.ExpressionContext ctx) {

            }

            @Override
            public void enterCondition(CriteriaParser.ConditionContext ctx) {

            }

            @Override
            public void exitCondition(CriteriaParser.ConditionContext ctx) {

            }

            @Override
            public void enterOperand(CriteriaParser.OperandContext ctx) {

            }

            @Override
            public void exitOperand(CriteriaParser.OperandContext ctx) {

            }

            @Override
            public void visitTerminal(TerminalNode node) {
                System.out.println(node.getSymbol().getText());
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
        };

        parser.addParseListener(l);
        ParseTree tree = parser.expression();
        System.out.println(tree.toStringTree(parser));
    }
}
