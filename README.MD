# 科研项目管理系统
#### 系统构架与环境
+ Java 7
+ Tomcat 7
+ Mysql 5.6

----
+ Jersey 2.13
+ Snaker 2.5.1
+ spring 4.1.2.RELEASE
+ hibernate 3.6.10.Final

----
采用Maven构建  

----
数据库配置  
+ 新建一个`database.properties`文件，放在`src/main/resources`目录
+ 复制`database.properties.example`里的内容，并根据本机情况进行修改

- - -


#### TODO
- [ ] API权限控制
- [ ] 任务结束触发器
- [x] 帐号维护
- [x] 个人信息维护
- [x] 新期刊添加
- [ ] 数据导入导出
- [ ] etc

#### Bugs
- [ ] 论文编辑界面上传文件不显示
- [ ] 论文编辑界面确认按钮一直显示