package com.depsystem.app;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.depsystem.app.loginServer.Login;
import com.depsystem.app.loginServer.LoginDAO;
import com.depsystem.app.loginServer.LoginMapper;
import com.depsystem.app.systemServer.securityServer.entity.MyUserDetails;
import com.depsystem.app.systemServer.util.JwtUtil;
import com.depsystem.app.systemServer.util.ResultConvert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        System.out.println("**************************************");
        System.out.println("测试获取角色");
        LoginDAO userByName = loginMapper.findUserByName("683697414");
        ResultConvert convert = new ResultConvert();
        Login map1 = convert.map(userByName, Login.class);

        System.out.println(map1);
        System.out.println("**************************************");

        String role = loginMapper.getRole("683697414");
        System.out.println(role);

        List<Map<String, Object>> userPermissions = loginMapper.getUserPermissions("683697414");
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
        System.out.println(userByModelPath);
        JSONObject entries = JSONUtil.parseObj(userByModelPath);
        System.out.println(entries);
        /* 访问权限获取：
        结果：
    {urls=/:user/Debit,/:user/Fix,/:user/Inventory,/:user/Maintenance,/:user/Purchase,/:user/Query,/:user/Repair,/:user/Storage,/:user/Transfer,/me,
            name=683697414}
            */

        /*
         * 测试ResultConvert
         */

        String input = entries.get("urls").toString();
        List<String> paths = new ArrayList<>();
        String[] pairs = input.split(",");
        for (String pair : pairs) {
            paths.add(pair.trim());
        }
        System.out.println(Arrays.toString(pairs));

        System.out.println("**************************************");
        System.out.println("测试MyUserDetail");

        MyUserDetails userDetails = new MyUserDetails(map1,role,paths);
        System.out.println(userDetails);

        String roles = userDetails.getRoles();
        System.out.println(roles);
        String username = userDetails.getUsername();
        System.out.println(username);
        List<String> path = userDetails.getPath();
        System.out.println(path);

        System.out.println("**************************************");
        boolean b = JwtUtil.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJwYXRoIjoiWy9EZWJpdCwgL0ZpeCwgL0ludmVudG9yeSwgL01haW50ZW5hbmNlLCAvbWUsIC9QdXJjaGFzZSwgL1F1ZXJ5LCAvUmVwYWlyLCAvU3RvcmFnZSwgL1RyYW5zZmVyXSIsInJvbGUiOiJhZG1pbjIiLCJuYW1lIjoiNjgzNjk3NDE0IiwiZXhwIjoxNjgyMjc2OTMzLCJpYXQiOjE2ODIyNzMzMzN9.0zmWGYUcTg43nbx1QA4WIOjg5jdB3Ne8R81ah7b6MVI").isAuthenticated();
        System.out.println(b);
        String usernameFromToken = JwtUtil.getUsernameFromToken("eyJhbGciOiJIUzI1NiJ9.eyJwYXRoIjoiWy9EZWJpdCwgL0ZpeCwgL0ludmVudG9yeSwgL01haW50ZW5hbmNlLCAvbWUsIC9QdXJjaGFzZSwgL1F1ZXJ5LCAvUmVwYWlyLCAvU3RvcmFnZSwgL1RyYW5zZmVyXSIsInJvbGUiOiJhZG1pbjIiLCJuYW1lIjoiNjgzNjk3NDE0IiwiZXhwIjoxNjgyMjc2OTMzLCJpYXQiOjE2ODIyNzMzMzN9.0zmWGYUcTg43nbx1QA4WIOjg5jdB3Ne8R81ah7b6MVI");
        String roleFromToken = JwtUtil.getRoleFromToken("eyJhbGciOiJIUzI1NiJ9.eyJwYXRoIjoiWy9EZWJpdCwgL0ZpeCwgL0ludmVudG9yeSwgL01haW50ZW5hbmNlLCAvbWUsIC9QdXJjaGFzZSwgL1F1ZXJ5LCAvUmVwYWlyLCAvU3RvcmFnZSwgL1RyYW5zZmVyXSIsInJvbGUiOiJhZG1pbjIiLCJuYW1lIjoiNjgzNjk3NDE0IiwiZXhwIjoxNjgyMjc2OTMzLCJpYXQiOjE2ODIyNzMzMzN9.0zmWGYUcTg43nbx1QA4WIOjg5jdB3Ne8R81ah7b6MVI");
        //List<String> pathFromToken = JwtUtil.getPathFromToken("eyJhbGciOiJIUzI1NiJ9.eyJwYXRoIjpbIi9EZWJpdCIsIi9GaXgiLCIvSW52ZW50b3J5IiwiL01haW50ZW5hbmNlIiwiL21lIiwiL1B1cmNoYXNlIiwiL1F1ZXJ5IiwiL1JlcGFpciIsIi9TdG9yYWdlIiwiL1RyYW5zZmVyIl0sInJvbGUiOiJhZG1pbjIiLCJuYW1lIjoiNjgzNjk3NDE0IiwiZXhwIjoxNjgyMjU0NjkyLCJpYXQiOjE2ODIyNTEwOTJ9.Sf3p5o8p_Tj-33zXfofx1ld-ugXW7WYCewhx27nHSnw");
        System.out.println(usernameFromToken);
        System.out.println(roleFromToken);
        //System.out.println(pathFromToken);
        System.out.println("**************************************");
        JWT jwt = JWTUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJwYXRoIjpbIi9EZWJpdCIsIi9GaXgiLCIvSW52ZW50b3J5IiwiL01haW50ZW5hbmNlIiwiL21lIiwiL1B1cmNoYXNlIiwiL1F1ZXJ5IiwiL1JlcGFpciIsIi9TdG9yYWdlIiwiL1RyYW5zZmVyIl0sInJvbGUiOiJhZG1pbjIiLCJuYW1lIjoiNjgzNjk3NDE0IiwiZXhwIjoxNjgyMjU0MzkzLCJpYXQiOjE2ODIyNTA3OTN9.Jwg5jjobsLCaJy42aF0MzTpoA6ryfnw8SGiJCOdh5WI");
        JWTPayload payload = jwt.getPayload();
        System.out.println(payload);
        JWTHeader header = jwt.getHeader();
        System.out.println(header);
        JSONObject payloads = jwt.getPayloads();
        System.out.println(payloads);
        System.out.println("**************************************");

    }





    @Test
    void TestRedis(){
        /* 测试Redis 工具类是否可用。 */
        System.out.println("***************************************************");

        System.out.println();
    }

    @Test
    void redisTest(){
        String redisKey = "userinfo:";
//        Object o = redisUtil.get(redisKey);
//        System.out.println(o.toString());
        String key = UUID.randomUUID().toString();

    }



}
