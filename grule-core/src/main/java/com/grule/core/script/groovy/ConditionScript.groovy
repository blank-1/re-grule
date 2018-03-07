package com.grule.core.script.groovy

import com.grule.core.context.Context

/**
 * Created by  on 2017/8/15.
 */
public interface ConditionScript {
    public boolean run(Context context);
}