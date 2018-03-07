package com.regel.core.action;

import com.regel.core.Unit;

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
