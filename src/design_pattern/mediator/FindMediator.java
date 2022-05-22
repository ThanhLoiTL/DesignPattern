package design_pattern.mediator;

import manager.IManager;
import manager.impl.*;
import model.*;

public class FindMediator implements IFindMediator {
    private IManager<Student> studentManager;
    private IManager<Subject> subjectManager;
    private IManager<Course> courseManager;
    private IManager<Teacher> teacherManager;
    private IManager<Transcript> transcriptManager;

    public FindMediator() {
        studentManager = new StudentManager();
        subjectManager = new SubjectManager();
        courseManager = new CourseManager();
        teacherManager = new TeacherManager();
        transcriptManager = new GradesManager();
    }

    @Override
    public Object findOne(ObjectType type, String id) {
        switch (type) {
            case STUDENT:
                return studentManager.findById(id);
            case SUBJECT:
                return subjectManager.findById(id);
            case COURSE:
                return courseManager.findById(id);
            case TEACHER:
                return teacherManager.findById(id);
            case GRADES:
                return transcriptManager.findById(id);
            default:
                System.out.println("Unreachable code!");
        }
        return null;
    }

}
