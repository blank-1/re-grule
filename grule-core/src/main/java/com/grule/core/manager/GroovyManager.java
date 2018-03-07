package com.grule.core.manager;

import com.grule.core.context.Context;
import groovy.lang.GroovyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created on 2018/03/05.
 */
public class GroovyManager {
    private static GroovyManager sharedInstance = new GroovyManager();

    /**
     * 获取 Groovy 管理者实例
     * @return 返回共享实例
     * */
    public static GroovyManager getInstance() {
        return sharedInstance;
    }

    private GroovyClassLoader classLoader;

    private GroovyManager() {
        this.classLoader = new GroovyClassLoader();
    }

    /**
     * 获取 Groovy 类加载器
     * @return Groovy 类加载器实例
     * */
    public GroovyClassLoader getGroovyClassLoader() {
        return this.classLoader;
    }

    /**
     * 执行 Groovy 方法
     * @param groovyClass Groovy 类
     * @param methodName 方法名
     * @param args 参数列表
     * @return 执行结果
     * */
    public Object invokeMethod(Class groovyClass, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        if (groovyClass != null) {
            try {
                Method runMethod = groovyClass.getMethod(methodName, Context.class);
                Object actionInstance = groovyClass.newInstance();
                return runMethod.invoke(actionInstance, args);
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                throw e;
            }
        } else {
            return null;
        }
    }

}
