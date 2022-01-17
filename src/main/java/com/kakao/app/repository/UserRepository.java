package com.kakao.app.repository;

import com.kakao.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNm(String nm);
}
