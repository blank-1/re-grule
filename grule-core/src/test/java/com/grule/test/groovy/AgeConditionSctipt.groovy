package com.grule.test.groovy

import com.grule.core.context.Context
import com.grule.core.script.groovy.ConditionScript;

/**
 * Created on 2018/03/05
 */
class AgeConditionSctipt implements ConditionScript {
    @Override
    boolean run(Context context) {
        int age = (Integer) context.get("age");
        if (age < 18) {
            return false;
        }
        return true;
    }
}
