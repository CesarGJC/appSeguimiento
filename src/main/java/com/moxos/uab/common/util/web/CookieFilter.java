package com.moxos.uab.common.util.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CookieFilter<TData> {
    private String name = "";
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Cookie cookie;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private TData data;


    public CookieFilter(String name, HttpServletRequest request, HttpServletResponse response) {
        this.name = name;
        this.request = request;
        this.response = response;
        init();
    }

    public String getData(String value) {
        if (value == null)
            return null;
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public void setData(TData data) {
        try {
            String filtroJson = URLEncoder.encode(objectMapper.writeValueAsString(data), StandardCharsets.UTF_8.name());
            cookie = new Cookie(name, filtroJson);
            cookie.setSecure(true); // Si la cookie original era segura
            cookie.setHttpOnly(true);
            cookie.setMaxAge(1 * 24 * 60 * 60); // 1 días
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public void init() {
        cookie = new Cookie(name, "");
        cookie.setSecure(true); // Si la cookie original era segura
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1 * 24 * 60 * 60); // 1 días
        response.addCookie(cookie);
    }
}
