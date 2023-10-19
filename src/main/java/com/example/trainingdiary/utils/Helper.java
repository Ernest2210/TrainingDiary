package com.example.trainingdiary.utils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Helper {
    public static HashMap<String, Object> getGeneralContext(HttpServletRequest request){
        HashMap<String, Object> root = new HashMap<>();
        root.put("user", request.getSession().getAttribute("User"));
        root.put("user_name", "none");
        root.put("servletContext", request.getContextPath());
        return root;
    }
}
