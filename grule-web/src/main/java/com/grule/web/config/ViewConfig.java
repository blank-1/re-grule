package com.grule.web.config;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018/03/05
 */
//@Configuration
public class ViewConfig {

    @Bean
    public VelocityEngine getVelocityEngine() {
        VelocityEngine velocityEngine=new VelocityEngine();
        //velocityEngine.set
        return null;
    }

}
