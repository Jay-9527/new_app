/**
 * @author JOJO
 * @class AuthenticationsFailureHandler
 * @date 2023/4/21
 * @apiNote
 */

package com.depsystem.app.securityServer.handler;

import cn.hutool.json.JSONUtil;
import com.depsystem.app.systemServer.util.ResponseResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class AuthenticationsFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONUtil.toJsonPrettyStr(ResponseResult.failed(401,exception.getMessage())));
    }
}
