package com.grule.test.manager;

import com.grule.core.RuleEngine;
import com.grule.core.action.GroovyAction;
import com.grule.core.condition.Condition;
import com.grule.core.condition.GroovyCondition;
import com.grule.core.context.Context;
import com.grule.core.context.ConcurrentContext;
import org.junit.Test;

/**
 * Created on 2018/03/05
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
                "        System.out.println(\"滴！市民卡，请上车～～～～\");\n" +
                "        System.out.println(name + \" is ok enough to \" + will);\n" +
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
                "        System.out.println(name + \" is too young to \" + will);\n" +
                "    }\n" +
                "}\n";

        Condition condition = new GroovyCondition(conditionScript);

        GroovyAction trueAction = new GroovyAction(trueActionScript);
        GroovyAction falseAction = new GroovyAction(falseActionScript);

        condition.registerTrueUnit(trueAction);
        condition.registerFalseUnit(falseAction);

        Context context = new ConcurrentContext();
        context.put("age", 16);
        context.put("name", "leo");
        context.put("will", "小屁孩推车");
        RuleEngine.getInstance().setEntry(condition).start(context);
        context.put("age", 20);
        context.put("name", "lee");
        context.put("will", "老司机开车");
        RuleEngine.getInstance().setEntry(condition).start(context);

    }
}
