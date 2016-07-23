/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.interceptors;

import app.db.Logs;
import app.db.dao.LogsDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author NavNag
 */
public class AppLogsInterceptor implements HandlerInterceptor {

    @Autowired
    private LogsDao logsDao;

    @Override
    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
        String ipAddress = hsr.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = hsr.getRemoteAddr();
        }
        Logs log = new Logs();
        log.setIp(ipAddress);
        log.setPath(hsr.getServletPath());
        logsDao.save(log);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
    }

}
