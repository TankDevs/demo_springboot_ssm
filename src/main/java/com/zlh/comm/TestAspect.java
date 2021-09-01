package com.zlh.comm;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//参考：Spring-AOP之aspectj注解方式
//https://blog.csdn.net/zhu_tianwei/article/details/43352471
//@Aspect : 标记为切面类
//@Pointcut : 指定匹配切点集合
//@Before : 指定前置通知，value中指定切入点匹配
//@AfterReturning ：后置通知，具有可以指定返回值
//@AfterThrowing ：异常通知
//@Around 环绕通知 环绕通知的方法中一定要有ProceedingJoinPoint 参数,与Filter中的  doFilter方法类似
//注意：前置/后置/异常通知的函数都没有返回值，只有环绕通知有返回值

/**
 * @author zlh
 */
//使用自动注解的方式实例化并初始化该类
@Component
@Aspect
public class TestAspect {

//如果要设置多个切点可以使用 || 拼接
//    /**
//    *定义切点：PersonInfoController类的任意方法的执行
//     */
//    @Pointcut("execution(* com.zlh.demo_springboot_ssm.controller.PersonInfoController.*(..))")
//    private void personInfoAnyMathod(){
//
//    }

    /**
     * 定义切点：com.zlh.demo_springboot_ssm.controller包中任意方法的执行
     */
        @Pointcut("execution(* com.zlh.demo_springboot_ssm.controller.*.*(..))")
        private void anyMethod() {

    }

    @Before(value = "anyMethod()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("Before-前置通知");
    }

    @AfterReturning(value = "anyMethod()",returning = "result")
    public void doAfterReturning(JoinPoint joinPoint,Object result) {
        //后置通知只有正常返回时才有通知，异常则无通知
        System.out.println("AfterReturning-带参数-后置通知：");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        for (Object arg : obj) {
            HttpServletRequest req=(HttpServletRequest)arg;
            System.out.println("arg:id=" + ((HttpServletRequest) arg).getParameter("id"));
        }
        System.out.println(result.toString());

        System.out.println("======");

    }

    @AfterReturning(value = "anyMethod()")
    public void doAfterReturning() {
        //后置通知只有正常返回时才有通知，异常则无通知
        System.out.println("AfterReturning-无参数-后置通知");
    }


    @After("anyMethod()")
    public void doAfter() {
        System.out.println("After-最终通知");
    }

    @AfterThrowing(value = "anyMethod()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("AfterThrowing-异常通知");
    }

    @Around("anyMethod()")
    public Object doArund(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("进入环绕通知");
        System.out.println("目标类名称：" + joinPoint.getTarget().getClass().getName());
        System.out.println("方法名称：" + joinPoint.getSignature().getName());
        System.out.println("方法参数：" + joinPoint.getArgs());
        System.out.println("staticPart:" + joinPoint.getStaticPart().toShortString());
        System.out.println("kind:" + joinPoint.getKind());
        System.out.println("sourceLocation:" + joinPoint.getSourceLocation());
        // 执行该方法
        Object object = joinPoint.proceed();
        System.out.println("退出方法");
        return object;
    }


}
