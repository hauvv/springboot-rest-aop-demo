package com.ascend.example.demospringboot.aspect;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    }
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();

        final Object proceed = joinPoint.proceed();

        System.out.println("************ Entering in Method :  " + joinPoint.getSignature().getName());
        System.out.println("************ Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        Object[] signatureArgs = joinPoint.getArgs();
        for (Object signatureArg : signatureArgs) {
            System.out.println("************ Request parameter: " + signatureArg);
        }
        final long executionTime = System.currentTimeMillis() - start;
        System.out.println("************ Runtime: "+joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }
        // Print response data for all method
//    @AfterReturning(pointcut = "controller() && allMethod()", returning = "result")
//    public void logAfter(JoinPoint joinPoint, Object result) {
//        String returnValue = this.getValue(result);
//        System.out.println("************ Method Return value : " + returnValue);
//    }

    // Only print for create() method in UserController
    @AfterReturning(pointcut = "execution(* com.ascend.example.demospringboot.controller.UserController.create(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String returnValue = this.getValue(result);
        System.out.println("************ Method Return value : " + returnValue);
    }

    @AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println("************ An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
        System.out.println("************ Cause : " + exception.getMessage());
    }
    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }
}
