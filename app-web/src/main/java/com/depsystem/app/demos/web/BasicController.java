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

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import com.depsystem.app.loginServer.Login;
import com.depsystem.app.loginServer.LoginServerImpl;
import com.depsystem.app.systemServer.securityServer.securityVO.*;
import com.depsystem.app.systemServer.util.ResponseResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

import static com.depsystem.app.systemServer.util.ResponseResult.*;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BasicController {

    @Resource
    LoginServerImpl loginServer;
    @Autowired
    StringRedisTemplate redisTemplate;

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
    public ResponseResult<CaptchaVO> getCaptcha(HttpServletResponse response){
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200,100);
        String code = lineCaptcha.getCode();
        String base64 = lineCaptcha.getImageBase64Data();
        CaptchaVO captcha = new CaptchaVO();
        captcha.setId(UUID.randomUUID().toString());
        captcha.setBase64(base64);
        redisTemplate.opsForValue().set(captcha.getId(), code,10, TimeUnit.MINUTES);
        return ok(captcha,"验证创建成功");
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
