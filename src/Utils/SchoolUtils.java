/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import schoolstructure.Course;
import schoolstructure.Student;
import schoolstructure.StudentAssignment;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that contains helper methods used in the application (e.g. methods that
 * validate, select data etc)
 *
 * @author Nikos Syrios
 */
public class SchoolUtils {

    public static final String CANCEL_COMMAND_STRING = "<X>";
    public static final String RETURN_TO_MENU_COMMAND_STRING = "<R>";
    public static final int CANCEL_COMMAND_INTEGER = Integer.MIN_VALUE;
    public static final int RETURN_TO_MENU_COMMAND_INTEGER = Integer.MIN_VALUE + 197;
    public static final double CANCEL_COMMAND_DOUBLE = Double.MIN_VALUE;
    public static final double RETURN_TO_MENU_COMMAND_DOUBLE = Double.MIN_VALUE + 197.0;
    public static final LocalDate CANCEL_COMMAND_LOCAL_DATE = LocalDate.MAX;
    public static final LocalDate RETURN_TO_MENU_COMMAND_LOCAL_DATE = LocalDate.MAX.minusDays(3);
    public static final Course CANCEL_COMMAND_COURSE = new Course() {
        {
            setTitle(CANCEL_COMMAND_STRING);
        }
    };
    public static final Course RETURN_TO_MENU_COMMAND_COURSE = new Course() {
        {
            setTitle(RETURN_TO_MENU_COMMAND_STRING);
        }
    };
    public static final Student CANCEL_COMMAND_STUDENT = new Student() {
        {
            setFirstName(CANCEL_COMMAND_STRING);
        }
    };
    public static final Student RETURN_TO_MENU_COMMAND_STUDENT = new Student() {
        {
            setFirstName(RETURN_TO_MENU_COMMAND_STRING);
        }
    };
    public static final ArrayList<Course> CANCEL_COMMAND_COURSES_ARRAY_LIST = new ArrayList<Course>() {
        {
            add(CANCEL_COMMAND_COURSE);
        }
    };
    public static final ArrayList<Course> RETURN_TO_MENU_COMMAND_COURSES_ARRAY_LIST = new ArrayList<Course>() {
        {
            add(RETURN_TO_MENU_COMMAND_COURSE);
        }
    };
    public static final ArrayList<Student> CANCEL_COMMAND_STUDENTS_ARRAY_LIST = new ArrayList<Student>() {
        {
            add(CANCEL_COMMAND_STUDENT);
        }
    };
    public static final ArrayList<Student> RETURN_TO_MENU_COMMAND_STUDENTS_ARRAY_LIST = new ArrayList<Student>() {
        {
            add(RETURN_TO_MENU_COMMAND_STUDENT);
        }
    };
    private static final Scanner SC = new Scanner(System.in, "utf-8");

    /**
     * This is just a yes/no dialogue (polar dialogue). Upon calling it, it
     * waits for user inputs y or n and then checks the input. It also checks
     * for wrong input and creates new dialogue until the user uses one of the
     * options.
     *
     * @return true(yes) or false(no) depending on user input
     */
    public static boolean getPolarDialogue() {
        boolean isInvalidInput = true;
        boolean result = false;
        while (isInvalidInput) {
            if (SC.hasNextLine()) {
                String currentInput = SC.nextLine();
                if (currentInput.equals("Y") || currentInput.equals("y")) {
                    result = true;
                    isInvalidInput = false;
                } else if (currentInput.equals("N") || currentInput.equals("n")) {
                    result = false;
                    isInvalidInput = false;
                } else {
                    System.out.println("Please type y for yes and n for no.");
                }
            }
        }
        return result;
    }

