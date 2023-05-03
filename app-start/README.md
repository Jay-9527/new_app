# module description

入口模块，引导工程启动以及基础配置

测试账号和密码：

1. 136323636  HziqpyeW
2. 683697414  p5IHkJ47
3. 226294130  I41V3H0M

## 验证：

token 验证：

首先，请求->后端，token过滤器过滤，未发现放行，->用户过滤器，重写用户认证过滤器，注入进去。

@ModelAttribute 注解通常用于在 Spring MVC 中标记方法参数，指示该参数应该从模型中获取。与 Vue3 的 axios 一起使用时，可以使用 @ModelAttribute 将请求参数与后端的 Java 对象绑定起来，以便在后端方法中访问。这可以使代码更简洁和可读，因为您可以在方法参数中直接访问所需的对象，而无需在方法内部手动解析请求参数。

例如，以下是一个示例控制器方法，该方法使用 @ModelAttribute 绑定一个名为 user 的对象，并将其保存到数据库中：

```，
@PostMapping("/users")
public String createUser(@ModelAttribute("user") User user) {
    userService.saveUser(user);
    return "redirect:/users";
}
```

在前端，您可以使用 Vue3 的 axios 将表单数据作为 JSON 对象发送到后端，如下所示：

```vuejavascript
axios.post('/users', {
user: {
name: 'Alice',
age: 25,
email: 'alice@example.com'
}
})
```

这样，后端控制器方法就可以轻松地访问请求参数中的用户对象，并执行必要的操作。这种结合方式的优势是，能够更轻松地处理请求参数，减少代码的冗余程度，并增强代码的可读性和可维护性。

逻辑:

Login 【vo】： Name、password

token ： 是传入权限信息、用户名、用户角色所封装成的token。

roles ： 是通过后台查询并封装到response中。

在bilibili 中所看到的三更Up主，所采用的方法是将权限封装到userdetail中。然后在token生成中封装了userid.并接着状态类返回。

那么。

在本项目中。

所访问的路径需要动态一下。

所以，我们这边要对用户所访问的路径进行设计一下。

路径格式：

```
/index/[menu_item] >>>> 系统管理员角色的界面路径
/用户名/[menu_item] >>>> 用户所对应角色的界面路径
```
然后呢，对应的操作权限我们通过注解的方式添加到controller的API方法中。
来实现操作权限的限制。

与此同时，将token返回放到登录成功信息的响应体中。而controller则是返回用户信息。

经过查询源码和查阅相关的security6.0的教程视频发现。很多开发者都是将数据库所查到信息存到User Detail
中。然后丢给框架来存储认证好的信息到SecurityContextHold中。
随后再将用户信息存到redis缓存中，然后后面验证时只需要从Redis中获取所存放的用户信息出来验证就行了。 
并不需要再从请求中获取。

逻辑：

从controller中获取用户账号、密码。传递到service中。再service实现类中将所传递的信息封装到Username Password Token对象中。
并存到AuthenticationManager中。将用户信息返回。

随后Security 框架会从AuthenticationManager 中获取所存的用户名和密码。进行查询和验证。【所查询的操作是再User Detail ServiceImpl类中
实现，然后将查到的信息存到Redis中。】

##### JWT 验证

这个验证就是先从Redis中获取所存放的token。并将存放的token进行解码获取信息。判断是否正确。是就直接放行请求。
但再权限认证过程中，我们需要将存放到token的权限信息提取出来。然后判断。前提是在请求后访问操作时，我们要对其权限
拦截，通过拦截器。

---

原理学习地址:

[Spring Security 6.x\_云烟成雨TD的博客-CSDN博客](https://blog.csdn.net/qq_43437874/category_12259144.html)

[Spring Security 官网](https://spring.io/projects/spring-security[](https://www.bing.com/search?q=spring+security+&qs=n&form=QBRE&sp=-1&lq=0&pq=&sc=0-0&sk=&cvid=DA7F551E132D44F4929E186022FA945B&ghsh=0&ghacc=0&ghpl=&mkt=zh-CN#))
