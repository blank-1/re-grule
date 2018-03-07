package com.grule.test.manager;

import com.grule.core.RuleEngine;
import com.grule.core.action.GroovyAction;
import com.grule.core.condition.Condition;
import com.grule.core.condition.GroovyCondition;
import com.grule.core.context.Context;
import com.grule.core.context.SimpleContext;
import org.junit.Test;

/**
 * Created by  on 2017/8/14.
 */
public class RuleEngineTest {


    @Test
    public void testAge() {

        String conditionScript = "package com.grule.core.script.groovy;\n" +
                "import com.grule.core.context.Context;\n" +
                "\n" +
                "class AgeConditionSctipt implements ConditionScript {\n" +
                "    @Override\n" +
                "    boolean run(Context context) {\n" +
                "        int age = (Integer) context.get(\"age\");\n" +
                "        if (age < 18) {\n" +
                "            return false;\n" +
                "        }\n" +
                "        return true;\n" +
                "    }\n" +
                "}";

        String trueActionScript = "package com.grule.core.script.groovy;\n" +
                "import com.grule.core.context.Context;\n" +
                "\n" +
                "class After18ActionScript implements ActionScript {\n" +
                "    @Override\n" +
                "    void run(Context context) {\n" +
                "        String name = (String) context.get(\"name\");\n" +
                "        String will = (String) context.get(\"will\");\n" +
                "        System.out.println(\"老司机开车进行中\");\n" +
                "        System.out.println(name + \" is old enough to \" + will);\n" +
                "    }\n" +
                "}\n";

        String falseActionScript = "package com.grule.core.script.groovy;\n" +
                "import com.grule.core.context.Context;\n" +
                "\n" +
                "class Before18ActionScript implements ActionScript {\n" +
                "    @Override\n" +
                "    void run(Context context) {\n" +
                "        String name = (String) context.get(\"name\");\n" +
                "        String will = (String) context.get(\"will\");\n" +
                "        System.out.println(\"滴！学生卡，请下车～～～～\");\n" +
                "        System.out.println(name + \" is to young to \" + will);\n" +
                "    }\n" +
                "}\n";

        Condition condition = new GroovyCondition(conditionScript);

        GroovyAction trueAction = new GroovyAction(trueActionScript);
        GroovyAction falseAction = new GroovyAction(falseActionScript);

        condition.registerTrueUnit(trueAction);
        condition.registerFalseUnit(falseAction);

        Context context = new SimpleContext();
        context.put("age", 16);
        context.put("name", "leo");
        context.put("will", "在1024");
        RuleEngine.getInstance().setEntry(condition).start(context);

    }
}
