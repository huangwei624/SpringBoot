package com.middleyun.springbootduridactuator.entity;

import com.middleyun.springbootduridactuator.enums.GenderEnum;

import javax.persistence.*;

/**
 * Create by huangwei on 2020/5/22 0022
 */
@Entity
@Table(name="user")
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID 自动增长
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)   // 将枚举类型保存为整数
    @Column(nullable = false)
    private GenderEnum sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GenderEnum getSex() {
        return sex;
    }

    public void setSex(GenderEnum sex) {
        this.sex = sex;
    }
}
