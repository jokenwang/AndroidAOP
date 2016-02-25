package com.example.jack.aspectjlibrary.aspect;

import android.util.Log;

import com.example.jack.aspectjlibrary.StopWatch;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by jack on 16/2/24.
 * Aspect 切片
 */
@Aspect
public class BeforeMethodAspect {

    public static final String TAG = "BeforeMethodAspect";

    private static final String ASPECT_METHOD_CLICK = "execution( * android.view.View.OnClickListener .*(..))";
    private static final String ASPECT_AROUND = "";

    private static final String ASPECT_METHOD_ONCREATE = "execution(* android.app.Activity.onCreate(..))";
    private static final String ASPECT_METHOD_CREATEVIEW = "execution(* android.app.Fragment.onCreateView(..))";

    @Pointcut(ASPECT_METHOD_CLICK)
    public void aspectMethodOnClick(){

    }

    @Pointcut(ASPECT_METHOD_ONCREATE)
    public void aspectMethodCreate(){

    }

    @Pointcut(ASPECT_METHOD_CREATEVIEW)
    public void aspectMethodCreateView(){

    }

    @Before("aspectMethodOnClick()")
    public void invokeBeforeOnClick(JoinPoint joinPoint) {
        Log.e(TAG, "invokeBeforeOnClick: ");
    }

    @Around("aspectMethodCreate() || aspectMethodCreateView()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        joinPoint.proceed();
        stopWatch.stop();
        Log.e(TAG, " around:  className = " + className + "  methodName = " + methodName + " time = " + stopWatch.getTotalTimeMillis() );
    }

}
