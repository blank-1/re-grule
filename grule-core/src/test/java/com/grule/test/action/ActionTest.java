package com.grule.test.action;

import com.grule.core.action.AbstractAction;
import com.grule.core.context.Context;
import com.grule.core.exception.UnitRunException;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2018/03/05.
 */
public class ActionTest extends AbstractAction {

    @Override
    public void run(Context context) throws UnitRunException {
        assertEquals(context.get("ConditionScript"), "hello");
    }
}
