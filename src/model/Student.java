package model;

import observer.IObserver;
import prototype.IPrototype;

import java.io.Serializable;

public class Student implements Serializable, IPrototype, IObserver {
    private static int nextId = 0;
    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String email;
    private String phoneNumber;

    public Student() {
    }

    public Student(String id, String name, int age, String gender, String address, String email, String phoneNumber) {
        this.setId(id);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public IPrototype clone() {
        return new Student(null, name, age, gender, address, email, phoneNumber);
    }

    @Override
    public void update(String message) {
        System.out.println("Sinh viên " + getName() + " nhận được thông báo: "+ message);
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(String id) {
        if (id == null) { // nếu id rỗng chứng tỏ đối tượng cần được tạo mới.
            this.id = "S" + nextId;
            nextId++;
        } else {
            this.id = id;
        }
    }

    public String getId() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String line() {
        return id + "," + name + "," + age + "," + gender + "," + address + "," + email + "," + phoneNumber + "\n";
    }

    public void parse(String line) {
        String[] params = line.split(",");
        try {
            id = params[0];
            name = params[1];
            age = Integer.parseInt(params[2]);
            gender = params[3];
            address = params[4];
            email = params[5];
            phoneNumber = params[6];
        } catch (ArrayIndexOutOfBoundsException e) {
        } finally {
        }
    }
}