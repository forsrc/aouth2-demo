package org.forsrc.auth2.dao.mapper;

import java.util.List;
import org.springframework.data.repository.query.Param;

public interface UserRoleMapper {
	List<String> findByUserId(@Param("userId") Long paramLong);
}
