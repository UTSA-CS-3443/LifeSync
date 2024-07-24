package edu.utsa.cs3443.lifesync.model;

public class Account {
    private String name;
    private String age;
    private String occupation;
    private String address;

    public Account(String name, String address, String occupation, String age) {
        this.name = name;
        this.address = address;
        this.occupation = occupation;
        this.age = age;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", occupation='" + occupation + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
