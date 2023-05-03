package com.depsystem.app.systemServer.securityServer.securityFilter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.io.InputStream;

public class UsernamePasswordAuthenticationFilter {
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    /**
     * 登陆拦截请求
     */
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/api/login",
            "POST");
    private final String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;

    private final String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private UsernamePasswordAuthenticationToken authRequest = null;

    private final boolean postOnly = true;
    public UsernamePasswordAuthenticationFilter(){
        super();
    }
    protected UsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            //  在这里取消原有的通过 request parameter 形式获取用户名密码的方法, 替换为 json 数据格式的获取
            ObjectMapper objectMapper = new ObjectMapper();
            try (InputStream inputStream = request.getInputStream()) {
                JsonNode jsonNode = objectMapper.readTree(inputStream);
                String username = jsonNode.get("username").asText();
                String password = jsonNode.get("password").asText();
                authRequest = new UsernamePasswordAuthenticationToken(username, password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        ObjectMapper mapper = new ObjectMapper();
//        UsernamePasswordAuthenticationToken authRequest = null;
//        try (InputStream is = request.getInputStream()) {
//            Map<String,Object> authenticationBean = mapper.readValue(is, Map.class);
//            authRequest = new UsernamePasswordAuthenticationToken(
//                    authenticationBean.get("username"), authenticationBean.get("password"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // 源码方法
        /*String username = obtainUsername(request);
        username = (username != null) ? username : "";
        username = username.trim();
        String password = obtainPassword(request);
        password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);*/
        // Allow subclasses to set the "details" property
//        authRequest.setDetails(mapper);
//        return this.getAuthenticationManager().authenticate(authRequest);
        this.setDetails(request, authRequest);
        return null;
    }
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(request);
    }
}
