package edu.utsa.cs3443.lifesync.model;

public class Account {
    private String name;
    private int age;
    private String occupation;
    private String address;
    private String gender;
    private String biography;
    private String hobby;

    public Account(String name, String address, String occupation, int age) {
        this.name = name;
        this.address = address;
        this.occupation = occupation;
        this.age = age;
    }

    public Account(String name, int age, String occupation, String address, String gender, String biography, String hobby) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.address = address;
        this.gender = gender;
        this.biography = biography;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", biography='" + biography + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
