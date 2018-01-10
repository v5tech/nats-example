### 使用方式
1、获取代码
```
git clone https://github.com/ChinaSilence/spring-boot-starter-nats.git
```

2、安装到本地 Maven 仓库
```
mvn clean install
```

3、在项目中添加依赖
```xml
<!-- Nats 消息中间件 -->
<dependency>
    <groupId>com.anoyi</groupId>
    <artifactId>spring-boot-starter-nats</artifactId>
    <version>1.0-RELEASE</version>
</dependency>
```

4、配置 Nats 相关参数
```yaml
# 默认 nats://127.0.0.1
spring:
  nats:
    urls:
      - nats://192.168.99.102:30000
      - nats://192.168.99.102:30001
      - nats://192.168.99.102:30002
```

5、发布订阅消息
```java
@Autowired
private nats.client.Nats nats;

...

// 订阅消息
public void subscribe() {
	nats.subscribe("some.nats.subject", new MessageHandler() {
	@Override
		public void onMessage(Message message) {
			System.out.println("Received: " + message);
		}
	});
}

// 发布消息
public void send() {
	nats.subscribe("some.nats.subject", "message content");
}
```
