package org.forsrc.auth2.dao;

import org.forsrc.auth2.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends BaseDao<Role, Long> {
}
