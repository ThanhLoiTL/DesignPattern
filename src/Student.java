import java.io.Serializable;
import java.util.Scanner;

public class Student implements Serializable, IStudent, Prototype {
    private int id;
    private String name;
    private int age;
    private String address;
    private String email;
    private float gpa;

    //private static int count;

    public Student() {
        //id = ++count;
    }

    public Student(int id, String name, int age, String address, String email, float gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.email = email;
        this.gpa = gpa;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public void input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input name: ");
        name = scanner.nextLine();
        System.out.print("Input age: ");
        age = Integer.parseInt(scanner.nextLine());
        System.out.print("Input address: ");
        address = scanner.nextLine();
        System.out.print("Input email: ");
        email = scanner.nextLine();
        System.out.print("Input gpa: ");
        gpa = Float.parseFloat(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Student: " +
                "id= " + id +
                ", name= " + name +
                ", address= " + address +
                ", email= " + email +
                ", gpa= " + gpa;
    }

    public void display(){
        System.out.println(this);
    }

    @Override
    public Prototype clone() {
        return new Student(id, name, age, address, email, gpa);
    }
}