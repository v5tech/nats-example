# nats-example

## 示例演示

集群环境搭建

```bash
gnatsd -a 10.61.8.7 -p 4222 -m 8222 -cluster nats://10.61.8.7:5222 -routes nats://10.61.8.10:5222,nats://10.61.8.18:5222 -DV

gnatsd -a 10.61.8.10 -p 4222 -m 8222 -cluster nats://10.61.8.10:5222 -routes nats://10.61.8.7:5222,nats://10.61.8.18:5222 -DV

gnatsd -a 10.61.8.18 -p 4222 -m 8222 -cluster nats://10.61.8.18:5222 -routes nats://10.61.8.7:5222,nats://10.61.8.10:5222 -DV
```

访问监控观察集群状态

http://10.61.8.7:8222

http://10.61.8.10:8222

http://10.61.8.18:8222

![](Screenshots/20180111103332.png)
![](Screenshots/20180111103301.png)
![](Screenshots/20180111103310.png)
![](Screenshots/20180111103321.png)


## Linux远程部署

```bash
mvn package
```

MAVEN本地打包远程部署日志输出
```bash
"C:\Program Files\Java\jdk1.8.0_144\bin\java" -Dmaven.multiModuleProjectDirectory=C:\Users\Administrator\Desktop\nats-example\spring-boot-nats-example -Dmaven.home=D:\maven -Dclassworlds.conf=D:\maven\bin\m2.conf -Didea.launcher.port=61243 "-Didea.launcher.bin.path=C:\Program Files\JetBrains\IntelliJ IDEA 2017.2.3\bin" -Dfile.encoding=UTF-8 -classpath "D:\maven\boot\plexus-classworlds-2.5.2.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2017.2.3\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMainV2 org.codehaus.classworlds.Launcher -Didea.version=2017.2.6 -s D:\maven\conf\settings.xml package
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building spring-boot-nats-example 1.0
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ spring-boot-nats-example ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ spring-boot-nats-example ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ spring-boot-nats-example ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory C:\Users\Administrator\Desktop\nats-example\spring-boot-nats-example\src\test\resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ spring-boot-nats-example ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-surefire-plugin:2.18.1:test (default-test) @ spring-boot-nats-example ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:2.6:jar (default-jar) @ spring-boot-nats-example ---
[INFO] Building jar: C:\Users\Administrator\Desktop\nats-example\spring-boot-nats-example\target\spring-boot-nats-example-1.0.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.5.6.RELEASE:repackage (default) @ spring-boot-nats-example ---
[INFO] 
[INFO] --- wagon-maven-plugin:1.0:upload-single (upload-deploy) @ spring-boot-nats-example ---
[INFO] Uploading: C:\Users\Administrator\Desktop\nats-example\spring-boot-nats-example\target\spring-boot-nats-example-1.0.jar scp://10.61.8.18/opt/spring-boot-nats-example-1.0.jar
[INFO] 
[INFO] --- wagon-maven-plugin:1.0:sshexec (upload-deploy) @ spring-boot-nats-example ---
[INFO] sshexec: pkill -f spring-boot-nats-example-1.0.jar


[INFO] sshexec: nohup java -jar /opt/spring-boot-nats-example-1.0.jar > /opt/nohup.out 2>&1 &


[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 5.411 s
[INFO] Finished at: 2018-01-11T12:07:46+08:00
[INFO] Final Memory: 28M/325M
[INFO] ------------------------------------------------------------------------

Process finished with exit code 0
```

## 程序运行控制台

![](Screenshots/20180111104002.png)

程序运行起来首先订阅了`some.nats.subject`,然后每隔5秒向`some.nats.subject`发布消息。从日志中可以看出`NATS`会尝试自动重连.

## 参考文章

https://nats.io/documentation/server/gnatsd-cluster/

https://github.com/cloudfoundry-community/java-nats/tree/master/client

https://nats.io/documentation/server/gnatsd-cluster/

http://gomqtt.taitan.org/user-guide/install/#nats

http://www.cnblogs.com/liang1101/category/975332.html

http://blog.csdn.net/chszs/article/category/5987039

https://github.com/ChinaSilence/spring-boot-starter-nats

http://xxgblog.com/2015/10/23/wagon-maven-plugin/