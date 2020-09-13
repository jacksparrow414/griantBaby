# griantBaby(手把手教巨婴开发完整的前后端分离项目)

[![](https://badgen.net/badge/License/MIT/blue)](https://opensource.org/licenses/MIT) [![](https://badgen.net/badge/Maven/3.0+/green)](https://maven.apache.org/) [![](https://badgen.net/badge/JDK/1.8+/orange)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) [![](https://badgen.net/badge/SpringBoot/2.0+/pink)](https://spring.io/projects/spring-boot) [![](https://badgen.net/badge/Mysql/5.7+/purple)](https://www.mysql.com/) [![](https://badgen.net/badge/Shiro/1.5/yellow)](http://shiro.apache.org/) [![](https://badgen.net/badge/Mybatis-plus/3.0+/green)](https://mp.baomidou.com/) [![](https://badgen.net/badge/author/jacksparrow414,Canon4G/cyan?list=|)](https://github.com/jacksparrow414)

## 简介:
此项目由Java8、SpringBoot、Mybatis、Mybatis-plus、Mysql、Lambok、Shiro搭建而成的后端框架
前端代码参阅[这里](https://github.com/jacksparrow414/Vue)

## 项目结构:
- |——code                                                *项目顶级目录*
  - |——sql                                              *项目所有的表结构 SQL都放在这里*
  - |——nginx                                            *nginx配置示例文件nginx.conf*
  - |——vue/dist                                         *前端打好的包,如果不想看前端代码，直接用这个包即可*
  - |——src
     - |——main
         - |——java
           - |——..code(package)                         *下面都为包级别*
           - |——CodeAppication.java                  *项目主启动类*
           - |——common                               *项目维护的所有常量放在此包下*
           - |——config                               *配置类*
           - |——exception                            *项目全局异常处理类*
           - |——generator                            *Mybatis-plus提供的代码生成器,MysqlGenerator*
           - |——modules                              *放置所有和业务相关的Controller、Entity、Dao、Service,根据不同模块进行划分*
           - |——shiro                                *shiro的鉴权操作等*
           - |——task                                 *定时任务*
           - |——mq                                   *消息队列*
           - |——utils                                *支持本项目的工具类*
       - |——resource
          - |——mapper                                  *所有*Mapper.xml文件的位置*
          - |——application-*.yml                       *项目的主配置文件，根据不同环境可切换*
          - |——banner.txt                              *项目启动界面配置文字*
    - |——test                                          *测试集合*
       - |——..*Test                                    *所有*Test测试用例在这里*
    - |——pom.xml                                       *项目的pom文件*

## 简要说明：
1. 所有表和基础POJO类都由项目中的**MysqlGenerator**生成，减少工作量，可快速进行开发。
2. 计划所有业务的操作都用**Mybatis-plus**书写，除必要情况，采取手写SQL。
3. 目前本项目已经所有已实现的接口都会有**shiro鉴权**操作，并且集成了jwt(token),保证接口的基本安全。
4. 由于定时任务和消息队列还没有密集使用，代码里涉及到定时任务和消息队列的代码为*demo*。
## 建议：
1. 建议所有接口验证使用[Yapi](http://yapi.demo.qunar.com/)或者PostMan进行接口验证
2. Yapi高级操作可参阅[这里](https://www.bookstack.cn/read/YApi-zh/README.md)
3. shiro文档如果看不懂，可参阅[这里](https://blog.csdn.net/dghkgjlh/article/details/88725508)的系列文章
## 部署
1. 本地安装好nginx，将项目的nginx文件夹下的nginx.conf替换默认的nginx.conf
2. 将项目vue下的dist文件夹放在自定义的位置上，如D盘
3. 修改第1步中的nginx.conf下的 /baby下的root，路径替换为第2步中保存的位置
4. 验证nginx.conf的正确性并启动，访问本地80端口,查看是否显示出项目的首页
5. 本地建立名字为baby的MySQL数据库，并运行sql文件夹下的sql文件
6. 修改appincation-dev.yml中的数据库项中的用户名、密码切换成自己的
7. 启动后端项目
8. 从首页的位置点击登录按钮，即可进入系统
## 参与贡献
一起参与开源，十分期待你的加入!!!
[如何参与开源?](https://blog.csdn.net/dghkgjlh/article/details/106955241)  
提交代码之前，请确保CheckStyle检查通过、确保所有单元测试通过
## 特别感谢
[![JetBrains对本项目的支持](https://github.com/jacksparrow414/griantBaby/blob/master/logo-text.png)]( https://www.jetbrains.com/?from=https://github.com/jacksparrow414/griantBaby)
