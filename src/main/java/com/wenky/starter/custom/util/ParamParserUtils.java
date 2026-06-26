package com.wenky.starter.custom.util;

import com.wenky.starter.custom.util.reflect.People;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author 克林
 * @date 2026/6/26 14:03
 */
public class ParamParserUtils {

    public static final ThreadLocal<EvaluationContext> EVALUATION_CONTEXT_THREAD_LOCAL =
            ThreadLocal.withInitial(StandardEvaluationContext::new);

    public static final ThreadLocal<ExpressionParser> EVALUATION_PARSER_THREAD_LOCAL =
            ThreadLocal.withInitial(SpelExpressionParser::new);

    public static void main(String[] args) {
        objectValueParserExample();
    }

    public static void objectValueParserExample() {
        People people = new People();
        people.setName("wenky");
        people.setHobbies(Arrays.asList("swimming", "runnning"));
        people.setInfoMap(new HashMap<String, Object>() {{put("nickName", "wenky11");}});

        EvaluationContext evaluationContext = EVALUATION_CONTEXT_THREAD_LOCAL.get();
        evaluationContext.setVariable("people", people);

        ExpressionParser expressionParser = EVALUATION_PARSER_THREAD_LOCAL.get();
        Object value1 = expressionParser.parseExpression("#" + "people.name").getValue(evaluationContext);
        System.out.println(value1);

        Object value2 = expressionParser.parseExpression("#" + "people.hobbies[0]").getValue(evaluationContext);
        System.out.println(value2);

        Object value3 = expressionParser.parseExpression("#" + "people.infoMap['nickName']").getValue(evaluationContext);
        System.out.println(value3);

    }

}
