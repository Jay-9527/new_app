/**
 * @author JOJO
 * @class AuthenticationServerImpl
 * @date 2023/4/21
 * @apiNote
 */

package com.depsystem.app.systemServer.securityServer.securityFilter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.depsystem.app.loginServer.Login;
import com.depsystem.app.loginServer.LoginDAO;
import com.depsystem.app.loginServer.LoginMapper;
import com.depsystem.app.systemServer.securityServer.entity.MyUserDetails;
import com.depsystem.app.systemServer.util.ResultConvert;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * 完成
 * 这里实现用户封装
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
        /* 封装用户信息 */
        Login loginVO = buildLoginVO(username);
        List<String> paths = getPath(username);
        String roles = getRole(username);
        return new MyUserDetails(loginVO,roles,paths);
    }

    private Login buildLoginVO(String name){
        LoginDAO userByName = usermapper.findUserByName(name);
        ResultConvert convert = new ResultConvert();
        return convert.map(userByName, Login.class);
    }

    private String getPermission(String name){
        return null;
    }

    /**
     * 获取访问路径
     * @param name 用户
     * @return 路径列表
     */
    private List<String> getPath(String name){
        Map<String, Object> userByModelPath = usermapper.findUserByModelPath(name);
        JSONObject entries = JSONUtil.parseObj(userByModelPath);
        String input = entries.get("urls").toString();
        List<String> paths = new ArrayList<>();

        String[] pairs = input.split(",");
        for (String pair : pairs) {
            paths.add(pair.trim());
        }
        return paths;
    }

    private String getRole(String name){
        return usermapper.getRole(name);
    }

}
