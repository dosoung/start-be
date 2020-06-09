package kr.ac.jejunu.user;

import javax.servlet.ServletContextListener;


import javax.servlet.ServletContextEvent;

public class UserContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("******************* Context init ***************************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("******************* Context destroy  ***************************");

    }
}

