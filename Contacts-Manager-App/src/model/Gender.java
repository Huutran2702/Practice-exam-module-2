package model;

public enum Gender {
    MALE("MALE"),FEMALE("FEMALE"),OTHER("OTHER");
    private String value;

    private Gender(String value) {
        this.value = value;
    }
}
