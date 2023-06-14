package com.logicmint.criteriaparser;

import com.vmware.antlr4c3.CodeCompletionCore;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestC3Autocompletion {

    public static class CountingErrorListener extends BaseErrorListener {

        public int errorCount = 0;

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
            errorCount++;

        }
    }
    @Test
    public void testAutocomplete() {
        System.out.println();
        System.out.println("simpleExpressionTest");

        String expression = "myCol IN ('one', 'two', 'three') OR secondCol LIKE '%wibble%'";
        CriteriaLexer lexer = new CriteriaLexer(CharStreams.fromString(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CriteriaParser parser = new CriteriaParser(tokens);

        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        CountingErrorListener errorListener = new CountingErrorListener();
        parser.addErrorListener(errorListener);

        // Specify our entry point
        CriteriaParser.ExpressionContext tree = parser.expression();

        assertEquals(0, errorListener.errorCount);

        CodeCompletionCore core = new CodeCompletionCore(parser, null, null);

        // 1) At the input start.

        CodeCompletionCore.CandidatesCollection candidates = core.collectCandidates(0, null);

        assertEquals(2, candidates.tokens.size());
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.COLUMN_ID_TERM));
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.L_PARENTHESIS)); //left bracket
//
//        // 2) On the first whitespace. In real implementations you would do some additional checks where in the whitespace
//        //    the caret is, as the outcome is different depending on that position.
        candidates = core.collectCandidates(1, null);
        assertEquals(10, candidates.tokens.size());
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.NOT_EQUAL_TO));
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.GREATER_THAN));
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.LESS_THAN));
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.EQUAL_TO));
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.GRTR_THAN_OR_EQUAL));
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.LESS_THAN_OR_EQUAL));
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.IS));
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.LIKE));
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.IN));
        assertTrue(candidates.tokens.containsKey(CriteriaLexer.NOT));

        core.setPreferredRules(Set.of(CriteriaParser.RULE_term, CriteriaParser.RULE_expression));
        candidates = core.collectCandidates(5, null);
        assertEquals(10, candidates.tokens.size());
//        assertTrue(candidates.tokens.containsKey(ExprLexer.ID));
//
//        // 3) On the variable name ('c').
//        candidates = core.collectCandidates(2, null);
//        assertEquals(1, candidates.tokens.size());
//        assertTrue(candidates.tokens.containsKey(ExprLexer.ID));
//
//        // 4) On the equal sign (ignoring whitespace positions from now on).
//        candidates = core.collectCandidates(4, null);
//        assertEquals(1, candidates.tokens.size());
//        assertTrue(candidates.tokens.containsKey(ExprLexer.EQUAL));
//
//        // 5) On the variable reference 'a'. But since we have not configure the c3 engine to return us var refs
//        //    (or function refs for that matter) we only get an ID here.
//        candidates = core.collectCandidates(6, null);
//        assertEquals(1, candidates.tokens.size());
//        assertTrue(candidates.tokens.containsKey(ExprLexer.ID));
//
//        // 6) On the '+' operator. Usually you would not show operators as candidates, but we have not set up the c3 engine
//        //    yet to not return them.
//        candidates = core.collectCandidates(8, null);
//        assertEquals(5, candidates.tokens.size());
//        assertTrue(candidates.tokens.containsKey(ExprLexer.PLUS));
//        assertTrue(candidates.tokens.containsKey(ExprLexer.MINUS));
//        assertTrue(candidates.tokens.containsKey(ExprLexer.MULTIPLY));
//        assertTrue(candidates.tokens.containsKey(ExprLexer.DIVIDE));
//        assertTrue(candidates.tokens.containsKey(ExprLexer.OPEN_PAR));
    }
}
