package org.forsrc.auth2.dao;

import org.forsrc.auth2.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, Long> {
	Page<User> findAll(Pageable paramPageable);

	User findByUsername(String paramString);
}
