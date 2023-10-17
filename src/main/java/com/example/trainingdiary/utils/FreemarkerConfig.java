package com.example.trainingdiary.utils;

import freemarker.template.Configuration;

import javax.servlet.ServletContext;
import java.util.Locale;

public class FreemarkerConfig {
    private static Configuration cfg = null;

    public static Configuration getConfig(ServletContext servletContext){
        if(cfg == null){
            cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setServletContextForTemplateLoading(servletContext, "/WEB-INF/templates/");
        }
        return cfg;
    }
}
