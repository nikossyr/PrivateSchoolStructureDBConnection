/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolstructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Nikos Syrios
 */
public class StudentAssignment {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy HH:mm:ss", Locale.UK);

    private String title;
    private String description;
    private LocalDateTime assignmentSubDateTime;
    private LocalDateTime studentSubDateTime;
    private double studentOralMark;
    private double studentTotalMark;
    private double assignmentOralMark;
    private double assignmentTotalMark;
    private int id;
    private Course course;
    private Student student;

    public StudentAssignment(int id, String title, String description, LocalDateTime studentSubDateTime, double studentOralMark, double studentTotalMark, Course course) {
        this.title = title;
        this.description = description;
        this.studentSubDateTime = studentSubDateTime;
        this.studentOralMark = studentOralMark;
        this.studentTotalMark = studentTotalMark;
        this.id = id;
        this.course = course;
    }

    public StudentAssignment(int id, String title, String description, LocalDateTime subDateTime, double oralMark, double totalMark) {
        this.title = title;
        this.description = description;
        this.assignmentSubDateTime = subDateTime;
        this.studentTotalMark = totalMark;
        this.id = id;
    }

    public StudentAssignment() {
        this.assignmentSubDateTime = LocalDateTime.MAX;
    }

    public Course getCourse() {
        return course;
    }

    public void setCoursesAL(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStudentSubDateTime() {
        return studentSubDateTime;
    }

    public void setStudentSubDateTime(LocalDateTime studentSubDateTime) {
        this.studentSubDateTime = studentSubDateTime;
    }

    public double getStudentOralMark() {
        return studentOralMark;
    }

    public void setStudentOralMark(double studentOralMark) {
        this.studentOralMark = studentOralMark;
    }

    public double getStudentTotalMark() {
        return studentTotalMark;
    }

    public void setStudentTotalMark(double studentTotalMark) {
        this.studentTotalMark = studentTotalMark;
    }

    public LocalDateTime getAssignmentSubDateTime() {
        return assignmentSubDateTime;
    }

    public void setAssignmentSubDateTime(LocalDateTime submissionDate) {

        this.assignmentSubDateTime = submissionDate;
    }

    public double getAssignmentOralMark() {
        return assignmentOralMark;
    }

    public void setAssignmentOralMark(double assignmentOralMark) {
        this.assignmentOralMark = assignmentOralMark;
    }

    public double getAssignmentTotalMark() {
        return assignmentTotalMark;
    }

    public void setAssignmentTotalMark(double assignmentTotalMark) {
        this.assignmentTotalMark = assignmentTotalMark;
    }

    @Override
    public String toString() {

        return String.format("Assignment Title: %s\nAssignment Description: %s\nSubmission Date: %s\nOral Mark: %s\nTotal Mark: %s",
                getTitle(), getDescription(), getAssignmentSubDateTime().format(DATE_FORMATTER), getStudentOralMark(), getStudentTotalMark());
    }

}
