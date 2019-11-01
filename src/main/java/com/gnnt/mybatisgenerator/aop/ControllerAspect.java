package com.gnnt.mybatisgenerator.aop;

import com.alibaba.fastjson.JSON;
import com.gnnt.mybatisgenerator.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object requestAround(ProceedingJoinPoint proceedingJoinPoint) {
        return controllerAround(proceedingJoinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object postAround(ProceedingJoinPoint proceedingJoinPoint) {
        return controllerAround(proceedingJoinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object getAround(ProceedingJoinPoint proceedingJoinPoint) {
        return controllerAround(proceedingJoinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public Object putAround(ProceedingJoinPoint proceedingJoinPoint) {
        return controllerAround(proceedingJoinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object deleteAround(ProceedingJoinPoint proceedingJoinPoint) {
        return controllerAround(proceedingJoinPoint);
    }

    private Object controllerAround(ProceedingJoinPoint pjp) {

        Method method = getMethod(pjp);
        Class<?> clazz = method.getDeclaringClass();
        Class<?> returnClazz = method.getReturnType();
        try {


            Object[] args = pjp.getArgs();

            //校验入参中是否包含BeanPropertyBindingResult，从而判断是否会出现数据校验失败的情况
            if (Optional.ofNullable(args).isPresent()) {

                Optional<BeanPropertyBindingResult> beanPropertyBindingResultOptional = Arrays.stream(args).filter(
                        Objects::nonNull).filter(
                        e -> e instanceof BeanPropertyBindingResult).map(o -> (BeanPropertyBindingResult)o).findFirst();
                if (beanPropertyBindingResultOptional.isPresent() && beanPropertyBindingResultOptional.get()
                        .hasErrors()) {
                    Class<? extends BaseResponse> baseResponseClass = (Class<? extends BaseResponse>)returnClazz;
                    if (ClassUtils.getAllSuperclasses(returnClazz).contains(BaseResponse.class)
                            || returnClazz == BaseResponse.class) {
                        return BaseResponse.buildFailed(baseResponseClass,"505", "参数校验失败",
                                beanPropertyBindingResultOptional.get().getFieldErrors());
                    }else {
                        throw new RuntimeException("参数校验失败");
                    }
                }
            }

            Object object = pjp.proceed(args);

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();

            HttpSession session = request.getSession();
            String userID =(String) session.getAttribute("sessionID");

            boolean isGet = request.getMethod().equalsIgnoreCase(RequestMethod.GET.name());

            if (Objects.equals(returnClazz, BaseResponse.class) || ClassUtils.getAllSuperclasses(returnClazz).contains(
                    BaseResponse.class)) {
                if (isGet) {
                    log.info("[web][{}][{}][{}][{}]",userID, clazz.getSimpleName(), method.getName(),
                            request.getServletPath());
                } else {
                    log.info("[web][{}][{}][{}][{}][{}][{}]", userID, clazz.getSimpleName(), method.getName(),
                            request.getServletPath(), JSON.toJSONString(args),
                            JSON.toJSONString(object));
                }
            }
            //oneLog(clazz, method, args);
            return object;
        } catch (Throwable t) {
            log.error("[web][{}][{}][{}]", clazz.getSimpleName(), method.getName(), t);

            Class<? extends BaseResponse> baseResponseClass = (Class<? extends BaseResponse>)returnClazz;
            try {
                if (t instanceof MyBatisSystemException && StringUtils.contains(t.getMessage(), "Duplicate entry")) {
                    return BaseResponse.buildFailed(baseResponseClass, "500", "code重复");
                }
            } catch (Exception e) {
                return handleError(t, returnClazz);
            }

            return handleError(t, returnClazz);
        }
    }
    private Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        return methodSignature.getMethod();
    }

    private Object handleError(Throwable t, Class<?> returnClazz) {
        Class<? extends BaseResponse> baseResponseClass = (Class<? extends BaseResponse>)returnClazz;
        if (ClassUtils.getAllSuperclasses(returnClazz).contains(BaseResponse.class)
                || returnClazz == BaseResponse.class) {
            return BaseResponse.buildFailed(baseResponseClass, "500", t.getMessage());
        } else {
            throw new RuntimeException(t);
        }
    }
}
