package com.grule.core.script.groovy

import com.grule.core.context.Context;

/**
 * Created on 2018/03/05
 */
class After18ActionScript implements ActionScript {
    @Override
    void run(Context context) {
        String name = (String) context.get("name");
        String will = (String) context.get("will");
        String res = "老司机开车进行中" + name + " is old enough to " + will;
        println(res)
        context.setResult(res);
    }
}
