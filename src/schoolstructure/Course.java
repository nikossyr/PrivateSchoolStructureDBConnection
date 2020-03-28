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
public class Course {

    final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.UK);

    private String title;
    private String stream;
    private String type;
    private LocalDate start_date;
    private LocalDate end_date;
    private int id;
    private ArrayList<Assignment> assignmnentsAL;

    public Course(int id, String title, String stream, String type, LocalDate start_date, LocalDate end_date) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.id = id;
        assignmnentsAL = new ArrayList<>();
    }

    public Course() {
        this.start_date = LocalDate.MAX;
        this.end_date = LocalDate.MAX;
        assignmnentsAL = new ArrayList<>();
    }

    public ArrayList<Assignment> getAssignmnentsAL() {
        return assignmnentsAL;
    }

    public void setAssignmnentsAL(ArrayList<Assignment> assignmnentsAL) {
        this.assignmnentsAL = assignmnentsAL;
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

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return start_date;
    }

    public void setStartDate(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEndDate() {
        return end_date;
    }

    public void setEndDate(LocalDate end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return String.format("Course Title: %s\nCourse Stream: %s\nCourse Type: %s\nCourse Starts: %s\nCourse Ends: %s",
                getTitle(), getStream(), getType(), getStartDate().format(DATE_FORMATTER), getEndDate().format(DATE_FORMATTER));
    }

}
