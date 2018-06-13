package com.sxt.cfg;

import com.sxt.utils.ObjectUtil;
import com.sxt.utils.RequestData;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 */
public class BaseContextHandler {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static String CONTEXT_KEY_USER_ID = "CONTEXT_KEY_USER_ID";
    public static String CONTEXT_KEY_USERNAME = "CONTEXT_KEY_USERNAME";
    public static String CONTEXT_KEY_USER_NAME = "CONTEXT_KEY_USER_NAME";
    public static String CONTEXT_KEY_USER_TOKEN = "CONTEXT_KEY_USER_TOKEN";
    public static String CONTEXT_KEY_MOBILE = "CONTEXT_KEY_MOBILE";
    public static String CONTEXT_IP = "CONTEXT_IP";

    public static String RESPONSE = "RESPONSE";

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static void setResponse(HttpServletResponse response) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(RESPONSE, response);
    }


    public static HttpServletResponse getResponse() {
        Object value = get(RESPONSE);
        if (value != null && value instanceof HttpServletResponse) {
            return (HttpServletResponse) value;
        }
        return null;
    }

    public static String getUserID() {
        Object value = get(CONTEXT_KEY_USER_ID);
        return returnObjectValue(value);
    }

    public static String getIp() {
        Object value = get(CONTEXT_IP);
        return returnObjectValue(value);
    }

    public static String getUserName() {
        Object value = get(CONTEXT_KEY_USERNAME);
        return returnObjectValue(value);
    }


    public static String getName() {
        Object value = get(CONTEXT_KEY_USER_NAME);
        return ObjectUtil.tostring(value);
    }

    public static String getMobile() {
        Object value = get(CONTEXT_KEY_MOBILE);
        return ObjectUtil.tostring(value);
    }

    public static String getToken() {
        Object value = get(CONTEXT_KEY_USER_TOKEN);
        return ObjectUtil.tostring(value);
    }

    public static void setRequestDate(RequestData requestDate) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put("requestDate", requestDate);
    }

    public static RequestData getRequestDate() {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        Object o = map.get("requestDate");

        if (o != null && o instanceof RequestData) {
            return (RequestData) o;
        }
        return null;
    }

    public static void setToken(String token) {
        set(CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setName(String name) {
        set(CONTEXT_KEY_USER_NAME, name);
    }

    public static void setUserID(String userID) {
        set(CONTEXT_KEY_USER_ID, userID);
    }

    public static void setIp(String ip) {
        set(CONTEXT_IP, ip);
    }

    public static void setUserName(String username) {
        set(CONTEXT_KEY_USERNAME, username);
    }

    public static void setMobile(String mobile) {
        set(CONTEXT_KEY_MOBILE, mobile);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
