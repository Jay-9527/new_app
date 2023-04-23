package com.depsystem.app;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.depsystem.app.loginServer.Login;
import com.depsystem.app.loginServer.LoginMapper;
import com.depsystem.app.systemServer.util.JwtUtil;
import com.depsystem.app.systemServer.util.ResultConvert;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.*;

@SpringBootTest
class AppApplicationTests {

    @Autowired
    LoginMapper loginMapper;


    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encode = encoder.encode("HziqpyeW");

        System.out.println(encode);

        //Claims b = JwtUtil.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJbXSIsImlhdCI6MTY4MjA2NDMzMSwiZXhwIjoxNjgyMDY3OTMxfQ._bZ7dQ-GvWo5N98EKGieblGbsrcDu2NiYEYpyOgbFVg");
        //System.out.println(b);
        //String usernameFromToken = JwtUtil.getUsernameFromToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJbXSIsImlhdCI6MTY4MjA2NDMzMSwiZXhwIjoxNjgyMDY3OTMxfQ._bZ7dQ-GvWo5N98EKGieblGbsrcDu2NiYEYpyOgbFVg");
        //System.out.println(usernameFromToken);

        List<Map<String, Object>> userPermissions = loginMapper.getUserPermissions("683697414");
        //Integer[] permission = (Integer[]) userPermission.get("permissionId");
        //String permission2 = userPermission.get("permission").toString();
        //System.out.println(Arrays.toString(permission));
        //System.out.println(permission2);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Map<String, Object> permission : userPermissions) {
            Map<String, Object> map = new HashMap<>();
            map.put("permissionId", permission.get("permissionId"));
            map.put("permission", Arrays.asList(((String)permission.get("permission")).split(",")));
            resultList.add(map);
        }
        System.out.println(resultList);

        /* 操作权限获取：
        结果：[1, 111, 121, 131, 141, 151, 161, 171, 181, 191] 这些menu选项，可以增删改查。
        * 后面再操作编辑时就判断是其角色是否有这个权限。
        * [
        * {permissionId=1, permission=[delete, updata, select, add]},
        * {permissionId=111, permission=[add, delete, updata, select]},
        * {permissionId=121, permission=[select, updata, delete, add]},
        * {permissionId=131, permission=[add, delete, updata, select]},
        * {permissionId=141, permission=[add, delete, updata, select]},
        * {permissionId=151, permission=[add, select, delete, updata]},
        * {permissionId=161, permission=[add, delete, updata, select]},
        * {permissionId=171, permission=[select, updata, delete, add]},
        * {permissionId=181, permission=[add, delete, updata, select]},
        * {permissionId=191, permission=[add, delete, updata, select]}
        * ]
        * */

        Map<String, Object> userByModelPath = loginMapper.findUserByModelPath("136323636");
        //String name = (String) userByModelPath.get("name");
        //String urls = (String) userByModelPath.get("url");
        //System.out.println(userByModelPath.get("name"));
        //System.out.println(userByModelPath.get("url"));
        System.out.println(userByModelPath);
        JSONObject entries = JSONUtil.parseObj(userByModelPath);
        System.out.println(entries);
        /* 访问权限获取：
        结果：
    {urls=/:user/Debit,/:user/Fix,/:user/Inventory,/:user/Maintenance,/:user/Purchase,/:user/Query,/:user/Repair,/:user/Storage,/:user/Transfer,/me,
            name=683697414}
            */

        /**
         * 测试ResultConvert
         */
        //ResultConvert convert = new ResultConvert();


        String input = entries.get("urls").toString();
        List<String> paths = new ArrayList<>();
        String[] pairs = input.split(",");
        for (String pair : pairs) {
            paths.add(pair.trim());
        }
        System.out.println(Arrays.toString(pairs));

    }

}
