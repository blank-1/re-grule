package com.regel.core;

import com.regel.core.context.Context;
import com.regel.core.exception.UnitRunException;

/**
 * Created by  on 2017/8/11.
 */
public interface Unit {

    /**
     * 返回下一个单元
     * @return 返回下一个单元，未执行或不存在返回 null
     * */
    public Unit next();

    /**
     * 执行单元
     * @param context 执行上下文
     * @throws UnitRunException 单元执行异常
     * */
    public void run(Context context) throws UnitRunException;

}
