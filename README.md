# springboot-demo-inner

使用springboot中的一些demo

# elasticsearch-demo

测试springboot接入es

# jooq-demo

测试springboot接入jooq

# mbatis-plus-demo

测试springboot接入mybatis-plus

# mybatis-demo

测试springboot接入mybatis

# spring-demo

## 1、过滤器

### 1、1 过滤器的使用

实现Filter，并实现doFilter方法。

在方法filterChain.doFilter(servletRequest, servletResponse);的前后执行的代码即是在控制器前后执行的逻辑。

例如：

```java
@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uuid = IdUtil.simpleUUID();
        System.out.println("requestId=" + uuid);
        MDC.put("requestId", uuid);
        try {
            System.out.println("xss过滤器执行前的逻辑运行");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("xss过滤器执行后的逻辑运行");
        } finally {
            MDC.remove("requestId");
        }
    }
```

### 1、2 注册过滤器

实现过滤器方法之后，需要把过滤器注册到容器中才会生效。注册的方式有三种

在Spring Boot中注册过滤器主要有以下几种方式：

1. **使用`@WebFilter`注解**：
   - 这种方式适用于Servlet API项目。
   - 通过在过滤器类上使用`@WebFilter(urlPatterns = "/*")`来指定该过滤器应当拦截哪些URL模式。
   - 需要在项目启动类上使用`@ServletComponentScan`注解来扫描并注册使用了`@WebFilter`的过滤器。
   - 这种方式的限制是无法通过`@Order`注解来指定过滤器的优先级。
2. **使用`FilterRegistrationBean`**：
   - 这是Spring Boot推荐的方式。
   - 通过创建一个配置类，并在该类中定义一个`@Bean`方法，该方法返回一个`FilterRegistrationBean`实例。
   - 在`FilterRegistrationBean`实例中设置过滤器实例和需要拦截的URL模式。
   - 可以通过多个`FilterRegistrationBean`来注册多个过滤器，并可以设置它们的优先级。
3. **使用`@Component`和`@Order`注解**：
   - 可以将过滤器类标记为`@Component`，这样Spring Boot就会自动扫描并注册它。
   - 使用`@Order`注解可以指定过滤器的优先级。
   - 这种方式需要在过滤器类中实现`Filter`接口，并重写`doFilter`方法。

总结：

- 在Spring Boot中注册过滤器主要有三种方式：使用`@WebFilter`注解、使用`FilterRegistrationBean`以及使用`@Component`和`@Order`注解。
- 使用`@WebFilter`注解需要配合`@ServletComponentScan`使用，但无法指定优先级。
- 使用`FilterRegistrationBean`是Spring Boot推荐的方式，它可以灵活地配置过滤器的优先级和拦截的URL模式。
- 使用`@Component`和`@Order`注解可以方便地将过滤器注册为Spring组件，并通过`@Order`指定优先级。

详细示例请参考对应项目中的模块

## 2、拦截器

### 2.1 拦截器原理

在Spring Boot中，拦截器（Interceptor）是一种强大的工具，用于在某个请求处理过程中的关键点上执行代码，通常用于执行安全检查、日志记录、事务管理等。拦截器在Spring MVC框架中，围绕着`DispatcherServlet`的请求处理来执行，允许在请求处理之前、请求处理之后以及请求完成后、视图渲染之后执行自定义的操作。

1. **请求开始时**，`DispatcherServlet`会查找匹配的拦截器，并按照它们在配置中的声明顺序进行调用。
2. 每个拦截器的`preHandle`方法会被依次调用。如果其中任何一个`preHandle`方法返回`false`，则请求将被中断，后续的拦截器和控制器将不会被执行。
3. 如果所有的`preHandle`方法都返回`true`，则请求会继续向下执行，直到达到对应的控制器方法。
4. 控制器方法执行完毕后，会执行所有拦截器的`postHandle`方法，这个方法的调用顺序和`preHandle`是相反的。
5. 最后，当请求完全处理完毕，也就是视图渲染之后，会执行`afterCompletion`方法。

### 2.2 使用场景

- **身份验证和授权**：验证用户身份，并检查用户是否有权限访问请求的资源。
- **日志记录**：记录请求和响应的信息，以便于监控和调试。
- **性能监控**：记录请求处理的时间，分析系统性能。
- **事务处理**：在请求开始和结束时进行事务的开启和提交或回滚。
- **请求参数校验**：在请求到达控制器之前校验请求参数的合法性。

### 2.3 拦截器使用

实现HandlerInterceptor，并实现接口。

示例：

```java
@Component  
public class LoggingInterceptor implements HandlerInterceptor {  

    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {  
        System.out.println("Request URI: " + request.getRequestURI());  
        return true;  
    }  

    @Override  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {  
        System.out.println("Request processed");  
    }  

    @Override  
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {  
        System.out.println("Request completed");  
    }  
}
```

### 2.4 注册拦截器

如上示例，程序启动后并不会拦截请求，还需要将拦截器注册。注册的方式如下：

在Spring Boot中，拦截器的注册方式主要有两种：

1. 实现`WebMvcConfigurer`接口并重写`addInterceptors`方法
2. 使用注解`@Configuration`和`@Bean`配合`InterceptorRegistry`

#### 方法一：实现`WebMvcConfigurer`接口

**优点**：

