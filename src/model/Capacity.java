package model;

public enum Capacity {
    F("trượt môn"), D("yếu"), C("trung bình"), B("khá"), A("giỏi"), A_PLUS("xuất sắc");
    private String value;

    Capacity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
