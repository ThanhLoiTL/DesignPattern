package model;

public class Teacher extends Person{
    private static int nextId = 0;
    private String department;
    private int salary;

    public Teacher(String id, String name, int age, String gender, String address, String email, String phoneNumber, String department, int salary) {
        super(name, age, gender, address, email, phoneNumber);
        super.setId(this.setIdTeacher(id));
        this.department = department;
        this.salary = salary;
    }

    public Teacher() {
        super();
    }

    public String setIdTeacher(String id) {
        if (id == null) { // nếu id rỗng chứng tỏ đối tượng cần được tạo mới.
            String s = "T" + nextId;
            nextId++;
            super.setId(s);
            return s;
        } else {
            return id;
        }
    }

    public static void setNextId(int nextId) {
        Teacher.nextId = nextId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String line() {
        return this.getId() + "," + this.getName() + "," + this.getAge() + "," + this.getGender() + "," + this.getAddress()
                + "," + this.getEmail() + "," + this.getPhoneNumber() + "," + this.getDepartment() +"," + this.getSalary() +"\n";
    }

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
            this.setDepartment(params[7]);
            this.setSalary(Integer.parseInt(params[8]));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }
}
