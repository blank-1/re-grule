package com.grule.common.req;

import java.io.Serializable;

/**
 * Created on 2018/03/05
 */
public class ReqPutScript implements Serializable{
    private static final long serialVersionUID = -475232470627222709L;
    String name;
    String script;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
