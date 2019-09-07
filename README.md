# griantBaby

# # 简介:
此项目由Java8、SpringBoot、Mybatis、Mybatis-plus、Mysql、Lambok、Shiro搭建而成的后端框架

# # 项目结构:
# # # 目前结构如下：
- |——code                                                #项目顶级目录
  - |——sql                                              #项目所有的表结构 SQL都放在这里>
  - |——src
     - |——main
         - |——java
            - |——..code(package)                         #下面都为包级别
           - |——CodeAppication.java                  #项目主启动类
           - |——common                               #项目维护的所有常量放在此包下
           - |——config                               #配置类
           - |——exception                            #项目全局异常处理类
           - |——generator                            #Mybatis-plus提供的代码生成器,MysqlGenerator
           - |——modules                              #放置所有和业务相关的Controller、Entity、Dao、Service,根据不同模块进行划分
           - |——shiro                                #shiro的鉴权操作等
           - |——task                                 #定时任务
           - |——mq                                   #消息队列
           - |——utils                                #支持本项目的工具类
       - |——resource
          - |——mapper                                  #所有*Mapper.xml文件的位置
          - |——application-*.yml                       #项目的主配置文件，根据不同环境可切换
          - |——banner.txt                              #项目启动界面配置文字
    - |——test                                          #测试集合
       - |——..*Test                                    #所有*Test测试用例在这里
    - |——pom.xml                                       #项目的pom文件

# # 简要说明：
1. 所有表和基础POJO类都由项目中的MysqlGenerator生成，减少工作量，可快速进行开发。
2. 计划所有业务的操作都用Mybatis-plus书写，除必要情况，采取手写SQL。
3. 目前本项目已经所有已实现的接口都会有shiro鉴权操作，并且集成了jwt(token),保证接口的基本安全。
4. 由于定时任务和消息队列还没有密集使用，代码里涉及到定时任务和消息队列的代码为demo。
# # 建议：
1. 建议所有接口验证使用[Yapi](http://yapi.demo.qunar.com/)或者PostMan
