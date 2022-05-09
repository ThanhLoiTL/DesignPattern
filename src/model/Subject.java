package model;

public class Subject {
    private String id;
    private String name;
    private int credit;
    private int numOfLesson;

    public Subject(String id, String name, int credit, int numOfLesson) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.numOfLesson = numOfLesson;
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getNumOfLesson() {
        return numOfLesson;
    }

    public void setNumOfLesson(int numOfLesson) {
        this.numOfLesson = numOfLesson;
    }
}
