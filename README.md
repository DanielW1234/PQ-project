# PQ-project

>## 该PopQuiz智能课堂互动系统（Spring Boot + Vue3）项目详细软硬件环境与依赖版本清单

|组件|推荐版本/说明|
|----|-------------|
|JDK|21|
|Maven|3.6+|
|MySQL|8.0+|
|Node.js|18.x LTS|
|npm|9.x+|
|Nginx|1.18+|
|Tomcat|Spring Boot内置/9.0+|
|MyBatis|3.0.2（spring-boot版）|
|Hutool|5.8.21|
|Commons-io|2.11.0|
|PDFBox|2.0.29|
|POI|5.2.3|
|Vue|3.3.4|
|ECharts|5.4.3|

> 后端依赖文件见\AIcoding\Springboot\pom.xml\
> 前端依赖文件见\AIcoding\vueproject\package.json\
> 数据库创建代码见sql.txt

># 完整部署方法

1.数据库准备
-
- 安装MySQL 8.0+，创建数据库popquiz，导入建表SQL；
- 设置字符集为utf8mb4。

2.后端部署
-
- 安装JDK21，配置JAVA_HOME；
- 安装Maven 3.6+，配置MAVEN_HOME；
- 修改application.yml，配置数据库连接（账号密码）；
- 在Springboot目录下执行"mvn clean package"，或用IDEA直接运行SpringbootApplication.java；
- 访问http://localhost:8080/feedback/stats/测试接口。

3.前端部署
-
- 安装Node.js 18.x+和npm；
- 进入vueproject目录，执行"npm install" "npm run dev"；
- 访问http://localhost:5173。
