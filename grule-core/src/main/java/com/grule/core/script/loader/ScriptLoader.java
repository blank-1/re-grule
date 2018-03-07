package com.grule.core.script.loader;

import java.util.Map;

/**
 * Created on 2018/03/05
 */
public interface ScriptLoader {

    /**
     * 获取一个脚本
     *
     * @param name
     * @return
     */
    public String get(String name);

    /**
     * 添加或者修改一个脚本
     *
     * @param name
     * @return
     */
    public boolean put(String name, String script);

    /**
     * 删除一个脚本
     *
     * @param name
     * @return
     */
    public boolean remove(String name);

    /**
     * 获取所有脚本
     *
     * @return
     */
    public Map<String, String> getAll();


}