    /**
     * It gets the user input, checks if it is a valid String and then it
     * Validates the characters to a regex that contains only letters and
     * characters (' -).
     * <p>
     * If the String does not pass the aformentioned parameters then it asks for
     * a different input until successful user input
     *
     * @return the validated String
     */
    public static String getCharacterValidatedString() {
        String inputString = "";
        boolean isInputInvalid = true;
        while (isInputInvalid) {
            inputString = getStringInput();
            if (isStringCharacterValidated(inputString)
                    || inputString.equalsIgnoreCase(CANCEL_COMMAND_STRING)
                    || inputString.equalsIgnoreCase(RETURN_TO_MENU_COMMAND_STRING)) {
                isInputInvalid = false;
            } else {
                System.out.println("Please use only the following: letters and characters(' -)");
            }
        }
        return inputString;
    }

    /**
     * This method uses regex to check whether a String is valid or not. Valid
     * String can contain letters and a limited array of special characters. It
     * is used primarily to Validate user input when inserting people names.
     * <p>
     * Netbeans default encoding for System.in is not UTF-8. Change
     * netbeans.config file, adding "-J-Dfile.encoding=UTF-8" in the line
     * starting with "netbeans_default_options"
     * <p>
     * \p{InGreek} - Character in the Greek Block (α,Ω,φ,ς..)
     * \p{InGreekExtended} - Character in the Greek Extended Block
     * (Ἐ,ἠ,ᾠ,ᾰ,ῢ...) \p{Lu} - Uppercase letter \p{IsAlphabetic} - An alphabetic
     * character (binary property) \s - A whitespace character: ( \t\n\x0B\f\r )
     * \' - Character (') \- - Character (-) + - One or more character of the
     * preceding token (Used to extend the length of the String it checks. We
     * need one or more characters)
     *
     * @param inputString
     * @return boolean TRUE if it matches the Regex or FALSE if it does not
     */
    private static boolean isStringCharacterValidated(String inputString) {
        String pattern = "[\\p{InGreek}\\p{InGreekExtended}\\p{Lu}\\p{IsAlphabetic}\\s\\'\\-]+";
        return inputString.matches(pattern);
    }

    /**
     * Gets the user input and verifies that it is a valid String.
     *
     * @return the validated String the user typed
     */
    public static String getStringInput() {
        boolean isInvalidInput = true;
        String result = "";
        while (isInvalidInput) {
            if (SC.hasNextLine()) {
                result = SC.nextLine();
                if (result.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_STRING;
                } else if (result.toUpperCase().contains(RETURN_TO_MENU_COMMAND_STRING)) {
                    return RETURN_TO_MENU_COMMAND_STRING;
                } else {
                    break;
                }
            } else {
                System.out.println("Please try again");
            }
        }
        return result;
    }

    /**
     * Gets the user input and verifies that it is a valid integer. If it is not
     * then the user is prompted to try again.
     *
     * @return the validated Integer the user typed
     */
    public static int getIntInput() {
        boolean isInvalidInput = true;
        int result = -1;
        while (isInvalidInput) {
            if (SC.hasNextInt()) {
                result = SC.nextInt();
                SC.nextLine();
                isInvalidInput = false;
            } else if (SC.hasNextLine()) {
                String currentInput = SC.nextLine();
                if (currentInput.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_INTEGER;
                } else if (currentInput.toUpperCase().contains(RETURN_TO_MENU_COMMAND_STRING)) {
                    return RETURN_TO_MENU_COMMAND_INTEGER;
                } else {
                    System.out.println("Please type a valid integer");
                }
            }
        }
        return result;
    }

    /**
     * Gets the user input and verifies that it is a valid integer and it is
     * within a range. Greater than or equal to the start parameter (inclusive)
     * and lower than the end parameter (exclusive).
     *
     * @param start of the range (inclusive)
     * @param end   of the range (exclusive)
     * @return the validated integer that the user typed
     */
    public static int getIntInput(int start, int end) {
        boolean isInvalidInput = true;
        int result = -1;
        while (isInvalidInput) {
            if (SC.hasNextInt()) {
                int currentUserInput = SC.nextInt();
                SC.nextLine();
                if (currentUserInput >= start && currentUserInput < end) {
                    result = currentUserInput;
                    break;
                } else {
                    System.out.println("Please type a number in the specified range");
                }
            } else if (SC.hasNextLine()) {
                String currentInput = SC.nextLine();
                if (currentInput.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_INTEGER;
                } else if (currentInput.toUpperCase().contains(RETURN_TO_MENU_COMMAND_STRING)) {
                    return RETURN_TO_MENU_COMMAND_INTEGER;
                } else {
                    System.out.println("Please type a valid integer");
                }
            }
        }
        return result;
    }

