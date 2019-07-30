<p align="center">
  <a target="_blank" href="https://nacos.io/en-us/"><img src="https://img.shields.io/badge/Nacos-0.2.2-blue.svg" alt="Nacos"></a>
  <a><img src="https://img.shields.io/badge/Spring%20Cloud-%20Greenwich.SR1-brightgreen.svg" alt="SpringCloud"></a>
  <a><img src="https://img.shields.io/badge/Spring%20Boot-2.1.4-brightgreen.svg" alt="SpringBoot"></a>
  <a><img src="https://img.shields.io/badge/Redis-orange.svg"></a>
  <a><img src="https://img.shields.io/badge/RabbitMq-orange.svg"></a>
  <a target="_blank" href="https://www.iviewui.com/docs/guide/install"><img src="https://img.shields.io/badge/iview-3.1.3-brightgreen.svg?style=flat-square" alt="iview"></a>
  <a><img src="https://img.shields.io/badge/vue-2.5.10-brightgreen.svg?style=flat-square" alt="vue"></a>
  <a><img src="https://img.shields.io/npm/l/express.svg" alt="License"></a>
</p>  

## 微服务开放平台 3.0.0(重构版) 更快、更新、更全面
#### 开源不易，请随手给个Star! 感谢支持！

#### 简介
搭建基于OAuth2的开放平台、为APP端、应用服务提供统一接口管控平台、为第三方合作伙伴的业务对接提供授信可控的技术对接平台
+ 分布式架构,统一配置中心,服务治理.fegin(RPC)内部调用,微服务管理开发更便捷!
+ 统一API网关、访问鉴权、接口管理、参数验签、接口调用更安全!
+ 深度整合SpringSecurity+Oauth2,统一认证协议、url级鉴权、更细粒度、灵活的ABAC权限控制!
+ 前后端分离方式开发应用，分工合作更高效!
+ 代码合理封装、简单易懂、简化开发流程!


<a target="_blank" href="http://www.openc.top">开发平台门户预览</a> 

<a target="_blank" href="http://admin.openc.top">运营管理后台预览</a>
+ 后台默认账号:admin 123456  
+ 后台测试账号:test 123456
+ SpringBootAdmin账号:sba 123456
#### 更新日志
     v-3.0.0 2019-07-29
        1. 修复springgateway已知bug
        2. docs/sql/data/20190729.sql 增加字段修改语句
    v-3.0.0 2019-07-19
        1. 增加open-cloud-generator-server 在线代码生成器
        2. docs/sql/data/日期.sql 增量数据
        
    v-3.0.0 2019-07-11 （重大更新） 
        1. 新增开发者管理
        2. 调整项目结构
        3. 优化ui交互方式
        4. 调整部分代码
        5. 升级方式 
            + 升级前注意对老数据进行备份
            + 重新导入common.propertis到配置中心
            + 重新执行 2019-07-19.sql oauth2.sql gateway.sql msg.sql 并手动删除无效表名
        
    v-2.1.0 2019-06-10 
        1. base_api表新增字段is_open是否公开访问: 0-内部的 1-公开的
        2. 更新base_api数据
        
    v-2.1.0 2019-05-26 （重大变更）
        1. 重新梳理base表结构和权限相关接口,解决用户和客户端动态分配权。 机制问题暂不支持用户动态分配角色,需重新登录获取最新角色
        2. 优化页面功能
        3. 升级nacos客户端版本.支持1.0.0以上版本
        5. 完善权限数据,去除外键约束.
        6. 升级方式更新ui和服务代码, 重新执行base.sql。手动删除无效表
        7. 移除app-admin模块 相关功能迁移到opencloud-auth-provider中
        
    v-2.0.0 2019-05-01
        1. 升级SpringCloud Greenwich.SR1,SpringBoot 2.1.4.RELEASE
        2. 重构项目结构
        3. 优化Zuul网关性能
        4. 增加官方SpringCloudGateway
        5. 迁移Gateway功能到base服务中
        6. 增加MybatisPlus
        7. 使用.yml代替.properties
        
    v-1.0.0 2019-03-18
        1. 重构项目结构
        2. 重构表结构
        3. 重构授权逻辑
        4. 提取公共配置,并迁移到Nacos配置中心
        5. 优化功能
        
#### 系统结构图

![拓扑图](/docs/拓扑图.jpg)  

#### 功能介绍
![功能介绍](/docs/功能介绍.png)  


#### 服务端源码
<a target="_blank" href="https://gitee.com/liuyadu">码云</a>  <a target="_blank" href="https://github.com/liuyadu/">github</a>  

#### vue后台UI源码
<a target="_blank" href="https://gitee.com/liuyadu/open-admin-ui">后台UI源码</a>

#### vue门户UI源码
<a target="_blank" href="https://gitee.com/liuyadu_open/open-portal-ui.git">门户UI源码</a>

#### 使用手册
<a target="_blank" href="https://gitee.com/liuyadu/open-cloud/wikis/pages">使用手册</a>  

#### 学习交流群 
交流群:760809808  
扫码进群： ![760809808](/docs/qq.png)  

