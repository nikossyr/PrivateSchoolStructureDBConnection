/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolstructure;

import java.util.ArrayList;

/**
 * @author Nikos Syrios
 */
public class Trainer {

    private String firstName;
    private String lastName;
    private String subject;
    private int id;
    private ArrayList<Course> courses;

    public Trainer(int id, String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.id = id;
        courses = new ArrayList<>();
    }

    public Trainer() {
        courses = new ArrayList<>();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {

        return String.format("Trainer Name: %s %s\nSubject: %s",
                getFirstName(), getLastName(), getSubject());
    }

}
