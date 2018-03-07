package com.grule.test.condition;

import com.grule.core.condition.AbstractCondition;
import com.grule.core.context.Context;
import com.grule.core.exception.UnitRunException;

/**
 * Created by  on 2017/8/11.
 */
public class ConditionTest extends AbstractCondition {
    @Override
    public boolean getResult() {
        return true;
    }

    @Override
    public void run(Context context) throws UnitRunException {
        context.put("ConditionScript", "hello");
    }
}
