package com.grule.core.manager;

import com.grule.core.action.GroovyAction;
import com.grule.core.condition.Condition;
import com.grule.core.condition.GroovyCondition;
import com.grule.core.context.Context;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GruleThreadPoolExecutor {

    //设置核心池大小
    int corePoolSize = 25;

    //设置线程池最大能接受多少线程
    int maximumPoolSize = 30;

    //当前线程数大于corePoolSize、小于maximumPoolSize时，超出corePoolSize的线程数的生命周期
    long keepActiveTime = 200;

    //设置时间单位，秒
    TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    //设置线程池缓存队列的排队策略为FIFO，并且指定缓存队列大小为5
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);

    private ThreadPoolExecutor pool = null;

    private  static GruleThreadPoolExecutor gtpeInstance = new GruleThreadPoolExecutor();

    /**
     * 获取规则引擎实例
     *
     * @return 规则引擎共享实例
     */
    public static GruleThreadPoolExecutor getInstance() {
        return gtpeInstance;
    }


    private GruleThreadPoolExecutor() {
    }

    /**
     * 线程池初始化方法
     * <p>
     * corePoolSize 核心线程池大小
     * maximumPoolSize 最大线程池大小
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>
     * threadFactory 新建线程工厂----new ActionThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     * 前面线程都没有执行完,此测试方法中用sleep(100)),
     * 任务会交给RejectedExecutionHandler来处理
     */
    public void init() {
        pool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepActiveTime,
                timeUnit,
                workQueue,
                new ActionThreadFactory(),
                new ActionRejectedExecutionHandler());
    }


    public void destory() {
        if (pool != null) {
            pool.shutdownNow();
        }
    }


    public ExecutorService getGruleThreadPoolExecutor() {
        return this.pool;
    }

    private class ActionThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = GruleThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }


    private class ActionRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                // 核心改造点，由blockingqueue的offer改成put阻塞方法
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // 构造rule线程池
    public void runRuleEngine(Context context) {
        gtpeInstance.init();
        ExecutorService pool = gtpeInstance.getGruleThreadPoolExecutor();
        List<String> conditionScriptList = (List<String>) context.get("conditionScriptList");
        String trueActionScript = (String) context.get("trueActionScript");
        String falseActionScript = (String) context.get("falseActionScript");
        if (!CollectionUtils.isEmpty(conditionScriptList)) {
            for (int i = 0; i < conditionScriptList.size(); i++) {
                final int finalI = i;
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        //先决条件
                        Condition condition = new GroovyCondition(conditionScriptList.get(finalI));
                        //执行动作
                        GroovyAction trueAction = new GroovyAction(trueActionScript);
                        GroovyAction falseAction = new GroovyAction(falseActionScript);
                        //注册单元
                        condition.registerTrueUnit(trueAction);
                        condition.registerFalseUnit(falseAction);
                        //运行脚本
                        RuleEngine.getInstance().setEntry(condition).start(context);
                    }
                });
            }
        }
        //gtpeInstance.destory();
    }
}