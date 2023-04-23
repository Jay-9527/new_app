/**
 * @author JOJO
 * @class AuthenticationServerImpl
 * @date 2023/4/21
 * @apiNote
 */

package com.depsystem.app.systemServer.securityServer;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.depsystem.app.loginServer.Login;
import com.depsystem.app.loginServer.LoginDAO;
import com.depsystem.app.loginServer.LoginMapper;
import com.depsystem.app.systemServer.securityServer.entity.MyUserDetails;
import com.depsystem.app.systemServer.util.JwtUtil;
import com.depsystem.app.systemServer.util.ResultConvert;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 认证服务
 * 用户认证用户的账号密码是否正确。
 * 并同时检查用户可访问的路径。
 * 以及用户对路径下的权限。
 * 这里实现查询和封装authentication。
 *
 * @author adiao
 */
@Service
public class AuthenticationServerImpl implements UserDetailsService {
    @Resource
    LoginMapper usermapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* 检查用户的账号 */
        if (ObjectUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户不能为空");
        }
        /* 检查用户权限 */
        Login loginVO = new Login();
        Map<String, Object> userByModelPath = usermapper.findUserByModelPath(username);
        JSONObject entries = JSONUtil.parseObj(userByModelPath);
        String input = entries.get("urls").toString();
        List<String> paths = new ArrayList<>();
        String[] pairs = input.split(",");
        for (String pair : pairs) {
            paths.add(pair.trim());
        }

        return new MyUserDetails();
    }

    private void checkPermission(String token){
        try {
            JwtUtil.validateToken(token);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkPassword(){

    }


    private String getPermission(String name){
        return null;
    }


}
