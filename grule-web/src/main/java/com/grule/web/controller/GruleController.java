package com.grule.web.controller;

import com.grule.common.req.ReqDoAction;
import com.grule.common.req.ReqPutScript;
import com.grule.core.RuleEngine;
import com.grule.core.action.GroovyAction;
import com.grule.core.condition.Condition;
import com.grule.core.condition.GroovyCondition;
import com.grule.core.context.Context;
import com.grule.core.context.ConcurrentContext;
import com.grule.core.script.loader.ScriptLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created on 2018/03/05
 */
@Controller
@RequestMapping("/")
public class GruleController {

    @Resource
    private ScriptLoader scriptLoader;

    @RequestMapping(value = "putScript")
    @ResponseBody
    public boolean putScript(@RequestBody ReqPutScript putScript) {
        if (putScript == null) {
            return false;
        }
        scriptLoader.put(putScript.getName(), putScript.getScript());
        return true;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("scripts", scriptLoader.getAll());
        return "index";
    }

    @RequestMapping("getScript")
    @ResponseBody
    public String getScript(@RequestParam String name) {
        return scriptLoader.get(name);
    }


    @RequestMapping("doAction")
    @ResponseBody
    public String doAction(@RequestBody ReqDoAction reqDoAction) {

        Condition condition = new GroovyCondition(scriptLoader.get(reqDoAction.getCondition()));
        GroovyAction trueAction = new GroovyAction(scriptLoader.get(reqDoAction.getTrueAction()));
        GroovyAction falseAction = new GroovyAction(scriptLoader.get(reqDoAction.getFalseAction()));
        GroovyAction switchAction = new GroovyAction(scriptLoader.get(reqDoAction.getSwitchAction()));

        condition.registerTrueUnit(trueAction);
        condition.registerFalseUnit(falseAction);

        //不管结果如何，都要有个遍历年龄的过程
        trueAction.registerUnit(switchAction);
        falseAction.registerUnit(switchAction);

        Context<String> context = new ConcurrentContext();
        context.put("age", reqDoAction.getAge());
        context.put("name", reqDoAction.getName());
        context.put("will", reqDoAction.getWill());
        RuleEngine.getInstance().setEntry(condition).start(context);
        String executeResult = context.getResult();

        return executeResult;
    }

}
