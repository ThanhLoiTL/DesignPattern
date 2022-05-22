package model;

import design_pattern.observer.IObserver;
import design_pattern.prototype.IPrototype;
import java.io.Serializable;

public class Student extends Person implements Serializable, IPrototype, IObserver {
    private static int nextId = 0;
    private String major;

    public Student(String id, String name, int age, String gender, String address, String email, String phoneNumber, String major) {
        super(name, age, gender, address, email, phoneNumber);
        super.setId(this.setIdStudent(id));
        this.major = major;
    }

    public Student() {
        super();
    }

    @Override
    public IPrototype clone() {
        return new Student(null, this.getName(), this.getAge(), this.getGender(), this.getAddress(), this.getEmail(), this.getPhoneNumber(), major);
    }

    @Override
    public void update(String message) {
        System.out.println("Sinh viên " + getName() + " nhận được thông báo: "+ message);
    }

    public String setIdStudent(String id) {
        if (id == null) { // nếu id rỗng chứng tỏ đối tượng cần được tạo mới.
            String s = "S" + nextId;
            nextId++;
            super.setId(s);
            return s;
        } else {
            return id;
        }
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String line() {
        return this.getId() + "," + this.getName() + "," + this.getAge() + "," + this.getGender() + "," + this.getAddress() + "," + this.getEmail() + "," + this.getPhoneNumber() + "," + this.getMajor() +"\n";
    }
    @Override
    public void parse(String line) {
        String[] params = line.split(",");
        try {
            this.setId(params[0]);
            this.setName(params[1]);
            this.setAge(Integer.parseInt(params[2]));
            this.setGender(params[3]);
            this.setAddress(params[4]);
            this.setEmail(params[5]);
            this.setPhoneNumber(params[6]);
            this.setMajor(params[7]);
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }
}