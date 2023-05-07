# 资产系统前端项目

> 这是前端项目所采用的框架：Vue 3 + Vite + Bootstrap，这是DepSystem资产系统的界面代码。
> 框架地址：
> 地址1：https://vxetable.cn/#/table/base/basic vxeTable的说明文档。

> 地址2：https://github.com/axios/axios#axios-api Axios 的说明文档。

> 地址3：https://router.vuejs.org/installation.html VueRouter 的说明文档。

---

## 前端界面

效果：

![效果1](http://rrq7aezf5.sabkt.gdipper.com/typora/%E6%88%AA%E5%B1%8F2023-04-04%20%E4%B8%8A%E5%8D%889.12.33.png)

![效果2](http://rrq7aezf5.sabkt.gdipper.com/typora/%E6%88%AA%E5%B1%8F2023-04-04%20%E4%B8%8A%E5%8D%889.13.10.png)

登录提示效果：

![截屏2023-04-05 上午1.37.02](https://i.imgur.com/up8uqdU.png)

---

项目目录结构：

```bash
app5
|__ .vsconde
|__ node_modules
|__ public
|__ src
    |__ assets
    |__ components
    |__ image
    |__ router
    |__ scss
    |__ utils
    |__ views
    |__ main.js
    |__ style.css
|__ .gitignore
|__ index.html
|__ package-lock.json
|__ package.json
|__ README.md
|__ vite.config.js
```

## 安装

运行环境：

node 16+ 版本

安装好后，下载本仓库代码。

```sh
git clone http://120.24.203.75:3000/DepSystem/WebVue.git
```

[代码地址](http://120.24.203.75:3000/DepSystem/WebVue.git)

接着运行

```bash
npm build
```

开发运行下面命令

```sh
npm run dev
```

打包好后，将其打包好的文件放到Spring boot的static文件夹中。

运行Spring boot项目即可。

---

效果：登录后获取用户信息，并根据所返回的信息，动态的添加访问路径。再进入到后台中。

登录分析：

这里的效果是先请求，获取数据将数据存到Store中，让守卫根据Store来动态添加路径到menu中。[token]是后端返回存到本地的数据。

// setup(props, context) 这是setup函数所要传入的参数。

/// 操作对象。

// reactive 是和Ref一样的函数。可传递参数和对象。是引用型实例。
