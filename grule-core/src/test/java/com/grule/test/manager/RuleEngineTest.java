package com.grule.test.manager;

import com.grule.core.manager.RuleEngine;
import com.grule.core.action.GroovyAction;
import com.grule.core.manager.GruleThreadPoolExecutor;
import com.grule.core.condition.Condition;
import com.grule.core.condition.GroovyCondition;
import com.grule.core.context.Context;
import com.grule.core.context.ConcurrentContext;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/03/05
 */
public class RuleEngineTest {


    @Test
    public void ITest() {

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
        ;
        //先决条件
        Condition condition = new GroovyCondition(conditionScript);
        //执行动作
        GroovyAction trueAction = new GroovyAction(trueActionScript);
        GroovyAction falseAction = new GroovyAction(falseActionScript);
        //注册单元
        condition.registerTrueUnit(trueAction);
        condition.registerFalseUnit(falseAction);
        //执行结果
        Context context = new ConcurrentContext();
        context.put("age", 16);
        context.put("name", "leo");
        context.put("will", "小于18小屁孩推车 ");
        //运行脚本
        RuleEngine.getInstance().setEntry(condition).start(context);
        context.put("age", 20);
        context.put("name", "lee");
        context.put("will", "大于18老司机开车");
        RuleEngine.getInstance().setEntry(condition).start(context);
        long start = System.currentTimeMillis();
        Context context1 = new ConcurrentContext();
        context1.put("age", 16);
        context1.put("name", "leo");
        context1.put("will", "小于18小屁孩推车 ");
        List<String> conditionScriptList = new ArrayList<>();
        int i=0,j=0;
        while (i<31) {
            conditionScriptList.add(conditionScript);
            i++;
        }
        context1.put("conditionScriptList", conditionScriptList);
        context1.put("trueActionScript", trueActionScript);
        context1.put("falseActionScript", falseActionScript);
        GruleThreadPoolExecutor.getInstance().runRuleEngine(context1);
        System.out.println("++++++++++++++++++++耗时：" + (System.currentTimeMillis() - start) + "ms.");
    }
}
