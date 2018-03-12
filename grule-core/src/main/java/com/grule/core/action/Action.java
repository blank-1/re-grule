package com.grule.core.action;

import com.grule.core.condition.Unit;

/**
 * Created on 2018/03/05.
 */
public interface Action extends Unit {

    /**
     * 注册单元
     * @param unit 单元
     * */
    public void registerUnit(Unit unit);

}
