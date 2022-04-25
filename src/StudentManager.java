import java.util.Scanner;

public class StudentManager {
    static Scanner scanner = new Scanner(System.in);
    StudentFileManager studentFileManager;
    IContainer studentContainer;
    IIterator studentIterator;

    public StudentManager() {
        studentFileManager = StudentFileManager.getInstance();
        studentContainer = new StudentContainer();
    }

    public int generateID() {
        return  (studentFileManager.read().size() > 0) ? studentFileManager.read().size() + 1 : 1;
    }

    private Student add(){
        Scanner scanner = new Scanner(System.in);
        int id = generateID();
        System.out.print("Input name: ");
        String name = scanner.nextLine();
        System.out.print("Input age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Input address: ");
        String address = scanner.nextLine();
        System.out.print("Input email: ");
        String email = scanner.nextLine();
        System.out.print("Input gpa: ");
        float gpa = Float.parseFloat(scanner.nextLine());
        return new Student(id, name, age, address, email, gpa);
    }

    public void showStudent() {
        studentIterator = studentContainer.createIterator();
        while (studentIterator.hasNext()){
            Student student = (Student) studentIterator.next();
            student.display();
        }
    }

    private void saveStudent(Student student) {
        studentFileManager.write(student);
    }

    public void inputStudent() {
        System.out.print("Input number of student: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Student student = add();
            saveStudent(student);
        }
    }

    public void clone(Student student) {
        if(student != null){
            Student s = (Student) student.clone();
            s.setId(generateID());
            saveStudent(s);
        }
    }

    public Student findOne() {
        System.out.print("Input ID student: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        studentIterator = studentContainer.createIterator();
        while (studentIterator.hasNext()){
            Student student = (Student) studentIterator.next();
            if(student.getId() == id) {
                return student;
            }
        }
//        for (Student s: studentFileManager.read()) {
//            if(s.getId() == id) {
//                return s;
//            }
//        }
        System.out.print("Not found student ID!\n");
        return null;
    }
}
