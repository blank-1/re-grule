package com.regel.core.script.loader;

import java.util.Map;

/**
 * Created by  on 2017/8/15.
 */
public class MysqlScriptLoader implements ScriptLoader {

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
