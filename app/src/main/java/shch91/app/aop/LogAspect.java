package shch91.app.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut( "execution (public * shch91.service.task..*.*(..))")
    private void controllerWrapper(){

    }

   @Before(value="controllerWrapper()")
    public void before(JoinPoint joinPoint){
        log.info("LogAspect.before");
    }

    @Around(value="controllerWrapper()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("LogAspect.around before");
        Object ret=proceedingJoinPoint.proceed();
        log.info("LogAspect.around after");
        return ret;
    }


    @AfterReturning(value="controllerWrapper()",returning="ret")
    public  void afterReturnng(JoinPoint joinPoint ,Object ret){
        log.info("LogAspect.afterReturning");
    }

    @AfterThrowing(value="controllerWrapper()", throwing="e")
    public  void afterThrowing(JoinPoint joinPoint,Throwable e){
        log.info("LogAspect.afterThrowing");
    }

    @After(value="controllerWrapper()")
    public void after(JoinPoint joinPoint) {
        log.info("LogAspect.after");
    }

}
