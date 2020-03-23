## 项目简介

此项目利用AOP实现拦截并打印controller层请求日志
#### v1（～2020／03／23）

>每5s调用一次timeTelling接口，AOP拦截并打印出request和response的信息日志。

+ 1 使用AOP做拦截；
+ 2 自定义PrintLog注解来标记需要被拦截打印日志的方法，也可直接用包名做筛选；
+ 3 用@Scheduled实现定时任务，5s访问一次timeTelling方法



