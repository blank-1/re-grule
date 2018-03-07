package com.grule.core.script.groovy

import com.grule.core.context.Context;

/**
 * Created on 2018/03/05
 */
class Before18ActionScript implements ActionScript {
    @Override
    void run(Context context) {
        String name = (String) context.get("name");
        String will = (String) context.get("will");
        String res = "滴！学生卡，请下车～～～～" + name + " is to young to " + will;
        println(res)
        context.setResult(res);
    }
}
