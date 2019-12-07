package com.excellent.accreditation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excellent.accreditation.model.entity.Role;
import com.excellent.accreditation.model.form.RoleQuery;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author evildoer
 * @since 2019-12-07
 */
public interface IRoleService extends IService<Role> {

    String checkCode(String code);

    void checkRole(String role);

    boolean create(Role role);

    PageInfo<Role> pageByQuery(RoleQuery roleQuery);
}
