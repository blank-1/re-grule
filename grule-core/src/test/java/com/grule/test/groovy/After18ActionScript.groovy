package com.grule.test.groovy

import com.grule.core.context.Context
import com.grule.core.script.groovy.ActionScript;

/**
 * Created on 2018/03/05
 */
class After18ActionScript implements ActionScript {
    @Override
    void run(Context context) {
        String name = (String) context.get("name");
        String will = (String) context.get("will");
        String res = "滴！市民卡，请上车～～～～" + name + " is ok enough to " + will;
        println(res)
        context.setResult(res);
    }
}
