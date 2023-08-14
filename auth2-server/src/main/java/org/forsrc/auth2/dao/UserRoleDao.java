package org.forsrc.auth2.dao;

import java.util.List;

import org.forsrc.auth2.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends BaseDao<UserRole, Long> {
	List<UserRole> findByUserId(Long paramLong);
}
