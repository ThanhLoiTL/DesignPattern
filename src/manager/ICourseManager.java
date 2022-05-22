package manager;

import model.Course;
import model.Student;

public interface ICourseManager extends IManager<Course>{
    Student findStudentInCourse(Course course ,String studentID);
    void deleteStudentFromCourse();
    void showStudentInCourse();
}
