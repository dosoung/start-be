package kr.ac.jejunu.userdao;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class UserRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("******************* requiest destroy ***************************");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("******************* request init ***************************");
    }
}
