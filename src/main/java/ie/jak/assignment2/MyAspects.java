package ie.jak.assignment2;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class MyAspects {
    @Before("execution(* ie.jak.assignment2.Application.run(..))")
    public void loadData() {
        log.error("\tLoading the data into H2 database.");
    }

    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restMethods(JoinPoint joinPoint) {
        log.error("\tIn " + joinPoint.getSignature().getName() + ": " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("within(@org.springframework.web.bind.annotation.ControllerAdvice *)")
    public void controllerAdviceMethods(JoinPoint joinPoint) {
        log.error("\tIn " + joinPoint.getSignature().getName() + ": " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* ie.jak.assignment2.repositories.*.*(..))")
    public void repoMethods(JoinPoint joinPoint) {
        log.error("\tIn " + joinPoint.getSignature().getName() + ": " + Arrays.toString(joinPoint.getArgs()));
    }

}