    /**
     * Gets the user input and verifies that it is a valid double.
     *
     * @return the validated double that the user typed
     */
    public static double getDoubleInput() {
        boolean isInvalidInput = true;
        double result = -1.0;
        while (isInvalidInput) {
            if (SC.hasNextDouble()) {
                result = SC.nextDouble();
                SC.nextLine();
                isInvalidInput = false;
            } else if (SC.hasNextLine()) {
                String currentInput = SC.nextLine();
                if (currentInput.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_DOUBLE;
                } else if (currentInput.toUpperCase().contains(RETURN_TO_MENU_COMMAND_STRING)) {
                    return RETURN_TO_MENU_COMMAND_DOUBLE;
                } else {
                    System.out.println("Please type a valid number");
                }
            }
        }
        return result;
    }

    /**
     * Dialogue to transform user input to a valid LocalDate date format.
     *
     * @return the validated LocalDate the user typed
     */
    public static LocalDate getDate() {
        boolean isInputInvalid = true;
        LocalDate userDateLD = LocalDate.MIN;
        int counter = 0;
        while (isInputInvalid && counter < 3) {
            System.out.println("Please insert the date in format DD-MM-YYYY, ex. 27-01-2020");
            if (SC.hasNextLine()) {
                String currentInputString = SC.nextLine();
                if (currentInputString.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_LOCAL_DATE;
                } else if (currentInputString.toUpperCase().contains(RETURN_TO_MENU_COMMAND_STRING)) {
                    return RETURN_TO_MENU_COMMAND_LOCAL_DATE;
                }
                if (!currentInputString.isEmpty() && currentInputString.contains("-")) {
                    String dateString[] = currentInputString.trim().split("-");
                    int day, month, year;
                    if (dateString.length == 3) {
                        try {
                            day = Integer.parseInt(dateString[0]);
                            month = Integer.parseInt(dateString[1]);
                            year = Integer.parseInt(dateString[2]);
                        } catch (NumberFormatException nfe) {
                            System.out.println("Invalid date. Please try again.");
                            counter++;
                            continue;
                        }
                    } else {
                        System.out.println("Invalid date. Please try again.");
                        counter++;
                        continue;
                    }

                    if (day <= 31 && day > 0
                            && month > 0 && month <= 12
                            && year > Year.MIN_VALUE && year < Year.MAX_VALUE) {
                        try {
                            userDateLD = LocalDate.of(year, month, day);
                            isInputInvalid = false;
                        } catch (DateTimeException dte) {
                            System.out.println("Date cannot exist for this year. Please check your calendar and try again.");
                            counter++;
                        }
                    } else {
                        System.out.println("Invalid date. Please try again.");
                        counter++;
                    }
                } else {
                    System.out.println("Invalid date. Please try again.");
                    counter++;
                }
            }
        }
        while (counter > 2) {
            System.out.println("Insert the day (1-31)");
            int day = SchoolUtils.getIntInput(1, 32);
            if (day == CANCEL_COMMAND_INTEGER) {
                return CANCEL_COMMAND_LOCAL_DATE;
            }
            System.out.println("Insert the month (1-12)");
            int month = SchoolUtils.getIntInput(1, 13);
            if (month == CANCEL_COMMAND_INTEGER) {
                return CANCEL_COMMAND_LOCAL_DATE;
            }
            System.out.println("Insert the year");
            int year = SchoolUtils.getIntInput();
            if (year == CANCEL_COMMAND_INTEGER) {
                return CANCEL_COMMAND_LOCAL_DATE;
            }
            try {
                userDateLD = LocalDate.of(year, month, day);
                break;
            } catch (DateTimeException dte) {
                System.out.format("Date %02d-%02d-%04d cannot exist for this year. Please check your calendar and try again.",
                        day, month, year);
                counter++;
            }
        }
        return userDateLD;
    }

