/**
 * @author JOJO
 * @class LoginServerImpl
 * @date 2023/4/20
 * @apiNote
 */

package com.depsystem.app.loginServer;

import jakarta.annotation.Resource;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


/**
 * 这里是用于将查询到的用户封装到UserDetails中并返回。
 * */
@Service
public class LoginServerImpl implements LoginServer {
    @Resource
    LoginMapper usermapper;

    @Resource
    AuthenticationManager authenticationsManager;

    @Override
    public Login login(String name, String password) {
        /* 检查用户的账号 */
        if (ObjectUtils.isEmpty(name)) {
            throw new UsernameNotFoundException("用户不能为空");
        }

        if (ObjectUtils.isEmpty(password)) {
            throw new UsernameNotFoundException("用户密码不能为空");
        }
        Login vo = new Login();
        vo.setName(name);
        vo.setPassword(password);
        /* 将前端所传递的用户信息存到AuthenticationManager中。 */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(name,password);
        Authentication authentication = authenticationsManager.authenticate(authenticationToken);

        return vo;
    }


}
