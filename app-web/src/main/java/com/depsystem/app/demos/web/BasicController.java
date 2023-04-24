/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.depsystem.app.demos.web;

import com.depsystem.app.loginServer.Login;
import com.depsystem.app.loginServer.LoginServerImpl;
import com.depsystem.app.systemServer.securityServer.securityVO.*;
import com.depsystem.app.systemServer.util.RedisUtil;
import com.depsystem.app.systemServer.util.ResponseResult;
import com.wf.captcha.SpecCaptcha;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.depsystem.app.systemServer.util.ResponseResult.*;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BasicController {

    @Resource
    LoginServerImpl loginServer;

    @RequestMapping("/api/login")
    @ResponseBody
    public ResponseResult<?> login(@RequestBody Login user){
        Login login = loginServer.login(user.getName(), user.getPassword());
        return ok(login);
    }

    /**
     * CaptchaGenerateCreate
     */
    @RequestMapping(value = "/captcha",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<CaptchaVO> getCaptcha(){
        SpecCaptcha captcha = new SpecCaptcha(200,100,5);
        String code = captcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.set(key,code,3);
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setId(key);
        captchaVO.setBase64(captcha.toBase64());
        return ok(captchaVO,"验证创建成功");
    }

    /**
     * <a href="http://127.0.0.1:8080/hello?name=lisi">...</a>
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    /**
     * <a href="http://127.0.0.1:8080/user">...</a>
     */
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    /**
     * <a href="http://127.0.0.1:8080/save_user?name=newName&age=11">...</a>
     */
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    /**
     * <a href="http://127.0.0.1:8080/html">...</a>
     */
    @RequestMapping("/html")
    public String html(){
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
}
