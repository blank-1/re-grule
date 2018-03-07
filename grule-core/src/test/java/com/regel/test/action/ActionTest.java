package com.regel.test.action;

import com.regel.core.action.AbstractAction;
import com.regel.core.context.Context;
import com.regel.core.exception.UnitRunException;

import static org.junit.Assert.assertEquals;

/**
 * Created by  on 2017/8/11.
 */
public class ActionTest extends AbstractAction {

    @Override
    public void run(Context context) throws UnitRunException {
        assertEquals(context.get("ConditionScript"), "hello");
    }
}
