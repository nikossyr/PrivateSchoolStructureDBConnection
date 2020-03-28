/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import Utils.DBUtils;
import Utils.SchoolUtils;
import schoolstructure.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class that contains the methods to generate a Menu that gives the option to
 * the user to Add more attributes.
 *
 * @author Nikos Syrios
 */
public class DataInsertMenu {

    private static final Scanner SC = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.UK);
    private static final String RESET_OR_RETURN_SHORTCUT_MESSAGE_STRING = "You can always type \""
            + SchoolUtils.CANCEL_COMMAND_STRING + "\" to start over \n"
            + "or \"" + SchoolUtils.RETURN_TO_MENU_COMMAND_STRING + "\" to return to menu.";
    private static ArrayList<Course> coursesAL;
    private static ArrayList<Trainer> trainerAL;
    private static ArrayList<Student> studentAL;
    private static ArrayList<StudentAssignment> assignmentAL;

    /**
     * Main method used to generate the Menu and Dialogue. It gives the user the
     * option to Add more attributes. It also generates all the following
     * dialogues needed to add all info in all attribute Object types. Then it
     * stores them in an ArrayList of all ArrayList of each Object type.
     * <p>
     * It takes no parameter. It is used when there are no data already typed.
     *
     * @return the ArrayList of all ArrayLists of all Object Types (Course,
     * Trainer, Student, Assignment with that order)
     */
    public static ArrayList<ArrayList> generateMenu() {

        coursesAL = new ArrayList<>();
        trainerAL = new ArrayList<>();
        studentAL = new ArrayList<>();
        assignmentAL = new ArrayList<>();

        return printMenu();
    }

    /**
     * Main method used to generate the Menu and Dialogue. It gives the user the
     * option to Add more attributes. It also generates all the following
     * dialogues needed to add all info in all attribute Object types. Then it
     * stores them in an ArrayList of all ArrayList of each Object type.
     * <p>
     * It is used when there are already data.
     *
     * @param schoolAttrAL the ArrayList of ArrayLists of all Object Types
     *                     (Course, Trainer, Student, Assignment with that order)
     * @return the ArrayList of all ArrayLists of all Object Types (Course,
     * Trainer, Student, Assignment with that order)
     */
    public static ArrayList<ArrayList> generateMenu(ArrayList<ArrayList> schoolAttrAL) {

        coursesAL = schoolAttrAL.get(0);
        trainerAL = schoolAttrAL.get(1);
        studentAL = schoolAttrAL.get(2);
        assignmentAL = schoolAttrAL.get(3);

        return printMenu();
    }

    private static ArrayList<ArrayList> printMenu() {

        boolean hasNextInput = true;
        while (hasNextInput) {
            System.out.println("What would you like to add next?");
            System.out.println("==================================");
            System.out.println("1 - Courses");
            System.out.println("2 - Trainers");
            System.out.println("3 - Students");
            System.out.println("4 - Assignements");
            System.out.println("----------------------------------");
            System.out.println("0 - Return to previous menu screen");
            System.out.println("----------------------------------");

            if (SC.hasNextInt()) {
                int userInput = SC.nextInt();
                SC.nextLine();
                switch (userInput) {
                    case 0:
                        hasNextInput = false;
                        DataViewAddMenu.generateMenu();
                        break;
                    case 1:
                        insertCourseInfo();
                        coursesAL.clear();
                        hasNextInput = true;
                        break;
                    case 2:
                        insertTrainerInfo();
                        trainerAL.clear();
                        hasNextInput = true;
                        break;
                    case 3:
                        insertStudentInfo();
                        studentAL.clear();
                        hasNextInput = true;
                        break;
                    case 4:
                        insertAssignementInfo();
                        assignmentAL.clear();
                        hasNextInput = true;
                        break;
                    default:
                        hasNextInput = true;
                        System.out.println("Please insert the number next to the option you want to choose");
                        break;
                }
            } else {
                System.out.println("Please use only Numbers to navigate");
                SC.nextLine();
            }
        }
        System.out.print(coursesAL + "\n" + trainerAL + "\n" + studentAL + "\n" + assignmentAL + "\n");
        ArrayList<ArrayList> schoolDataAL = new ArrayList<ArrayList>() {
            {
                add(coursesAL);
                add(trainerAL);
                add(studentAL);
                add(assignmentAL);
            }
        };
        return schoolDataAL;
    }

    private static void insertCourseInfo() {

        boolean hasNextEntry = true;
        boolean isInputInvalid = true;
        while (hasNextEntry) {
            while (isInputInvalid) {
                String courseTitle;
                String courseStream;
                String courseType;
                LocalDate courseStartDate;
                LocalDate courseEndDate;
                System.out.println(RESET_OR_RETURN_SHORTCUT_MESSAGE_STRING);
                System.out.println("----------------------------------");
                System.out.println("Type the Title of the Course");
                courseTitle = SchoolUtils.getStringInput();
                if (courseTitle.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                if (courseTitle.equalsIgnoreCase(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Stream of the Course");
                courseStream = SchoolUtils.getStringInput();
                if (courseStream.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                if (courseStream.equalsIgnoreCase(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Type of the Course");
                courseType = SchoolUtils.getStringInput();
                if (courseType.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                if (courseType.equalsIgnoreCase(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Date the Course Starts");
                courseStartDate = SchoolUtils.getDate();
                if (courseStartDate.equals(SchoolUtils.CANCEL_COMMAND_LOCAL_DATE)) {
                    break;
                }
                if (courseStartDate.equals(SchoolUtils.RETURN_TO_MENU_COMMAND_LOCAL_DATE)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Date the Course Ends");
                courseEndDate = SchoolUtils.getEndDate(courseStartDate);
                if (courseEndDate.equals(SchoolUtils.CANCEL_COMMAND_LOCAL_DATE)) {
                    break;
                }
                if (courseEndDate.equals(SchoolUtils.RETURN_TO_MENU_COMMAND_LOCAL_DATE)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("----------------------------------");

                System.out.format("Course Title: %s\nCourse Stream: %s\nCourse Type: %s\nCourse Duration:\n%s - %s\n",
                        courseTitle, courseStream, courseType, courseStartDate.format(DATE_FORMATTER), courseEndDate.format(DATE_FORMATTER));
                System.out.println("----------------------------------");
                System.out.println("Is this information correct? y/n");
                boolean courseInfoCorrectPolarDialogue = SchoolUtils.getPolarDialogue();
                if (courseInfoCorrectPolarDialogue) {

                    Course currentCourse = new Course();
                    currentCourse.setTitle(courseTitle);
                    currentCourse.setType(courseType);
                    currentCourse.setStream(courseStream);
                    currentCourse.setStartDate(courseStartDate);
                    currentCourse.setEndDate(courseEndDate);
                    if (DBUtils.insertCourse(currentCourse)) {
                        System.out.println("Course saved.");

                    } else {
                        System.out.println("An error has occured while connecting to database.");
                        System.out.println("Please try again.");
                    }
                    isInputInvalid = false;

                } else if (!courseInfoCorrectPolarDialogue) {
                    System.out.println("Please enter the correct information below");
                    System.out.println("------------------------------------");
                    break;
                }
                System.out.println("Do you want to add more Courses? y/n");
                hasNextEntry = SchoolUtils.getPolarDialogue();
            }
        }
    }

    private static void insertTrainerInfo() {
        boolean hasNextEntry = true;
        boolean isInputInvalid = true;
        while (hasNextEntry) {
            while (isInputInvalid) {

                String trainerFirstName;
                String trainerLastName;
                String trainerSubject;
                ArrayList<Course> coursesSelectedAL = new ArrayList<>();

                System.out.println(RESET_OR_RETURN_SHORTCUT_MESSAGE_STRING);
                System.out.println("----------------------------------");
                System.out.println("Type the First Name of the Trainer");
                trainerFirstName = SchoolUtils.getCharacterValidatedString();
                if (trainerFirstName.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                if (trainerFirstName.equalsIgnoreCase(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Last Name of the Trainer");
                trainerLastName = SchoolUtils.getCharacterValidatedString();
                if (trainerLastName.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                if (trainerLastName.equalsIgnoreCase(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Subject the Trainer teaches");
                trainerSubject = SchoolUtils.getStringInput();
                if (trainerSubject.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                if (trainerSubject.equalsIgnoreCase(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Would you like to add the Courses that " + trainerFirstName + " " + trainerLastName + " teaches, now? y/n");
                if (SchoolUtils.getPolarDialogue()) {
                    boolean isCancelTyped = true;
                    while (isCancelTyped) {
                        System.out.println("Select the Course the Trainer teaches. For multiple Courses, seperate the numbers with a comma.");
                        if (coursesAL.isEmpty()) {
                            coursesAL = DBUtils.getCourses();
                        }
                        if (!coursesAL.isEmpty()) {
                            switch (coursesAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("Oops! You need to create some Courses first and add them later!");
                                    System.out.println("");
                                    break;
                                default:
                                    coursesSelectedAL = SchoolUtils.selectCourses(coursesAL);
                                    if (coursesSelectedAL == SchoolUtils.CANCEL_COMMAND_COURSES_ARRAY_LIST) {
                                        isCancelTyped = false;
                                        break;
                                    } else if (coursesSelectedAL == SchoolUtils.RETURN_TO_MENU_COMMAND_COURSES_ARRAY_LIST) {
                                        isCancelTyped = false;
                                        isInputInvalid = false;
                                        break;
                                    }

                            }
                        }
                        break;
                    }
                }

                if (coursesSelectedAL != null
                        && coursesSelectedAL == SchoolUtils.CANCEL_COMMAND_COURSES_ARRAY_LIST) {
                    System.out.println("Nothing was saved! Starting over..");
                    break;
                }
                if (coursesSelectedAL != null
                        && coursesSelectedAL == SchoolUtils.RETURN_TO_MENU_COMMAND_COURSES_ARRAY_LIST) {
                    hasNextEntry = false;
                    System.out.println("Nothing was saved!");
                    break;
                }
                System.out.println("----------------------------------");
                System.out.format("Trainer Name: %s %s\nSubject: %s\n",
                        trainerFirstName, trainerLastName, trainerSubject);
                if (coursesSelectedAL != null) {
                    System.out.println("Courses taught:");
                    coursesSelectedAL.forEach((course) -> {
                        System.out.println(course.getTitle());
                    });
                }
                System.out.println("----------------------------------");
                System.out.println("Is this information correct? y/n");

                boolean trainerInfoCorrectPolarDialogue = SchoolUtils.getPolarDialogue();
                if (trainerInfoCorrectPolarDialogue) {
                    Trainer currentTrainer = new Trainer();
                    currentTrainer.setFirstName(trainerFirstName);
                    currentTrainer.setLastName(trainerLastName);
                    currentTrainer.setSubject(trainerSubject);
                    currentTrainer.setCourses(coursesSelectedAL);
                    if (DBUtils.insertTrainer(currentTrainer)) {
                        System.out.println("Trainer saved.");

                    } else {
                        System.out.println("An error has occured while connecting to database.");
                        System.out.println("Please try again.");
                    }
                    isInputInvalid = false;

                } else if (!trainerInfoCorrectPolarDialogue) {
                    System.out.println("Please enter the correct information below");
                    System.out.println("------------------------------------");
                    break;

                }
                System.out.println("Do you want to add more Trainers? y/n");
                hasNextEntry = SchoolUtils.getPolarDialogue();
            }
        }
    }

    private static void insertStudentInfo() {
        boolean hasNextEntry = true;
        boolean isInputInvalid = true;
        while (hasNextEntry) {
            while (isInputInvalid) {

                String studentFirstName;
                String studentLastName;
                LocalDate studentDateOfBirth;
                double studentTuitionFees;
                ArrayList<Course> coursesSelectedAL = new ArrayList<>();

                System.out.println(RESET_OR_RETURN_SHORTCUT_MESSAGE_STRING);
                System.out.println("----------------------------------");
                System.out.println("Type the First Name of the Student");
                studentFirstName = SchoolUtils.getCharacterValidatedString();
                if (studentFirstName.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                if (studentFirstName.equalsIgnoreCase(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Last Name of the Student");
                studentLastName = SchoolUtils.getCharacterValidatedString();
                if (studentLastName.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                if (studentLastName.equalsIgnoreCase(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Student's Date of Birth");
                studentDateOfBirth = SchoolUtils.getDate();
                if (studentDateOfBirth.equals(SchoolUtils.CANCEL_COMMAND_LOCAL_DATE)) {
                    break;
                }
                if (studentDateOfBirth.equals(SchoolUtils.RETURN_TO_MENU_COMMAND_LOCAL_DATE)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Student's Tuition Fees");
                studentTuitionFees = SchoolUtils.getDoubleInput();
                if (studentTuitionFees == SchoolUtils.CANCEL_COMMAND_DOUBLE) {
                    break;
                }
                if (studentTuitionFees == SchoolUtils.RETURN_TO_MENU_COMMAND_DOUBLE) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Would you like to add the Courses that " + studentFirstName + " " + studentLastName + " attends? y/n");
                if (SchoolUtils.getPolarDialogue()) {
                    boolean isCancelTyped = true;
                    while (isCancelTyped) {
                        System.out.println("Select the Course the Student attends. For multiple Courses, seperate the numbers with a comma.");
                        if (coursesAL.isEmpty()) {
                            coursesAL = DBUtils.getCourses();
                        }
                        if (!coursesAL.isEmpty()) {
                            switch (coursesAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("Oops! You need to create some Courses first and add them later!");
                                    System.out.println("");
                                    break;
                                default:
                                    coursesSelectedAL = SchoolUtils.selectCourses(coursesAL);
                                    if (coursesSelectedAL == SchoolUtils.CANCEL_COMMAND_COURSES_ARRAY_LIST) {
                                        isCancelTyped = false;
                                        break;
                                    } else if (coursesSelectedAL == SchoolUtils.RETURN_TO_MENU_COMMAND_COURSES_ARRAY_LIST) {
                                        isCancelTyped = false;
                                        isInputInvalid = false;
                                        break;
                                    }
                            }
                        }
                        break;
                    }
                }
                if (coursesSelectedAL != null
                        && coursesSelectedAL == SchoolUtils.CANCEL_COMMAND_COURSES_ARRAY_LIST) {
                    System.out.println("Nothing was saved! Starting over..");
                    break;
                }
                if (coursesSelectedAL != null
                        && coursesSelectedAL == SchoolUtils.RETURN_TO_MENU_COMMAND_COURSES_ARRAY_LIST) {
                    hasNextEntry = false;
                    System.out.println("Nothing was saved!");
                    break;
                }
                System.out.println("----------------------------------");
                System.out.format("Student Name: %s %s\nDate Of Birth: %s\nTuition Fees: %s\n",
                        studentFirstName, studentLastName, studentDateOfBirth.format(DATE_FORMATTER), studentTuitionFees);
                if (coursesSelectedAL != null) {
                    System.out.println("Courses attended:");
                    for (Course course : coursesSelectedAL) {
                        System.out.println(course.getTitle());
                    }
                }

                System.out.println("----------------------------------");
                System.out.println("Is this information correct? y/n");

                boolean studentInfoCorrectPolarDialogue = SchoolUtils.getPolarDialogue();
                if (studentInfoCorrectPolarDialogue) {
                    Student currentStudent = new Student();
                    currentStudent.setFirstName(studentFirstName);
                    currentStudent.setLastName(studentLastName);
                    currentStudent.setDateOfBirth(studentDateOfBirth);
                    currentStudent.setFees(studentTuitionFees);
                    if (coursesSelectedAL != null) {
                        currentStudent.setCourses(coursesSelectedAL);
                    }
                    if (DBUtils.insertStudent(currentStudent)) {
                        System.out.println("Student saved.");

                    } else {
                        System.out.println("An error has occured while connecting to database.");
                        System.out.println("Please try again.");
                    }
                    isInputInvalid = false;

                } else if (!studentInfoCorrectPolarDialogue) {
                    System.out.println("Please enter the correct information below");
                    System.out.println("------------------------------------");
                    break;
                }
                System.out.println("Do you want to add more Students? y/n");
                hasNextEntry = SchoolUtils.getPolarDialogue();
            }
        }
    }

    private static void insertAssignementInfo() {
        boolean hasNextEntry = true;
        boolean isInputInvalid = true;
        while (hasNextEntry) {
            while (isInputInvalid) {
                String assignmentTitle;
                String assignmentDescription;
                LocalDate assignmentSubDateTime;
                double assignmentOralMark;
                double assignmentTotalMark;
                Course courseSelected = new Course();

                System.out.println(RESET_OR_RETURN_SHORTCUT_MESSAGE_STRING);
                System.out.println("----------------------------------");
                System.out.println("Type the title of the Assignment");
                assignmentTitle = SchoolUtils.getStringInput();
                if (assignmentTitle.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                if (assignmentTitle.equalsIgnoreCase(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Description of the Assignment");
                assignmentDescription = SchoolUtils.getStringInput();
                if (assignmentDescription.equalsIgnoreCase(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    break;
                }
                if (assignmentDescription.equalsIgnoreCase(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Assignment's Final Submission Date");
                assignmentSubDateTime = SchoolUtils.getDate();
                if (assignmentSubDateTime.equals(SchoolUtils.CANCEL_COMMAND_LOCAL_DATE)) {
                    break;
                }
                if (assignmentSubDateTime.equals(SchoolUtils.RETURN_TO_MENU_COMMAND_LOCAL_DATE)) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Assignment's Oral Mark");
                assignmentOralMark = SchoolUtils.getDoubleInput();
                if (assignmentOralMark == SchoolUtils.CANCEL_COMMAND_DOUBLE) {
                    break;
                }
                if (assignmentOralMark == SchoolUtils.RETURN_TO_MENU_COMMAND_DOUBLE) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Type the Assignment's Total Mark");
                assignmentTotalMark = SchoolUtils.getDoubleInput();
                if (assignmentTotalMark == SchoolUtils.CANCEL_COMMAND_DOUBLE) {
                    break;
                }
                if (assignmentTotalMark == SchoolUtils.RETURN_TO_MENU_COMMAND_DOUBLE) {
                    hasNextEntry = false;
                    break;
                }
                System.out.println("Would you like to add the Course associated with this Assignment? y/n");
                if (SchoolUtils.getPolarDialogue()) {
                    boolean isCancelTyped = true;
                    while (isCancelTyped) {
                        System.out.println("Select the Course of the Assignment. One Assignment per Course is permitted.");
                        if (coursesAL.isEmpty()) {
                            coursesAL = DBUtils.getCourses();
                        }
                        if (!coursesAL.isEmpty()) {
                            switch (coursesAL.get(0).getId()) {
                                case DBUtils.NO_ENTRIES_FOUND_ON_DB:
                                    System.out.println("");
                                    System.out.println("Oops! You need to create some Courses first and add them later!");
                                    System.out.println("");
                                    break;
                                default:
                                    courseSelected = SchoolUtils.selectCourse(coursesAL);
                                    if (courseSelected == SchoolUtils.CANCEL_COMMAND_COURSE) {
                                        isCancelTyped = false;
                                        break;
                                    } else if (courseSelected == SchoolUtils.RETURN_TO_MENU_COMMAND_COURSE) {
                                        isCancelTyped = false;
                                        isInputInvalid = false;
                                        break;
                                    }
                                    break;
                            }
                        }
                    }

                    if (courseSelected != null
                            && courseSelected == SchoolUtils.CANCEL_COMMAND_COURSE) {
                        System.out.println("Nothing was saved! Starting over..");
                        break;
                    }
                    if (courseSelected != null
                            && courseSelected == SchoolUtils.RETURN_TO_MENU_COMMAND_COURSE) {
                        hasNextEntry = false;
                        System.out.println("Nothing was saved!");
                        break;
                    }
                    System.out.println("----------------------------------");
                    System.out.format("Assignment Title: %s\nAssignment Description: %s\nSubmission Date: %s\nOral Mark: %s\nTotal Mark: %s\n",
                            assignmentTitle, assignmentDescription, assignmentSubDateTime.format(DATE_FORMATTER), assignmentOralMark, assignmentTotalMark);

                    if (courseSelected != null) {
                        System.out.println("Course:");
                        System.out.println(courseSelected.getTitle());
                    }
                    System.out.println("----------------------------------");
                    System.out.println("Is this information correct? y/n");

                    boolean assignementInfoCorrectPolarDialogue = SchoolUtils.getPolarDialogue();
                    if (assignementInfoCorrectPolarDialogue) {
                        Assignment currentAssignment = new Assignment();
                        currentAssignment.setTitle(assignmentTitle);
                        currentAssignment.setDescription(assignmentDescription);
                        currentAssignment.setSubDateTime(LocalDateTime.of(assignmentSubDateTime, LocalTime.MAX));
                        currentAssignment.setOralMark(assignmentOralMark);
                        currentAssignment.setTotalMark(assignmentOralMark);
                        if (courseSelected != null
                                && courseSelected != SchoolUtils.CANCEL_COMMAND_COURSE
                                && courseSelected != SchoolUtils.RETURN_TO_MENU_COMMAND_COURSE) {
                            currentAssignment.setCourse(courseSelected);
                        }
                        if (DBUtils.insertAssignment(currentAssignment)) {
                            System.out.println("Assignement saved.");

                        } else {
                            System.out.println("An error has occured while connecting to database.");
                            System.out.println("Please try again.");
                        }
                        isInputInvalid = false;

                    } else if (!assignementInfoCorrectPolarDialogue) {
                        System.out.println("Please enter the correct information below");
                        System.out.println("------------------------------------");
                        break;
                    }
                }
                System.out.println("Do you want to add more Assignements? y/n");
                hasNextEntry = SchoolUtils.getPolarDialogue();
            }
        }
    }

}
