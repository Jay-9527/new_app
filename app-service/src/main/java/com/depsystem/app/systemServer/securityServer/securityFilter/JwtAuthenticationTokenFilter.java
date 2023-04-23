/**
 * @author JOJO
 * @class JwtAuthenticationTokenFilter
 * @date 2023/4/21
 * @apiNote
 */

package com.depsystem.app.systemServer.securityServer.securityFilter;

import cn.hutool.core.util.StrUtil;
import com.depsystem.app.systemServer.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)){
            filterChain.doFilter(request,response);
        }
        try {
            JwtUtil.validateToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token错误");
        }
    }
}
