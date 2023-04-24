/**
 * @author JOJO
 * @class JwtAuthenticationTokenFilter
 * @date 2023/4/21
 * @apiNote
 */

package com.depsystem.app.systemServer.securityServer.securityFilter;

import cn.hutool.core.util.StrUtil;
import com.depsystem.app.loginServer.Login;
import com.depsystem.app.systemServer.securityServer.entity.MyUserDetails;
import com.depsystem.app.systemServer.util.JwtUtil;
import com.depsystem.app.systemServer.util.RedisUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        Map<String,Object> unauthorisedList = null;

        if (StrUtil.isBlank(token)){
            filterChain.doFilter(request,response);
        }
        try {
            String usernameFromToken = JwtUtil.getUsernameFromToken(token);
            ArrayList<String> pathFromToken = JwtUtil.getPathFromToken(token);
            String roleFromToken = JwtUtil.getRoleFromToken(token);
            unauthorisedList.put("name",usernameFromToken);
            unauthorisedList.put("roles",roleFromToken);
            unauthorisedList.put("path",pathFromToken);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token错误");
        }

        String redisKey = "userinfo:";
        MyUserDetails o = new MyUserDetails();
        try {
            o = (MyUserDetails) redisUtil.get(redisKey);
        }catch (RuntimeException e){
            e.printStackTrace();
        }

        if (Objects.isNull(o)){
            throw new RuntimeException("获取用户信息失败");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(o,null,null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);

    }
}
