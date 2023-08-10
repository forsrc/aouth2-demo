package org.forsrc.auth2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
@Repository
public interface BaseDao<E extends java.io.Serializable, PK>
		extends JpaRepository<E, PK>, PagingAndSortingRepository<E, PK> {
}
