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

---

原理学习地址:

[Spring Security 6.x\_云烟成雨TD的博客-CSDN博客](https://blog.csdn.net/qq_43437874/category_12259144.html)

[Spring Security 官网](https://spring.io/projects/spring-security[](https://www.bing.com/search?q=spring+security+&qs=n&form=QBRE&sp=-1&lq=0&pq=&sc=0-0&sk=&cvid=DA7F551E132D44F4929E186022FA945B&ghsh=0&ghacc=0&ghpl=&mkt=zh-CN#))
