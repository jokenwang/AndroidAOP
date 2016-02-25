package com.example.jack.aspectjlibrary.aspect;

import android.util.Log;

import com.example.jack.aspectjlibrary.annotation.CustomTrace;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

/**
 * Created by jack on 16/2/25.
 */
@Aspect
public class CustomAspect {

    public static final String TAG = "CustomAspect";

    private static final String POINTCUT_METHOD =
            "execution(@com.example.jack.aspectjlibrary.annotation.CustomTrace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.example.jack.aspectjlibrary.annotation.CustomTrace *.new(..))";

    private String eventName;

    private String eventId;

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithCustomerTrace() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedCustomerTrace() {}

    @Before("methodAnnotatedWithCustomerTrace() || constructorAnnotatedCustomerTrace()")
    public void weaveJoinPoint(JoinPoint joinPoint)  {

        Method[] declaredMethods = joinPoint.getTarget().getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            CustomTrace customerTrace = method.getAnnotation(CustomTrace.class);
            if(customerTrace != null) {
                eventName= customerTrace.eventName();
                eventId=customerTrace.eventId();
                Log.e(TAG, "weaveJoinPoint: eventName = " + eventName + "  eventId = " + eventId );
            }
        }
    }
}
