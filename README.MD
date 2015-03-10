# 科研项目管理系统
## 系统构架与环境
+ Java 7
+ Tomcat 7
+ Mysql 5.6
----
+ Jersey 2.13
+ Snaker 2.5.1
+ spring 4.1.2.RELEASE
+ hibernate 3.6.10.Final
----
采用Maven构建。

- - -

## 代码介绍
### 工作流引擎相关
#### 工作流基本设定
工作流模型为process  
每次启动一个工作流，我们称之为工作流订单order  
每个工作流由很多任务节点组成，为task  
一个工作流模型可以启动无数次，生成无数个工作流订单  
一个工作流订单有若干个任务节点  

#### request参数定义
| key | value |备注|
|--------|--------|-------|
| WF-USER|参与任务的人|用户ID|
|WF-TaskId|任务ID||
|WF-Process|流程ID||
|WF-NextTaskActor|下一任务参与者|用逗号分开|
|DecByCol|学院决定|boolean|
|DecByDep|管理部门决定|boolean|

### TODO
- [ ] API权限控制
- [ ] 任务结束触发器
- [ ] 帐号维护
- [ ] 个人信息维护
- [ ] 数据导入导出
- [ ] etc