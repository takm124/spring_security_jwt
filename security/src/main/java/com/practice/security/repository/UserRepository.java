package com.practice.security.repository;

import com.practice.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//CURD 함수를 JpaRepository가 들고있음
//@Repository라는 어노테이션이 없어도 IoC됨, JpaRepository를 상속했기 때문
public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy 규칙 ->username 문법
    // select * from user where username = ?;
    public User findByUsername(String username);
}
