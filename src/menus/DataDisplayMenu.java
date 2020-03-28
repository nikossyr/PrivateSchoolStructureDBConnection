/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import Utils.DBUtils;
import Utils.SchoolUtils;
import schoolstructure.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class that contains the methods to generate a Menu that gives the option to
 * the user to View (print) the attributes. There are several View modes the
 * user could choose from.
 *
 * @author Nikos Syrios
 */
public class DataDisplayMenu {

    private static final Scanner SC = new Scanner(System.in);
    private static final String RESET_SHORTCUT_MESSAGE_STRING = "You can always type \""
            + SchoolUtils.CANCEL_COMMAND_STRING + "\" to start over ";

    /**
     * Main method used to generate the Menu and Dialogue. It gives the user the
     * option to View the attributes. There are several View modes the user
     * could choose from:
     * <ul>
     * <li>All Students
     * <li>All Trainers
     * <li>All Assignments
     * <li>All Students per Course
     * <li>All Trainers per Course
     * <li>All Assignments per Course
     * <li>All Students that belong to more than one Course
     * <li>All students that need to submit Assignments on the same calendar
     * week as the user typed Date.
     * </ul>
     */
    public static void generateMenu() {
        ArrayList<Course> coursesAL = new ArrayList<>();
        ArrayList<Trainer> trainersAL = new ArrayList<>();
        ArrayList<Trainer> trainersPerCourseAL;
        ArrayList<Student> studentsAL = new ArrayList<>();
        ArrayList<Student> studentsPerCourseAL;
        ArrayList<Student> studentsInMultipleCoursesAL = new ArrayList<>();
        ArrayList<Assignment> assignmentsAL = new ArrayList<>();
        ArrayList<Assignment> assignmentsPerCourseAL;
        ArrayList<StudentAssignment> assignmentsPerStudentAL;
        System.out.println("What would you like to view?");
        boolean hasNextInput = true;
        while (hasNextInput) {
            System.out.println("==================================");
            System.out.println("1 - View all Courses");
            System.out.println("2 - View all Trainers");
            System.out.println("3 - View all Students");
            System.out.println("4 - View all Assignments");
            System.out.println("**********************************");
            System.out.println("5 - View all Students per Course");
            System.out.println("6 - View all Trainers per Course");
            System.out.println("7 - View all Assignements per Course");
            System.out.println("**********************************");
            System.out.println("8 - View all Students that are enrolled to multiple Courses");
            System.out.println("9 - View all Assignments per Course per Student");
            System.out.println("----------------------------------");
            System.out.println("0 - Retrun to previous menu screen");
            System.out.println("----------------------------------");
            if (SC.hasNextInt()) {
                int currentUserInput = SC.nextInt();
                SC.nextLine();
                switch (currentUserInput) {
                    case 0:
                        hasNextInput = false;
                        DataViewAddMenu.generateMenu();
                        break;
                    case 1:
                        if (coursesAL.isEmpty()) {
                            coursesAL = DBUtils.getCourses();
                        }
                        if (!coursesAL.isEmpty()) {
                            switch (coursesAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("Oops! You need to create some Courses first!");
                                    System.out.println("");
                                    break;
                                default:
                                    System.out.println("Courses");
                                    System.out.println("**********************************");
                                    for (Course course : coursesAL) {
                                        System.out.println(course);
                                        System.out.println("");
                                    }
                                    break;
                            }
                        }
                        System.out.println("Press ENTER to return to the menu");
                        if (SC.hasNextLine()) {
                            SC.nextLine();
                            break;
                        }

                    case 2:
                        if (trainersAL.isEmpty()) {
                            trainersAL = DBUtils.getTrainers();
                        }
                        if (!trainersAL.isEmpty()) {
                            switch (trainersAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("Oops! You need to create some Trainers first!");
                                    System.out.println("");
                                    break;
                                default:
                                    System.out.println("Trainers");
                                    System.out.println("**********************************");
                                    for (Trainer trainer : trainersAL) {
                                        System.out.println(trainer);
                                        System.out.println("");
                                    }
                                    break;
                            }
                        }
                        System.out.println("Press ENTER to return to the menu");
                        if (SC.hasNextLine()) {
                            SC.nextLine();
                            break;
                        }
                    case 3:
                        if (studentsAL.isEmpty()) {
                            studentsAL = DBUtils.getStudents();
                        }
                        if (!studentsAL.isEmpty()) {
                            switch (studentsAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("Oops! You need to create some Students first!");
                                    System.out.println("");
                                    break;
                                default:
                                    System.out.println("Students");
                                    System.out.println("**********************************");
                                    for (Student student : studentsAL) {
                                        System.out.println(student);
                                        System.out.println("");
                                    }
                                    break;
                            }
                        }
                        System.out.println("Press ENTER to return to the menu");
                        if (SC.hasNextLine()) {
                            SC.nextLine();
                            break;
                        }
                    case 4:
                        if (assignmentsAL.isEmpty()) {
                            assignmentsAL = DBUtils.getAssignments();
                        }
                        if (!assignmentsAL.isEmpty()) {
                            switch (assignmentsAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("Oops! You need to create some Assignments first!");
                                    System.out.println("");
                                    break;
                                default:
                                    System.out.println("Assignments");
                                    System.out.println("**********************************");
                                    for (Assignment assignment : assignmentsAL) {
                                        System.out.println(assignment);
                                        System.out.println("");
                                    }
                                    break;
                            }
                        }
                        System.out.println("Press ENTER to return to the menu");
                        if (SC.hasNextLine()) {
                            SC.nextLine();
                            break;
                        }
                    case 5:
                        if (coursesAL.isEmpty()) {
                            coursesAL = DBUtils.getCourses();
                        }
                        if (!coursesAL.isEmpty()) {
                            switch (coursesAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("Oops! You need to create some Courses first!");
                                    System.out.println("");
                                    break;
                                default:
                                    Course courseSelected = SchoolUtils.selectCourse(coursesAL);
                                    if (courseSelected == SchoolUtils.CANCEL_COMMAND_COURSE
                                            || courseSelected == SchoolUtils.RETURN_TO_MENU_COMMAND_COURSE) {
                                        System.out.println("Returning to previous menu");
                                        break;
                                    }
                                    int courseId = courseSelected.getId();
                                    studentsPerCourseAL = DBUtils.getStudentsPerCourse(courseId);
                                    if (!studentsPerCourseAL.isEmpty()) {
                                        switch (studentsPerCourseAL.get(0).getId()) {
                                            case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                                System.out.println("");
                                                System.out.println("No students found for this course.");
                                                System.out.println("");
                                                break;
                                            default:
                                                printAttributtesPerCourse(studentsPerCourseAL, courseSelected);
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        System.out.println("Press ENTER to return to the menu");
                        if (SC.hasNextLine()) {
                            SC.nextLine();
                            break;
                        }
                    case 6:
                        if (coursesAL.isEmpty()) {
                            coursesAL = DBUtils.getCourses();
                        }
                        if (!coursesAL.isEmpty()) {
                            switch (coursesAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("Oops! You need to create some Courses first!");
                                    System.out.println("");
                                    break;
                                default:
                                    Course courseSelected = SchoolUtils.selectCourse(coursesAL);
                                    if (courseSelected == SchoolUtils.CANCEL_COMMAND_COURSE
                                            || courseSelected == SchoolUtils.RETURN_TO_MENU_COMMAND_COURSE) {
                                        System.out.println("Returning to previous menu");
                                        break;
                                    }
                                    int courseId = courseSelected.getId();
                                    trainersPerCourseAL = DBUtils.getTrainersPerCourse(courseId);
                                    if (!trainersPerCourseAL.isEmpty()) {
                                        switch (trainersPerCourseAL.get(0).getId()) {
                                            case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                                System.out.println("");
                                                System.out.println("No trainers found for this course.");
                                                System.out.println("");
                                                break;
                                            default:
                                                printAttributtesPerCourse(trainersPerCourseAL, courseSelected);
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        System.out.println("Press ENTER to return to the menu");
                        if (SC.hasNextLine()) {
                            SC.nextLine();
                            break;
                        }
                    case 7:
                        if (coursesAL.isEmpty()) {
                            coursesAL = DBUtils.getCourses();
                        }
                        if (!coursesAL.isEmpty()) {
                            switch (coursesAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("Oops! You need to create some Courses first!");
                                    System.out.println("");
                                    break;
                                default:
                                    Course courseSelected = SchoolUtils.selectCourse(coursesAL);
                                    if (courseSelected == SchoolUtils.CANCEL_COMMAND_COURSE
                                            || courseSelected == SchoolUtils.RETURN_TO_MENU_COMMAND_COURSE) {
                                        System.out.println("Returning to previous menu");
                                        break;
                                    }
                                    int courseId = courseSelected.getId();
                                    assignmentsPerCourseAL = DBUtils.getAssignmentsPerCourse(courseId);
                                    if (!assignmentsPerCourseAL.isEmpty()) {
                                        switch (assignmentsPerCourseAL.get(0).getId()) {
                                            case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                                System.out.println("");
                                                System.out.println("No assignmnets found for this course.");
                                                System.out.println("");
                                                break;
                                            default:
                                                printAttributtesPerCourse(assignmentsPerCourseAL, courseSelected);
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        System.out.println("Press ENTER to return to the menu");
                        if (SC.hasNextLine()) {
                            SC.nextLine();
                            break;
                        }
                    case 8:
                        if (studentsInMultipleCoursesAL.isEmpty()) {
                            studentsInMultipleCoursesAL = DBUtils.getStudentsWithMultipleCourses();
                        }
                        if (!studentsInMultipleCoursesAL.isEmpty()) {
                            switch (studentsInMultipleCoursesAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("No students enrolled in multiple courses found");
                                    System.out.println("");
                                    break;
                                default:
                                    printStudentsWithMultipleCourses(studentsInMultipleCoursesAL);
                                    break;
                            }
                        }
                        System.out.println("Press ENTER to return to the menu");
                        if (SC.hasNextLine()) {
                            SC.nextLine();
                            break;
                        }
                    case 9:
                        if (studentsAL.isEmpty()) {
                            studentsAL = DBUtils.getStudents();
                        }
                        if (!studentsAL.isEmpty()) {
                            switch (studentsAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("No Students found.");
                                    System.out.println("");
                                    break;
                                default:
                                    Student studentSelected = SchoolUtils.selectStudent(studentsAL);
                                    int studentId = studentSelected.getId();
                                    assignmentsPerStudentAL = DBUtils.getAssignmentsPerCoursePerStudent(studentId);
                                    if (!assignmentsPerStudentAL.isEmpty()) {
                                        switch (assignmentsPerStudentAL.get(0).getId()) {
                                            case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                                System.out.println("");
                                                System.out.println("No assignmnets found for this Student.");
                                                System.out.println("");
                                                break;
                                            default:
                                                printAssignmentsPerStudent(assignmentsPerStudentAL, studentSelected);
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        System.out.println("Press ENTER to return to the menu");
                        if (SC.hasNextLine()) {
                            SC.nextLine();
                            break;
                        }
                    default:
                        System.out.println("Please insert the number next to the option you want to choose");
                        hasNextInput = true;
                        System.out.println("Press ENTER to return to the menu");
                        if (SC.hasNextLine()) {
                            SC.nextLine();
                            break;
                        }
                }
            } else {
                hasNextInput = true;
                SC.nextLine();
                System.out.println("Please insert the number next to the option you want to choose");

            }
        }
    }

    private static void printAttributtesPerCourse(ArrayList attributesAL, Course course) {
        if (attributesAL != null && !attributesAL.isEmpty()) {
            if (attributesAL.get(0) instanceof Student) {
                int counter = 0;
                System.out.println("Students in " + course.getTitle());
                System.out.println("----------------------------------");
                for (int i = 0; i < attributesAL.size(); i++) {
                    Student currentStudent = (Student) attributesAL.get(i);
                    System.out.println(currentStudent.getFirstName() + " " + currentStudent.getLastName());
                    counter++;

                }
                System.out.println("----------------------------------");
                System.out.println(counter + " Students found.");

            } else if (attributesAL.get(0) instanceof Trainer) {
                int counter = 0;
                System.out.println("Trainers in " + course.getTitle());
                System.out.println("----------------------------------");
                for (int i = 0; i < attributesAL.size(); i++) {
                    Trainer currentTrainer = (Trainer) attributesAL.get(i);
                    System.out.println(currentTrainer.getFirstName() + " " + currentTrainer.getLastName());
                    System.out.println("Subject: " + currentTrainer.getSubject());
                    System.out.println("");
                    counter++;

                }
                System.out.println("----------------------------------");
                System.out.println(counter + " Trainers found.");

            } else if (attributesAL.get(0) instanceof Assignment) {
                int counter = 0;
                System.out.println("Assignments in " + course.getTitle());
                System.out.println("----------------------------------");
                for (int i = 0; i < attributesAL.size(); i++) {
                    Assignment currentAssignement = (Assignment) attributesAL.get(i);
                    System.out.println(currentAssignement.getTitle());
                    System.out.println("Due on: " + currentAssignement.getSubDateTime().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy", Locale.UK)));
                    System.out.println("Oral Mark: " + currentAssignement.getOralMark());
                    System.out.println("Total Mark: " + currentAssignement.getTotalMark());
                    System.out.println("");
                    counter++;
                }
                System.out.println("----------------------------------");
                System.out.println(counter + " Assignements found.");
            }
        }
    }

    private static void printAssignmentsPerStudent(ArrayList<StudentAssignment> studentAssignmentsssignmentsAL, Student student) {
        int counter = 0;
        System.out.println("Assignments of " + student.getLastName() + " " + student.getFirstName());
        System.out.println("----------------------------------");
        Course referenceAssignmentCourse = studentAssignmentsssignmentsAL.get(0).getCourse();
        for (int i = 0; i < studentAssignmentsssignmentsAL.size(); i++) {
            StudentAssignment currentStudentAssignmentAssignement = studentAssignmentsssignmentsAL.get(i);
            Course currentAssignmentCourse = currentStudentAssignmentAssignement.getCourse();
            if (i == 0) {
                System.out.println("----------------------------------");
                System.out.println("Course: " + referenceAssignmentCourse.getTitle());
                System.out.println("----------------------------------");
            } else if (currentAssignmentCourse.getId() != referenceAssignmentCourse.getId()) {
                referenceAssignmentCourse = currentAssignmentCourse;
                System.out.println("----------------------------------");
                System.out.println("Course: " + referenceAssignmentCourse.getTitle());
                System.out.println("----------------------------------");
            }

            System.out.println(currentStudentAssignmentAssignement.getTitle());
            System.out.println("Due on: " + currentStudentAssignmentAssignement.getAssignmentSubDateTime().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy HH:mm:ss", Locale.UK)));
            if (currentStudentAssignmentAssignement.getStudentSubDateTime() != null) {
                System.out.println("Submited on: " + currentStudentAssignmentAssignement.getStudentSubDateTime().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy HH:mm:ss", Locale.UK)));
                System.out.println("Oral Mark: " + currentStudentAssignmentAssignement.getStudentOralMark() + "/" + currentStudentAssignmentAssignement.getAssignmentOralMark());
                System.out.println("Total Mark: " + currentStudentAssignmentAssignement.getStudentTotalMark() + "/" + currentStudentAssignmentAssignement.getAssignmentTotalMark());
            } else {
                System.out.println("Not submited yet");
            }
            System.out.println("");
            counter++;
        }

        System.out.println(
                "----------------------------------");
        System.out.println(counter
                + " Assignements found.");
    }

    private static void printStudentsWithMultipleCourses(ArrayList<Student> studentsAL) {
        System.out.println("Students enrolled in multiple Courses");
        System.out.println("*************************************");
        int counter = 0;
        for (int i = 0; i < studentsAL.size(); i++) {
            Student currentStudent = studentsAL.get(i);
            System.out.println(currentStudent.getFirstName() + " " + currentStudent.getLastName());
            ArrayList<Course> currentStudentCourses = currentStudent.getCourses();
            if (currentStudentCourses != null && !currentStudentCourses.isEmpty()) {
                System.out.println("Courses: ");
                for (int j = 0; j < currentStudentCourses.size(); j++) {
                    System.out.println(currentStudentCourses.get(j).getTitle());
                }
            }
            System.out.println("");
            counter++;
        }
        System.out.println(counter + " Students found.");
    }

}
