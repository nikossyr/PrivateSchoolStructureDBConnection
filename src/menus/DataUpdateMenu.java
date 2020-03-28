/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import Utils.DBUtils;
import Utils.SchoolUtils;
import schoolstructure.Student;
import schoolstructure.StudentAssignment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that contains the methods to generate a Menu that gives the option to
 * the user to Update the attributes (Work in progress).
 *
 * @author Nikos Syrios
 */
public class DataUpdateMenu {

    static Scanner SC = new Scanner(System.in);

    public static void generateMenu() {

        boolean hasNextInput = true;
        while (hasNextInput) {
            System.out.println("What would you like to do next?");
            boolean isInputInvalid = true;
            while (isInputInvalid) {
                System.out.println("==================================");
                System.out.println("1 - Update the Assignments of a Student ");
                System.out.println("----------------------------------");
                System.out.println("0 - Return to previous menu screen");
                System.out.println("----------------------------------");
                if (SC.hasNextInt()) {
                    int currentUserInput = SC.nextInt();
                    switch (currentUserInput) {
                        case 0:
                            hasNextInput = false;
                            DataViewAddMenu.generateMenu();
                            break;
                        case 1:
                            updateAssignmentsPerStudent();
                            System.out.println("Press ENTER to return to the menu");
                            if (SC.hasNextLine()) {
                                SC.nextLine();
                                break;
                            }
                            hasNextInput = true;
                            break;
                        default:
                            isInputInvalid = true;
                            SC.nextLine();
                            System.out.println("Please insert the number next to the option you want to choose");
                            break;
                    }
                }
            }
        }
    }

    private static void updateAssignmentsPerStudent() {
        ArrayList<StudentAssignment> assignmentsPerStudentAL;
        ArrayList<Student> studentsAL = DBUtils.getStudents();

        boolean hasNextInput = true;
        while (hasNextInput) {
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
                                    StudentAssignment selectedAssignment = SchoolUtils.selectStudentAssignment(assignmentsPerStudentAL);
                                    if (selectedAssignment != null) {

                                        System.out.println("Type the Date the Student submitted the Assignment");
                                        System.out.println("Previous value: " + selectedAssignment.getStudentSubDateTime());
                                        LocalDate assignmentSubDateTime = SchoolUtils.getDate();
                                        System.out.println("Type the Assignment's Oral Mark");
                                        System.out.println("Previous value: " + selectedAssignment.getStudentOralMark());
                                        double assignmentOralMark = SchoolUtils.getDoubleInput();
                                        System.out.println("Type the Assignment's Total Mark");
                                        System.out.println("Previous value: " + selectedAssignment.getStudentTotalMark());
                                        double assignmentTotalMark = SchoolUtils.getDoubleInput();
                                        System.out.println("Are you sure of the changes?");
                                        if (SchoolUtils.getPolarDialogue()) {
                                            selectedAssignment.setAssignmentSubDateTime(assignmentSubDateTime.atStartOfDay());
                                            selectedAssignment.setAssignmentOralMark(assignmentOralMark);
                                            selectedAssignment.setAssignmentTotalMark(assignmentTotalMark);
                                            if (DBUtils.updateAssignmentPerStudent(selectedAssignment, studentSelected)) {
                                                System.out.println("Update complete");
                                            } else {
                                                System.out.println("An error has occured while updating " + selectedAssignment);
                                            }
                                        } else {
                                            System.out.println("Reverting changes");
                                        }
                                    }
                                    break;
                            }
                        }
                        break;
                }

            }
            System.out.println("Do you want to change another Assignment? y/n");
            if (!SchoolUtils.getPolarDialogue()) {
                hasNextInput = false;
                break;
            }
        }
    }
}
