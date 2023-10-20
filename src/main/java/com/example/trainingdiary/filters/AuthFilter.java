package com.example.trainingdiary.filters;

import com.example.trainingdiary.DAO.impl.RememberDAO;
import com.example.trainingdiary.DAO.impl.UserDAO;
import com.example.trainingdiary.models.Remember;
import com.example.trainingdiary.models.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        boolean isAuth = false;
        if(req.getSession().getAttribute("User") == null){
            Cookie[] cookies = req.getCookies();
            Cookie cookie = null;
            for(Cookie i: cookies){
                if("Rem".equals(i.getName())){
                    cookie = i;
                }
            }

            if(cookie != null){
                RememberDAO dao = new RememberDAO();
                Remember remember = dao.getByUUID(cookie.getValue());
                UserDAO userDAO = new UserDAO();
                if(remember != null){
                    User user = userDAO.get(remember.getUserId());
                    if(user != null){
                        isAuth = true;
                        req.getSession().setAttribute("User", user);
                    }
                }
            }
        }else{
            isAuth = true;
        }

        if(isAuth){
            if(req.getRequestURI().equals(req.getContextPath()+"/login") ||
                    req.getRequestURI().equals(req.getContextPath()+"/register")){
                res.sendRedirect(req.getContextPath() + "/");
            }else {
                chain.doFilter(req, res);
            }
        }else{
            if(req.getRequestURI().equals(req.getContextPath()+"/login") ||
                    req.getRequestURI().equals(req.getContextPath()+"/register")){
                chain.doFilter(req, res);
            }else {
                res.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }
}
