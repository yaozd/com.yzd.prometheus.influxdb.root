## 参考
- [https://github.com/bigjar/actuator-demo](https://github.com/bigjar/actuator-demo)
- []()
- []()

> **从web服务内部停止并关闭springboot项目** 
- [从web服务内部停止并关闭springboot项目](https://blog.csdn.net/jiewolf/article/details/78476537)

```
// 太麻烦了,我说种简单的办法
public class Application implements xxx{
	public static ConfigurableApplicationContext context;
	public static void main(String[] args) {
		context = SpringApplication.run(Application.class, args);
	}
}
 
// 在需要关闭程序的地方调用
Application.context.close();
 
// END
```