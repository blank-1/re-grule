package com.grule.core.action;


import com.grule.core.Unit;

/**
 * Created on 2018/03/05.
 */
public abstract class AbstractAction implements Action {

    protected Unit unit;

    /**
     * 注册下一个动作
     * @param unit 单元
     */
    public final void registerUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * 简单的动作，如果需要连贯动作，直接调用next
     *
     * @return
     */
    public final Unit next() {
        return unit;
    }
}
