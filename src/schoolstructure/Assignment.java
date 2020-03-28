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
public class Assignment {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy", Locale.UK);

    private String title;
    private String description;
    private LocalDateTime subDateTime;
    private double oralMark;
    private double totalMark;
    private int id;
    private Course course;

    public Assignment(int id, String title, String description, LocalDateTime subDateTime, double oralMark, double totalMark, Course course) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.id = id;
        this.course = course;
    }

    public Assignment(int id, String title, String description, LocalDateTime subDateTime, double oralMark, double totalMark) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.id = id;
    }

    public Assignment() {
        this.subDateTime = LocalDateTime.MAX;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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

    public LocalDateTime getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDateTime submissionDate) {

        this.subDateTime = submissionDate;
    }

    public double getOralMark() {
        return oralMark;
    }

    public void setOralMark(double oralMark) {
        this.oralMark = oralMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public String toString() {

        return String.format("Assignment Title: %s\nAssignment Description: %s\nSubmission Date: %s\nOral Mark: %s\nTotal Mark: %s",
                getTitle(), getDescription(), getSubDateTime().format(DATE_FORMATTER), getOralMark(), getTotalMark());
    }

}