- 可以精细地控制拦截器的添加顺序。
- 可以在同一个配置类中集中管理多个Spring MVC的配置。

**缺点**：

- 需要创建一个配置类，相对繁琐。

在这个示例中，`MyInterceptor`是自定义的拦截器类，实现了`HandlerInterceptor`接口。`WebConfig`类实现了`WebMvcConfigurer`接口，并重写了`addInterceptors`方法来注册拦截器。`addPathPatterns`方法用于指定拦截器要拦截的路径。

#### 方法二：使用`@Configuration`和`@Bean`

**优点**：

- 配置简洁，通过声明Bean的方式即可注册拦截器。

**缺点**：

- 对于拦截器的顺序控制不如实现`WebMvcConfigurer`直观。
- 如果需要注册多个拦截器，可能需要创建多个配置类或使用多个`@Bean`方法，稍显混乱。

在这个示例中，`MyInterceptor`同样是自定义的拦截器类。`InterceptorConfig`类是一个配置类，其中定义了两个Bean：一个是自定义的拦截器实例，另一个是`WebMvcConfigurer`的匿名内部类实现，用于注册拦截器。

总结

两种方法都可以成功注册拦截器，选择哪种方法取决于项目的具体需求和开发团队的偏好。如果需要更精细的控制，推荐使用实现`WebMvcConfigurer`接口的方式；如果追求简洁性，可以使用`@Configuration`和`@Bean`的方式。在实际开发中，实现`WebMvcConfigurer`接口的方式更为常见和推荐。

示例请参考对应项目中的过滤器板块

## 3. 拦截器和过滤器比较

### 3.1 执行顺序

请求过来时，顺序如下：

请求：过滤器1->过滤器2->拦截器1->拦截器2->控制器（业务代码）->拦截器2->拦截器1->过滤器2->过滤器1->客户端

## 4. 配置文件加密

[Springboot-配置文件中敏感信息的加密：三种加密保护方法比较-CSDN博客](https://blog.csdn.net/wtwcsdn123/article/details/138801475)

## 5. 循环依赖问题

如果BeanA依赖BeanB，BeanB依赖BeanA。

采用构造器注入会报错，提示循环依赖问题。

如果采用属性注入，不会报循环依赖问题。

采用setter注入，不会报循环依赖（A采用构造器注入，B采用setter注入）

### Spring处理循环依赖的方法

在Spring中解决循环依赖问题主要采用了以下几种策略，其中包括利用三级缓存机制。下面将分点详细解释这些策略：

1. **构造器参数循环依赖的解决**：
   - Spring容器会检测正在创建的Bean是否处于“当前创建Bean池”中，如果是，则表明存在循环依赖，并会抛出`BeanCurrentlyInCreationException`异常。
   - 对于已经创建完毕的Bean，将会从“当前创建Bean池”中清除。
2. **三级缓存机制**：
   - Spring为了解决循环依赖问题，确实采用了三级缓存策略。
   - **一级缓存**：存放已经完全初始化好的Bean实例（`singletonObjects`）。
   - **二级缓存**：存放原始的Bean对象，用于解决循环依赖（`earlySingletonObjects`）。
   - **三级缓存**：存放一个ObjectFactory对象，该对象用于创建一个Bean实例（`singletonFactories`）。
   - 当发生循环依赖时，Spring会利用这三级缓存来解决。具体流程是：当一个Bean正在创建过程中，会先将其ObjectFactory放入三级缓存中，如果遇到循环依赖，就可以利用这个ObjectFactory来提前暴露一个尚未完全初始化的Bean来解决依赖关系，并最终完成Bean的创建。
3. **setter注入和属性注入**：
   - 与构造器注入不同，setter注入和属性注入允许在Bean实例化之后设置依赖关系。
   - 这意味着即使存在循环依赖，Bean也可以先被实例化，然后再通过setter方法或直接设置属性值来解决依赖关系，从而避免循环依赖问题。

需要注意的是，Spring解决循环依赖的前提条件是：

- 相互依赖的Bean必须是单例的，因为对于原型（prototype）范围的Bean，每次请求都会创建一个新的实例，这会导致循环依赖无法解决。
- 不能仅通过构造函数注入来解决循环依赖，因为这种情况下会产生死锁。

综上所述，Spring通过结合使用“当前创建Bean池”检测、三级缓存机制以及支持setter和属性注入等多种方式来解决循环依赖问题。

# proj-admin

用于一些项目实战应用
## 1.自定义实现动态定时任务
基于TaskScheduler创建ScheduledFuture，创建ScheduledFuture的时候需要指定cron表达式，这个时候可以去数据库里查询初始化定时任务。
当需要变更时，修改数据库里的cron表达式。然后程序可以设置每个30s去数据库查询一下，如果cron表达式发生变化，那就重新初始化定时任务。
`futureTask = taskScheduler.schedule(this::executeTask, new CronTrigger(cronExpression));`。
如果一个类里有多个定时任务（方法），那就需要借助Map集合来实现，即每个定时任务创建一个ScheduledFuture。
详情请参考代码：com.weitw.study.sbt.schedules.DynamicScheduledTaskService、com.weitw.study.sbt.schedules.DynamicScheduled2TaskService、com.weitw.study.sbt.schedules.DynamicScheduled3TaskService

# swagger-demo

测试springboot接入swagger

# utils-demo

工具类
