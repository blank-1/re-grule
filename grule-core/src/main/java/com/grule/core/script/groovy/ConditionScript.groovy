package com.grule.core.script.groovy

import com.grule.core.context.Context

/**
 * Created on 2018/03/05
 */
public interface ConditionScript {
    public boolean run(Context context);
}