#### 代码结构
``` lua
open-cloud
├── docs                               -- 文档及脚本
    ├── bin                            -- 执行脚本  
    ├── config                         -- 公共配置,用于导入到nacos配置中心   
    ├── sql                            -- sql文件
      ├── data                         -- 增量数据
     
├── components                         -- 公共组件
    ├── open-cloud-common-core         -- 提供微服务相关依赖包、工具类、全局异常解析等
    ├── open-cloud-common-starter      -- SpringBoot自动配置扫描
    ├── open-cloud-tenant-starter      -- 多租户模块,多数据源自动切换(完善中...)
    ├── open-cloud-java-sdk            -- 开放平台api集成SDK(完善中...)
       
├── platform                           -- 平台服务
    ├── open-cloud-api-spring-server   -- API开放网关-基于SpringCloudGateway[port = 8888](推荐）  
    ├── open-cloud-api-zuul-server     -- API开放网关-基于Zuul[port = 8888](功能完善）
    ├── open-cloud-base-client         -- 平台基础服务接口
    ├── open-cloud-base-server         -- 平台基础服务器[port=8233]
    ├── open-cloud-uaa-admin-server    -- 平台用户认证服务器[port = 8211]
    ├── open-cloud-uaa-portal-server   -- 门户开发者认证服务器[port = 7211]
    ├── open-cloud-generator-server    -- 在线代码生成服务器[port = 5555]
    
├── services                           -- 通用微服务
    ├── open-cloud-msg-client          -- 消息服务接口
    ├── open-cloud-msg-server          -- 消息服务器[port = 8266]
    ├── open-cloud-task-client         -- 任务调度接口
    ├── open-cloud-task-server         -- 调度服务器[port = 8501]
    ├── open-cloud-bpm-client          -- 工作流接口
    ├── open-cloud-bpm-server          -- 工作流服务器[port = 8255]
    ├── open-cloud-sba-server          -- SpringBootAdmin监控服务[port = 8849]
    ├── open-cloud-sso-ui-demo         -- SSO单点登录演示demo[port = 8849]
```

#### 快速开始
上手难度：★★★★

本项目基于springCloud打造的分布式快速开发框架. 需要了解SpringCloud,SpringBoot开发,分布式原理。

1. 准备环境
    + Java1.8  (v1.8.0_131+)
    + Nacos服务发现和注册中心(v1.0.0+) <a href="https://nacos.io/zh-cn/">阿里巴巴nacos.io</a>
    + Redis (v3.2.00+)
    + RabbitMq (v3.7+)（需安装rabbitmq_delayed_message_exchange插件 <a href="https://www.rabbitmq.com/community-plugins.html" target="_blank">下载地址</a>）
    + Mysql (v5.5.28+)
    + Maven (v3+)
    + Nodejs (v10.14.2+)
   
2. 执行创建数据库open-platform并执行sql脚本
    + docs/sql/oauth2.sql
    + docs/sql/base.sql
    + docs/sql/gateway.sql
    + docs/sql/msg.sql
    + docs/sql/quartz.sql && task.sql
      ...
    
3.  启动nacos服务发现&配置中心,新建公共配置文件 
    + 访问 http://localhost:8848/nacos/index.html 
    + 新建配置文件 ```
        + 项目目录/docs/config/db.properties >  db.properties
        + 项目目录/docs/config/rabbitmq.properties > rabbitmq.properties
        + 项目目录/docs/config/redis.properties > redis.properties
        + 项目目录/docs/config/common.properties  > common.properties
          
     如图:
     ![nacos](https://gitee.com/uploads/images/2019/0425/231436_fce24434_791541.png "nacos.png")
4. 修改主pom.xml  

    初始化maven项目
    ``` bush
        maven clean install
    ```
    本地启动,默认不用修改
    ``` xml
        <!--Nacos配置中心地址-->
        <config.server-addr>127.0.0.1:8848</config.server-addr>
        <!--Nacos配置中心命名空间,用于支持多环境.这里必须使用ID，不能使用名称,默认为空-->
        <config.namespace></config.namespace>
        <!--Nacos服务发现地址-->
        <discovery.server-addr>127.0.0.1:8848</discovery.server-addr>
    ```
    
5. 本地启动(按顺序启动)
     1. [必需]BaseApplication(平台基础服务)
     2. [必需]UaaAdminApplication(平台用户认证服务器)
     3. [必需]ApiGatewaySpringApplication(推荐)或ApiGatewayZuulApplication
     ```
        访问 http://localhost:8888
     ```
     4.[非必需]SpringBootAdmin(监控服务器)(非必需)
      ```
          访问 http://localhost:8849
      ```
      
6. 前端启动
    ```bush
        npm install 
        npm run dev
    ``` 
    访问 http://localhost:8080
    
7. 项目打包部署  
     maven多环境打包,并替换相关变量
   ```bush
     mvn clean install package -P {dev|test|online}
   ```
    项目启动
    ```bush
    ./docs/bin/startup.sh {start|stop|restart|status} open-cloud-base-server.jar
    ./docs/bin/startup.sh {start|stop|restart|status} open-cloud-uaa-admin-server.jar
    ./docs/bin/startup.sh {start|stop|restart|status} open-cloud-api-zuul-server.jar
    ```
    
#### 集成开发 
<a target="_blank" href="https://gitee.com/liuyadu/open-cloud/wikis/pages?sort_id=1396933&doc_id=256893">集成开发</a>

#### Oauth2使用说明
<a target="_blank" href="https://gitee.com/liuyadu/open-cloud/wikis/pages?sort_id=1396294&doc_id=256893">Oauth2</a>
