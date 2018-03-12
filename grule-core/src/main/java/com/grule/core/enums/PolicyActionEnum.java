package com.grule.core.enums;

public enum PolicyActionEnum {
    PASS("放行"), REVIEW("挂起"), DENY("拦截"), SMS("短信通知");

    private String desc;//中文描述

    /**
     * 私有构造,防止被外部调用
     *
     * @param desc
     */
    private PolicyActionEnum(String desc) {
        this.desc = desc;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }
}