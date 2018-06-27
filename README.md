####更新记录
-下一步计划

为mongodb远程访问加入密码
完善后台的首页监控功能,可查看当前虚拟机运行情况,当前登录用户,mongodb概略情况...
网站流量统计分析系统 友盟
极光 推送
分享插件: JiaThis  百度分享

添加最牛程序员简历到关于页面:已嵌入
退出登录后页面跳转不完美,依然显示退出登录按钮:已完成
如果登录用户为admin则跳转到后台管理页面(完善的做法需要为后台管理页面都设置访问权限),可以就把用户名当成权限,只需要一个admin的后台管理权限即可:已完成
将MongoDB的后台管理完善出来,已完成

#### 分支说明

- master : 单机版，所有用户都连接到同一服务，单个服务资源有限，不支持大规模用户同时在线
- cluster: 集群版，支持大规模用户同时在线、互相通信

#### 技术栈说明

**单机版**

- Spring Boot / Spring Security / Spring WebSocket / MongoDB

**集群版**

- Spring Boot / Spring Security / Spring WebSocket / MongoDB / Nats
- Docker Swarm

#### 单机版开发文档

**1、运行服务【IDEA 方式】**

启动 MongoDB，并在 `application.yml` 中配置， 运行 Application 即可启动应用

**2、运行服务【Maven 方式】**

启动 MongoDB，并在 `application.yml` 中配置， 执行以下命令：
```
mvn spring-boot:run
```

**3、运行服务【Docker 方式】**

首先，打包应用并构建镜像：
```
mvn clean package

docker bulid -t anoyi-im .
```

然后，使用 Docker 启动 MongoDB：
```
docker run -d --name mongo -p 27017:27017 mongo
```

最后，启动应用：
```
docker run -d --name anoyi-im --link mongo:mongo -p 8080:8080 anoyi-im
```

**4、运行服务【Docker-Compose 方式】**
首先，打包应用：
```
mvn clean package
```

然后，启用应用
```
docker-compose up
```

<1>首次部署当前程序需要在对应的文件夹中执行以下命令

a.启动程序 nohup java -jar im.jar &

b.退出 ctrl + c

c.查看日志 tail -500f nohup.out

<2>非首次部署当前程序需要在对应的文件夹中执行以下命令

a.捕获上一个版本程序的进程 ps - ef|grep im.jar 

b.杀死对应的进程 kill 进程号 一般不要使用kill -9

c.启动程序 nohup java -jar im.jar & 

d.退出 ctrl + c 

e.查看日志 tail -500f nohup.out

#### 联系作者
- 微信 17673125001
- 邮箱 gentoo111@163.com