/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolstructure;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @author Nikos Syrios
 */
public class Student {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.UK);
    ArrayList<Course> courseAL;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private double tuitionFees;
    private int id;

    public Student(int studentId, String firstName, String lastName, LocalDate dateOfBirth, double tuitionFees, ArrayList<Course> courseAL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
        this.id = studentId;
        this.courseAL = courseAL;
    }

    public Student(int studentId, String firstName, String lastName, LocalDate dateOfBirth, double tuitionFees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
        this.id = studentId;

    }

    public Student() {
        this.dateOfBirth = LocalDate.MAX;
    }

    public double getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(double tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public ArrayList<Course> getCourses() {
        return courseAL;
    }

    public void setCourses(ArrayList<Course> courseAL) {
        this.courseAL = courseAL;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getFees() {
        return tuitionFees;
    }

    public void setFees(double tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    @Override
    public String toString() {

        return String.format("Student Name: %s %s\nDate Of Birth: %s\nTuition Fees: %s€",
                getFirstName(), getLastName(), getDateOfBirth().format(DATE_FORMATTER), getFees());
    }

}
