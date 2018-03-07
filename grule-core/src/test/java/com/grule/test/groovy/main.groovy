package com.grule.test.groovy

/**
 * Created by  on 2017/8/14.
 */

regelApplication = new GroovyActionScript();
regelApplication.name = "i'm your father";
regelApplication.ids = [1, 2, 3, 4, 5]
regelApplication.hello();
for (int i = 0; i < 4; i++) {
    println("i:" + i);
}
assert regelApplication instanceof GroovyActionScript

def javaCode = "println(\"fuck you\");"