package com.regel.test.condition;

import com.regel.core.condition.AbstractCondition;
import com.regel.core.context.Context;
import com.regel.core.exception.UnitRunException;

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
