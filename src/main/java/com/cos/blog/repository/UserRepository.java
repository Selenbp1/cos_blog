package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//DAO와 같음
// 자동으로 bean 등록이 됨
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {

}
