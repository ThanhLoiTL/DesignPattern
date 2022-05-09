package model;

public class Transcript {
    private String id;
    private float grade1;
    private float grade2;
    private float grade3;
    private float avgGrade;
    private Capacity capacity;

    public Transcript(String id, float grade1, float grade2, float grade3, float avgGrade, Capacity capacity) {
        this.id = id;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.avgGrade = avgGrade;
        this.capacity = capacity;
    }

    public Transcript() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getGrade1() {
        return grade1;
    }

    public void setGrade1(float grade1) {
        this.grade1 = grade1;
    }

    public float getGrade2() {
        return grade2;
    }

    public void setGrade2(float grade2) {
        this.grade2 = grade2;
    }

    public float getGrade3() {
        return grade3;
    }

    public void setGrade3(float grade3) {
        this.grade3 = grade3;
    }

    public float getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(float avgGrade) {
        this.avgGrade = avgGrade;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }

    public void calCulAvgGrade() {
        this.avgGrade = (grade1 + 2 * grade2 + 3 * grade3) / 6;
    }

    public void calCulCapacity() {
        if (avgGrade >= 9.0f)
            capacity = Capacity.A_PLUS;
        else if (avgGrade >= 8.0f)
            capacity = Capacity.A;
        else if (avgGrade >= 6.5f)
            capacity = Capacity.B;
        else if (avgGrade >= 5.0f)
            capacity = Capacity.C;
        else if (avgGrade >= 4.0f)
            capacity = Capacity.D;
        else
            capacity = Capacity.F;
    }
}
