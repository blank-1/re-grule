package com.grule.test.groovy

import com.alibaba.fastjson.JSON
import org.junit.Test

/**
 * Created on 2018/03/05
 */
class GroovyActionScript {
    def name;
    def ids;

    @Test
    void hello() {
        def map = new HashMap();
        map.put("fucl", "hello");
        map.put("fuck", "fuckcccc");
        def hello = JSON.toJSONString(map);
        println(hello);
    }

    public static void main(String[] args) {
        new GroovyActionScript().hello()
    }
}
