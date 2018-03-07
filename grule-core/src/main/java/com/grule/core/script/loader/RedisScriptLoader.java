package com.grule.core.script.loader;

import java.util.Map;

/**
 * Created on 2018/03/05
 */
public class RedisScriptLoader implements ScriptLoader {

    @Override
    public String get(String name) {
        return null;
    }

    @Override
    public boolean put(String name, String script) {
        return false;
    }

    @Override
    public boolean remove(String name) {
        return false;
    }

    @Override
    public Map<String, String> getAll() {
        return null;
    }
}
