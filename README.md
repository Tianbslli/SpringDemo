## 项目简介

此项目利用AOP/（Filter结合Interceptor）实现拦截并打印request和response信息
#### v1（～2020／03／23）

>每5s调用一次timeTelling接口，AOP拦截并打印出request和response的信息日志。

+ 1 使用AOP做拦截；
+ 2 自定义PrintLog注解来标记需要被拦截打印日志的方法，也可直接用包名做筛选；
+ 3 用@Scheduled实现定时任务，5s访问一次timeTelling方法

#### v2 (~2020/03/25)

>使用postMan调用GET方法(timeTelling)和POST方法(timeTellingAsRequire)，
>Filter和Interceptor依次拦截并打印出request和response的信息日志。

+ 1 使用Filter和Interceptor做拦截；
+ 2 丰富并规范项目结构；
+ 3 日志使用log4j2框架
+ 4 使用流读取ServletRequest/ServletResponse的body（也可以用AOP或Advice便捷获取）
+ 5 使用HttpServletRequestWrapper实现对httpServletRequest的装饰，以解决一个request只能调用依次getInputStream()的问题；response基本同理，额外用到了ByteArrayOutputStream来flush数据。

踩了挺多坑，但也学到了很多之前简单地写个小demo，以debug看看源码所没有注意到的细节，后续会写博客整理一波
