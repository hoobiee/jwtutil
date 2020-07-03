package com.xiaoliu.controller;

import com.xiaoliu.utils.JWTUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HoneController {
    @GetMapping("/login")
    public HashMap <String ,Object> login(String username){
        HashMap <String ,Object> hashMap = new HashMap();
        if("test".equals(username)){
            //登入成功
            //生成token
            String token = JWTUtils.getToken(username);
            hashMap.put("token",token);
            return hashMap;
        }
        hashMap.put("token","");
        return hashMap;
    }

    /**
     * 测试接口 不带token进不来
     * @param username
     * @return
     */
    @GetMapping("/test")
    public HashMap <String ,Object> test(String username){
        HashMap <String ,Object> hashMap = new HashMap();
        hashMap.put("msg","登入成功");
        return hashMap;
    }
}
