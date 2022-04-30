![](https://img.shields.io/badge/java-15-blue)
![](https://img.shields.io/badge/springboot-2.2.6-green)
![](https://img.shields.io/badge/mysql-8.0.26-orange)
![](https://img.shields.io/badge/mybatis_plus-3.2.0-yellow)
![](https://img.shields.io/badge/vuejs-2-brightgreen)
![](https://img.shields.io/badge/element_ui-2.1-inactive)
![](https://img.shields.io/badge/axios-12-purple)

# spring_vue_simple_blog

本项目是我基于`springboot`的**入门级**博客后台，学习xiaolblog项目。

## 技术栈

**后端**：
- springboot
- mybatis-plus
- mysql

**前端**：
- vuejs
- element-ui
- axios

## 开始使用

1. **克隆项目**

```bash
git clone git@github.com:jackyfzh/spring_vue_simple_blog.git
cd spring_vue_simple_blog
```

2. **启动后端**

在`idea`或`eclipse`中打开项目，运行`blog-api/src/main/java/com/example/blog/BlogApiApplication.java`启动文件。

3. **启动数据库**

这一步，伙伴们需要记得安装最新版mysql，并在`blog-api/src/main/resources/application.yml`中做好数据库密码的修改。
随后，新建一个数据库，并执行[blog.sql](https://github.com/jackyfzh/spring_vue_simple_blog/blob/Version-1.0.0/blog.sql)文件中的命令。
最后，命令行输入`mysql -u root -p`启动数据库。

4. **启动前端**

接下来，在`blog-admin`下输入命令：
```bash
npm install
npm run serve
```
随后，**访问localhost:4001**即可看到博客后台系统。

## 更快捷的方法
当然可以有更快捷的启动方法（~~我现在说应该不算太晚吧~~）

在[这个页面](https://github.com/jackyfzh/spring_vue_simple_blog/releases/tag/Version-1.0.0)下载`blog-v1.jar`文件，随后在该目录下输入命令`java -jar blog-v1.jar`，即可启动后端项目。当然，前端项目以及数据库的启动**与前面一样**。

## 协议

**本项目完全遵循`MIT-License`。供学习用途，转载须注明原作者，谢谢。**

## 最后

最后，**感谢**为我提供支持的**大佬和开发者们**。
如果你喜欢这个项目，**欢迎Star和Fork**。如果有问题，也可以**提Issue或Pr**联系我。
