/**
 * @author JOJO
 * @class AuthenticationsSuccessHandler
 * @date 2023/4/21
 * @apiNote
 */

package com.depsystem.app.systemServer.securityServer.handler;

import com.depsystem.app.systemServer.securityServer.entity.MyUserDetails;
import com.depsystem.app.systemServer.util.JwtUtil;
import com.depsystem.app.systemServer.util.RedisUtil;
import com.depsystem.app.systemServer.util.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 成功状态
 * @author adiao
 */
public class AuthenticationsSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * @param request 请求
     * @param response 响应
     * @param authentication 认证消息
     * @throws IOException IO错误
     * @throws ServletException Servlet错误
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //TODO 这里是封装响应的地方。
        /* 实现：
        *  格式
        *  {
        *   code: 存放状态码
        *   msg: 存放消息
        *   data: 存放ResponseResult状态工具类对象。
        *   token: 存放生成好的token
        *  } */

        MyUserDetails principal = (MyUserDetails) authentication.getPrincipal();
        System.out.println(principal);
        Map<String,Object> userinfo = new HashMap<>();
        userinfo.put("name",principal.getUsername());
        userinfo.put("role",principal.getRoles());
        userinfo.put("path",principal.getPath().toString());
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.set("userinfo:",userinfo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String s = new ObjectMapper().writeValueAsString(ResponseResult.ok(200,"登录成功",principal.getPath(),
                JwtUtil.generateToken(userinfo)));
        response.getWriter().write(s);

    }
}
