package com.regel.core.context;

/**
 * Created by  on 2017/8/11.
 */
public interface Context<T> {

    /**
     * 向上下文中添加数据
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, Object value);

    /**
     * 从上下文中获取数据
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key);

    public boolean setResult(T result);

    public T getResult();

}
