package com.grule.core.action;

import com.grule.core.Unit;

/**
 * Created by  on 2017/8/11.
 */
public interface Action extends Unit {

    /**
     * 注册单元
     * @param unit 单元
     * */
    public void registerUnit(Unit unit);

}
