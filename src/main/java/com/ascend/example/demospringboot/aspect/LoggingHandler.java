//package com.ascend.example.demospringboot.aspect;
//
//import java.util.Arrays;
//import java.util.Enumeration;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//@Aspect
//@Component
//public class LoggingHandler {
//    private static final Logger log = LogManager.getLogger(LoggingHandler.class);
//
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
//    public void controller() {
//    }
//
//    @Pointcut("execution(* *.*(..))")
//    protected void allMethod() {
//    }
//
//    @Pointcut("execution(public * *(..))")
//    protected void loggingPublicOperation() {
//    }
//
//    @Pointcut("execution(* *.*(..))")
//    protected void loggingAllOperation() {
//    }
//
//    @Pointcut("within(com.ascend.example.*)")
//    private void logAnyFunctionWithinResource() {
//    }
//
//    //before -> Any resource annotated with @Controller annotation
//    //and all method and function taking HttpServletRequest as first parameter
//    @Before("controller() && allMethod() && args(..,request)")
//    public void logBefore(JoinPoint joinPoint, HttpServletRequest request) {
//
//        System.out.println("Entering in Method :  " + joinPoint.getSignature().getName());
//        System.out.println("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
//        System.out.println("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
//        System.out.println("Target class : " + joinPoint.getTarget().getClass().getName());
//
//        if (null != request) {
//            System.out.println("Start Header Section of request ");
//            System.out.println("Method Type : " + request.getMethod());
//            Enumeration headerNames = request.getHeaderNames();
//            while (headerNames.hasMoreElements()) {
//                String headerName = headerNames.nextElement().toString();
//                String headerValue = request.getHeader(headerName);
//                System.out.println("Header Name: " + headerName + " Header Value : " + headerValue);
//            }
//            System.out.println("Request Path info :" + request.getServletPath());
//            System.out.println("End Header Section of request ");
//        }
//
//    }
//    //After -> All method within resource annotated with @Controller annotation
//    // and return a  value
//    @AfterReturning(pointcut = "controller() && allMethod()", returning = "result")
//    public void logAfter(JoinPoint joinPoint, Object result) {
//        String returnValue = this.getValue(result);
//        System.out.println("Method Return value : " + returnValue);
//    }
//    //After -> Any method within resource annotated with @Controller annotation
//    // throws an exception ...Log it
//    @AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
//        System.out.println("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
//        System.out.println("Cause : " + exception.getCause());
//    }
//    //Around -> Any method within resource annotated with @Controller annotation
//    @Around("controller() && allMethod()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        long start = System.currentTimeMillis();
//        try {
//            String className = joinPoint.getSignature().getDeclaringTypeName();
//            String methodName = joinPoint.getSignature().getName();
//            Object result = joinPoint.proceed();
//            long elapsedTime = System.currentTimeMillis() - start;
//            System.out.println("Method " + className + "." + methodName + " ()" + " execution time : "
//                    + elapsedTime + " ms");
//
//            return result;
//        } catch (IllegalArgumentException e) {
//            System.out.println("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
//                    + joinPoint.getSignature().getName() + "()");
//            throw e;
//        }
//    }
//    private String getValue(Object result) {
//        String returnValue = null;
//        if (null != result) {
//            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
//                returnValue = ReflectionToStringBuilder.toString(result);
//            } else {
//                returnValue = result.toString();
//            }
//        }
//        return returnValue;
//    }
//}
