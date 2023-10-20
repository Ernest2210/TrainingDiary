package com.example.trainingdiary.utils;

import com.example.trainingdiary.DAO.impl.MusculeDAO;
import com.example.trainingdiary.models.Muscule;
import com.example.trainingdiary.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {
    public static HashMap<String, Object> getGeneralContext(HttpServletRequest request){
        HashMap<String, Object> root = new HashMap<>();
        root.put("user", request.getSession().getAttribute("User"));
        MusculeDAO musculeDAO = new MusculeDAO();
        root.put("musculeTypes", musculeDAO.getAll());
        root.put("servletContext", request.getContextPath());
        return root;
    }

    public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String location){
        try {
            response.sendRedirect(request.getContextPath() + location);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(HttpSession session){
        return ((User) session.getAttribute("User"));
    }
}
