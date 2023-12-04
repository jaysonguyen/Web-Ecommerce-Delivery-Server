package com.example.deliveryecommercebackend.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

public class LoginInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before method " + invocation.getMethod().getName());
        var check = invocation.getArguments();
        System.out.println(check[0]);
        Object result = invocation.proceed();
        System.out.println("After method " + invocation.getMethod().getName());
        return result;
    }
}
