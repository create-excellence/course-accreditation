package com.excellent.accreditation.common.aop;

import com.excellent.accreditation.common.annotation.Permission;
import com.excellent.accreditation.common.authentication.JWTUtil;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.common.exception.AuthenticationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class PermissionAspect {
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
        String role = getRole();
        String[] roles = permission.roles();
        for (String r : roles) {
            if (r.equals(role))
                return;
        }
        throw new AuthenticationException("权限不足");
    }

    private String getRole() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        token = JWTUtil.decryptToken(token);            // 解密token
        return JWTUtil.getName(token);
    }
}
