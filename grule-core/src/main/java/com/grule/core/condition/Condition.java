package com.grule.core.condition;

import com.grule.core.Unit;

/**
 * Created by  on 2017/8/11.
 */
public interface Condition extends Unit {

    /**
     * 获取条件执行结果
     * @return 真或假
     * */
    public boolean getResult();

    /**
     * 注册条件为真时执行的单元
     * @param unit 单元
     */
    public void registerTrueUnit(Unit unit);

    /**
     * 注册条件为假时执行的单元
     * @param unit 单元
     */
    public void registerFalseUnit(Unit unit);

}
