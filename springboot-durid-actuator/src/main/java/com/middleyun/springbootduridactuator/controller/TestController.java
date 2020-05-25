package com.middleyun.springbootduridactuator.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.middleyun.springbootduridactuator.UserRepority;
import com.middleyun.springbootduridactuator.entity.User;
import com.middleyun.springbootduridactuator.enums.GenderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * Create by huangwei on 2020/5/25 0025
 */
@RestController
@RequestMapping("user")
public class TestController {

    @Autowired
    private UserRepority userRepority;

    @PostMapping("/add")
    public User addUser(@RequestBody User u){
        return userRepority.save(u);
    }

    @GetMapping("/delete")
    public String deleteUser(int id){
        userRepority.deleteById(id);
        return "删除成功！";
    }

    @GetMapping("/update")
    public User updateUser(int id, String newName){
        Optional<User> userOptional = userRepority.findById(id);
        User user = userOptional.get();
        user.setUsername(newName);
        return userRepority.save(user);
    }

    @GetMapping("/find")
    public User findUser(int id){
        Optional<User> userOptional = userRepority.findById(id);
        return userOptional.get();
    }

}