    /**
     * Dialogue to verify that the user types a date that is chronologically
     * after the startDate.
     *
     * @param startDate the date we will validate upon user input.
     * @return the date that is chronologically after the startDate or the
     * CANCEL_COMMAND_LOCAL_DATE
     */
    public static LocalDate getEndDate(LocalDate startDate) {
        boolean isEndDateBeforeStartDate = true;
        LocalDate endDate = LocalDate.MIN;
        while (isEndDateBeforeStartDate) {
            endDate = getDate();
            if (endDate == CANCEL_COMMAND_LOCAL_DATE) {
                isEndDateBeforeStartDate = false;
            } else if (endDate.isAfter(startDate)) {
                isEndDateBeforeStartDate = false;
            } else {
                System.out.println("Please choose a date after the starting date.");
            }
        }
        return endDate;
    }

    /**
     * Dialogue that prints all Course titles and waits for the user to choose
     * one by typing the respective number.
     *
     * @param coursesAl the Course ArrayList from which it will get the Courses
     *                  and print their titles
     * @return the integer Id of the course, the user has chosen
     */
    public static Course selectCourse(ArrayList<Course> coursesAl) {

        System.out.println("Type the number of the Course you want to choose");
        boolean isInputInvalid = true;
        while (isInputInvalid) {
            System.out.println("----------------------------------");
            for (int i = 0; i < coursesAl.size(); i++) {
                System.out.println((i + 1) + " - " + coursesAl.get(i).getTitle());
            }

            if (SC.hasNextInt()) {
                int currentUserInput = SC.nextInt();
                if (currentUserInput > 0 && currentUserInput <= coursesAl.size()) {
                    return coursesAl.get(currentUserInput - 1);

                } else {
                    isInputInvalid = true;
                    System.out.println("Please use one of the numbers below");
                }
            } else if (SC.hasNextLine()) {
                String currentUserInString = SC.nextLine();
                if (currentUserInString.toUpperCase().equals(SchoolUtils.CANCEL_COMMAND_STRING)) {
                    return SchoolUtils.CANCEL_COMMAND_COURSE;
                } else if (currentUserInString.toUpperCase().equals(SchoolUtils.RETURN_TO_MENU_COMMAND_STRING)) {
                    return SchoolUtils.RETURN_TO_MENU_COMMAND_COURSE;
                }
            } else {
                isInputInvalid = true;
                SC.nextLine();
                System.out.println("Please insert the number next to the option you want to choose");
            }
        }

        SC.nextLine();
        return new Course();
    }

