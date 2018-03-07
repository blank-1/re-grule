package com.grule.test.manager;

import com.grule.core.RuleEngine;
import com.grule.core.action.AbstractAction;
import com.grule.core.action.Action;
import com.grule.core.action.GroovyAction;
import com.grule.core.condition.AbstractCondition;
import com.grule.core.condition.Condition;
import com.grule.core.condition.GroovyCondition;
import com.grule.core.context.SimpleContext;
import com.grule.core.exception.AutoConfigException;
import com.grule.test.action.ActionTest;
import com.grule.test.condition.ConditionTest;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by  on 2017/8/11.
 */
public class RuleEngineActionScript {

    @Test
    public void testRun() {
        Condition condition = mock(AbstractCondition.class);
        Action action = mock(AbstractAction.class);

        condition.registerTrueUnit(action);
        when(condition.getResult()).thenReturn(true);

        RuleEngine.getInstance().setEntry(condition).start(new SimpleContext());
    }

    @Test
    public void testRunGroovy() throws IOException {
        String conditionScript = "import Context\n" +
                "class TestCondition {\n" +
                "    boolean run(Context contex) {\n" +
                "\t\tcontex.put(\"ConditionScript\", \"hello\")\n" +
                "\t\tprintln(\" conditionScript\")\n" +
                "\t\treturn true\n" +
                "\t}\n" +
                "}";
        String actionScript = "import Context\n" +
                "import static org.junit.Assert.*;\n" +
                "class TestAction {\n" +
                "    void run(Context context) {\n" +
                "\t\tprintln(\" actionScript\")\n" +
                "\t\tassertEquals(context.get(\"ConditionScript\"), \"hello\")\n" +
                "\t}\n" +
                "}";
        Condition condition = new GroovyCondition(conditionScript);
        GroovyAction action = new GroovyAction(actionScript);

        condition.registerTrueUnit(action);

        RuleEngine.getInstance().setEntry(condition).start(new SimpleContext());
    }

    @Test
    public void testJSONConfigRun() throws AutoConfigException {
        String json = "{\"type\":\"condition\",\"class\":\"" + ConditionTest.class.getName() + "\",\"trueUnit\":{\"type\":\"action\",\"class\":\"" + ActionTest.class.getName() + "\"}}";
        RuleEngine.getInstance().config(json).start(new SimpleContext());
    }

}
