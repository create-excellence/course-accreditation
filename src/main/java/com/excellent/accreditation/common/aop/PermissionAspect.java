package com.excellent.accreditation.common.aop;

import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.exception.AuthenticationException;
import com.excellent.accreditation.manage.UserManage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class PermissionAspect {
    @Autowired
    UserManage userManage;

    /**
     * @Description: 角色权限验证
     * @Param: [join, permission]
     * @return: boolean
     * @Author: ashe
     * @Date: 2019/11/15
     */
    @Pointcut("execution(public * com.excellent.accreditation.controller.*.*(..)) within(com.excellent.accreditation.controller.*)")
    private void point() {
    }

    //切入点签名
    @Before(value = "point()&&@annotation(permission)")
    public void rolePermission(JoinPoint joinPoint, Permission permission) {
        printLog();
        String role = getRole();
        String[] roles = permission.roles();
        for (String r : roles) {
            if (r.equals(role))
                return;
        }
        throw new AuthenticationException("权限不足");
    }

    private String getRole() {
        String code = userManage.getCodeByToken();
        return userManage.getRoleByCode(code);
    }

    public void printLog() {
        // Get request attribute
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(requestAttributes).getRequest();

        log.info("Request URL: [{}], URI: [{}], Request Method: [{}], IP: [{}]",
                request.getRequestURL(),
                request.getRequestURI(),
                request.getMethod(),
                getClientIp(request));
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddress = "";

        if (request != null) {
            remoteAddress = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddress == null || "".equals(remoteAddress)) {
                remoteAddress = request.getRemoteAddr();
            }
        }

        return remoteAddress;
    }

}
