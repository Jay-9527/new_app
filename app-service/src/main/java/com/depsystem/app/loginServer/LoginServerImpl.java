/**
 * @author JOJO
 * @class LoginServerImpl
 * @date 2023/4/20
 * @apiNote
 */

package com.depsystem.app.loginServer;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class LoginServerImpl implements LoginServer {
    @Resource
    LoginMapper userMapper;

    /** 登录
     * @param name     用户名
     * @param password 密码
     * @return vo
     */
    @Override
    public Login login(String name, String password) {




        LoginDAO userByNameAndPassword = userMapper.findUserByNameAndPassword(name, password);
        Login vo =new Login();
        if (JSONUtil.isNull(userByNameAndPassword)){
            vo.setName(userByNameAndPassword.getName());
            vo.setPassword(userByNameAndPassword.getPassword());
        }else {
            System.out.println("登录失败");
        }
        return vo;
    }

    /** 注测
     * @param login VO
     * @return vo
     */
    @Override
    public Login register(Login login) {
        return null;
    }
}
