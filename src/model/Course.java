package model;

import design_pattern.observer.IObserver;
import design_pattern.observer.ISubject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable, ISubject {
    private String id;
    private String name;
    private String classRoom;
    private Subject subject;
    private List<TranscriptOfStudent> transcriptOfStudents;
    private String message;

    public Course() {
        id = "";
        classRoom = "";
        name = "";
        subject = null;
        transcriptOfStudents = new ArrayList<>();
    }

    public Course(String id, String name, String classRoom, Subject subject) {
        this.id = id;
        this.name = name;
        this.classRoom = classRoom;
        this.subject = subject;
        transcriptOfStudents = new ArrayList<>();
    }

    @Override
    public void registerObserver(IObserver student) {
        addStudentToCourse((Student) student);
    }

    @Override
    public void removeObserver(IObserver student) {
        int index = transcriptOfStudents.indexOf(student);
        if(index >= 0) {
            transcriptOfStudents.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i< transcriptOfStudents.size(); i++) {
            transcriptOfStudents.get(i).student.update(this.message);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
        this.message = "Phòng học đã được cập nhật: " + this.classRoom;
        notifyObservers();
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setTranscriptOfStudents(Student student, Transcript transcript) {
        for (int i = 0; i < transcriptOfStudents.size(); i++) {
            if (transcriptOfStudents.get(i).student.getId().compareTo(student.getId()) == 0) {
                transcriptOfStudents.get(i).transcript = transcript;
                break;
            }
        }
    }

    public void addStudentToCourse(Student student) {
        transcriptOfStudents.add(new TranscriptOfStudent(student, null));
    }

    public List<TranscriptOfStudent> getTranscriptOfStudents() {
        return transcriptOfStudents;
    }

    public class TranscriptOfStudent {
        private Student student;
        private Transcript transcript;

        public TranscriptOfStudent(Student student, Transcript transcript) {
            this.student = student;
            this.transcript = transcript;
        }

        public Student getStudent() {
            return student;
        }

        public Transcript getTranscript() {
            return transcript;
        }
    }
}
