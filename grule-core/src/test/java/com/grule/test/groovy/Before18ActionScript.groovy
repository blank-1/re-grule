package com.grule.test.groovy

import com.grule.core.context.Context
import com.grule.core.script.groovy.ActionScript;

/**
 * Created on 2018/03/05
 */
class Before18ActionScript implements ActionScript {
    @Override
    void run(Context context) {
        String name = (String) context.get("name");
        String will = (String) context.get("will");
        String res = "滴！学生卡，请下车～～～～" + name + " is too young to " + will;
        println(res)
        context.setResult(res);
    }
}
