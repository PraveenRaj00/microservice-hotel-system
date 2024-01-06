package com.example.User.Service.repository;

import com.example.User.Service.entities.UserTable;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable, String> {
}
