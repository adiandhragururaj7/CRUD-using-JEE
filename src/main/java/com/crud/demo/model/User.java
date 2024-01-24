package com.crud.demo.model;

public class User {

    private int id;
    private String name;
    private int age;
    private String sex;
    private String position;
    private String address;
    private int salary;

    public User(int id, String name, String sex, int age, String position, String address, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.position = position;
        this.address = address;
        this.salary = salary;
    }
    public User( String name, String sex, int age, String position, String address, int salary) {

        this.name = name;
        this.age = age;
        this.sex = sex;
        this.position = position;
        this.address = address;
        this.salary = salary;
    }

    public User(int id, int age, String address, String position, int salary) {
        this.age = age;
        this.position = position;
        this.address = address;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
