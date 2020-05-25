package com.middleyun.springbootduridactuator;

import com.middleyun.springbootduridactuator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by huangwei on 2020/5/25 0025
 */
public interface UserRepority extends JpaRepository<User, Integer> {
}