    /**
     * Dialogue that prints all Course titles and waits for the user to choose
     * one or multiple
     *
     * @param coursesAL the Course ArrayList from which it will get the Courses
     *                  and print their titles
     * @return the Course ArrayList that contains one or more Course objects
     * that the user has chosen
     */
    public static ArrayList<Course> selectCourses(ArrayList<Course> coursesAL) {

        ArrayList<Course> selectedCoursesAL = new ArrayList<>();

        boolean isInputInvalid = true;
        while (isInputInvalid) {
            System.out.println("----------------------------------");
            int counter = 1;
            for (int i = 0; i < coursesAL.size(); i++) {
                System.out.println((counter) + " - " + coursesAL.get(i).getTitle());
                counter++;
            }
            if (SC.hasNextLine()) {
                String currentUserInString = SC.nextLine();

                if (!currentUserInString.isEmpty() && currentUserInString.toUpperCase().contains(CANCEL_COMMAND_STRING)) {
                    return CANCEL_COMMAND_COURSES_ARRAY_LIST;
                } else if (!currentUserInString.isEmpty() && currentUserInString.toUpperCase().contains(RETURN_TO_MENU_COMMAND_STRING)) {
                    return RETURN_TO_MENU_COMMAND_COURSES_ARRAY_LIST;
                }
                if (!currentUserInString.isEmpty() && currentUserInString.contains(",")) {
                    String coursesString[] = currentUserInString.split(",");
                    for (int i = 0; i < coursesString.length; i++) {
                        try {
                            int currentCourse = Integer.parseInt(coursesString[i]);
                            if (currentCourse > 0 && currentCourse < counter) {
                                selectedCoursesAL.add(coursesAL.get(currentCourse - 1));
                            } else {
                                System.out.println("Please insert the number next to the option you want to choose");
                                break;
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Please use only numbers seperated by comma");
                        }
                    }
                    isInputInvalid = false;
                } else if (!currentUserInString.isEmpty()) {
                    try {
                        int currentUserInInt = Integer.parseInt(currentUserInString);
                        if (currentUserInInt > 0 && currentUserInInt < counter) {
                            selectedCoursesAL.add(coursesAL.get(currentUserInInt - 1));
                            isInputInvalid = false;
                        } else {
                            isInputInvalid = true;
                            System.out.println("Please insert the number next to the option you want to choose");
                        }
                    } catch (NumberFormatException nfe) {
                        isInputInvalid = true;
                        System.out.println("Please insert the number next to the option you want to choose");
                    }

                }
            } else {
                isInputInvalid = true;
                System.out.println("Please insert the number next to the option you want to choose");
            }
        }
        return selectedCoursesAL;
    }

    /**
     * Dialogue that prints all Students titles and waits for the user to choose
     * one by typing the respective number.
     *
     * @param studentAL the Student ArrayList from which it will get the
     *                  Students and print their names
     * @return the integer Id of the student, the user has chosen
     */
    public static Student selectStudent(ArrayList<Student> studentAL) {

        System.out.println("Type the number of the Student you want to choose");
        boolean isInputInvalid = true;
        while (isInputInvalid) {
            System.out.println("----------------------------------");
            for (int i = 0; i < studentAL.size(); i++) {
                System.out.println((i + 1) + " - " + studentAL.get(i).getLastName() + " " + studentAL.get(i).getFirstName());
            }

            if (SC.hasNextInt()) {
                int currentUserInput = SC.nextInt();
                SC.nextLine();
                if (currentUserInput > 0 && currentUserInput <= studentAL.size()) {
                    return studentAL.get(currentUserInput - 1);

                } else {
                    isInputInvalid = true;
                    System.out.println("Please use one of the numbers below");
                }
            } else {
                isInputInvalid = true;
                SC.nextLine();
                System.out.println("Please insert the number next to the option you want to choose");
            }
        }
        return new Student();
    }

    /**
     * Dialogue that prints all Assignments titles and waits for the user to
     * choose one by typing the respective number.
     *
     * @param studentAssignmentAL the StudentAssignment ArrayList from which it
     *                            will get the Students and print their names
     * @return the integer Id of the StudentAssignment, the user has chosen
     */
    public static StudentAssignment selectStudentAssignment(ArrayList<StudentAssignment> studentAssignmentAL) {

        System.out.println("Type the number of the Assignment you want to change");
        boolean isInputInvalid = true;
        while (isInputInvalid) {
            System.out.println("----------------------------------");
            for (int i = 0; i < studentAssignmentAL.size(); i++) {
                System.out.println((i + 1) + " - " + studentAssignmentAL.get(i).getTitle());
            }

            if (SC.hasNextInt()) {
                int currentUserInput = SC.nextInt();
                SC.nextLine();
                if (currentUserInput > 0 && currentUserInput <= studentAssignmentAL.size()) {
                    return studentAssignmentAL.get(currentUserInput - 1);

                } else {
                    isInputInvalid = true;
                    System.out.println("Please use one of the numbers below");
                }
            } else {
                isInputInvalid = true;
                SC.nextLine();
                System.out.println("Please insert the number next to the option you want to choose");
            }
        }
        return new StudentAssignment();
    }

}
