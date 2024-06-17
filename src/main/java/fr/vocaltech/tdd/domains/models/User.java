package fr.vocaltech.tdd.domains.models;

public class User {
    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
