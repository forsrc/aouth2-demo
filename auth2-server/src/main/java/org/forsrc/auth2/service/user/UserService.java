package org.forsrc.auth2.service.user;

import java.util.List;
import org.forsrc.auth2.dao.UserDao;
import org.forsrc.auth2.dao.mapper.UserRoleMapper;
import org.forsrc.auth2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleMapper userRoleMapper;

	public User findByUsername(String username) {
		return this.userDao.findByUsername(username);
	}

	public List<String> findRoleByUserId(Long id) {
		return this.userRoleMapper.findByUserId(id);
	}
}
