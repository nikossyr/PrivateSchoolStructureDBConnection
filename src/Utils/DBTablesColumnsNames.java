/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 * Table and Column names as Strings for the DB
 *
 * @author Nikos Syrios
 */
class DBTablesColumnsNames {

    // Attribute names
    public final static String STUDENT = "student";
    public final static String TRAINER = "trainer";
    public final static String COURSE = "course";
    public final static String ASSIGNMENT = "assignment";

    // Table names
    public final static String STUDENTS_TABLE_NAME = "STUDENTS";
    public final static String TRAINERS_TABLE_NAME = "TRAINERS";
    public final static String ASSIGNMENTS_TABLE_NAME = "ASSIGNMENTS";
    public final static String COURSES_TABLE_NAME = "COURSES";
    public final static String TRAINERS_PER_COURSE_TABLE_NAME = "TRAINERS_PER_COURSE";
    public final static String STUDENTS_PER_COURSE_TABLE_NAME = "STUDENTS_PER_COURSE";
    public final static String ASSIGNMENTS_PER_STUDENT_TABLE_NAME = "ASSIGNMENTS_PER_STUDENT";

    //Column names for Assignments_per_student table
    public final static String APS_ASSIGNMENT_PER_STUDENT_ID = "ASSIGNMENT_PER_STUDENT_ID";
    public final static String APS_STUDENTS_ID = "STUDENT_ID";
    public final static String APS_ASSIGNMENT_ID = "ASSIGNMENT_ID";
    public final static String APS_STUDENT_SUB_DATE_TIME = "STUDENT_SUB_DATE_TIME";
    public final static String APS_STUDENT_ORAL_MARK = "STUDENT_ORAL_MARK";
    public final static String APS_STUDENT_TOTAL_MARK = "STUDENT_TOTAL_MARK";

    // Column names for Students table
    public final static String STUDENTS_ID = "STUDENT_ID";
    public final static String STUDENTS_FIRST_NAME = "FIRST_NAME";
    public final static String STUDENTS_LAST_NAME = "LAST_NAME";
    public final static String STUDENTS_DATE_OF_BIRTH = "DATE_OF_BIRTH";
    public final static String STUDENTS_TUITION_FEES = "TUITION_FEES";

    // Column names for Trainers table
    public final static String TRAINERS_ID = "TRAINER_ID";
    public final static String TRAINERS_FIRST_NAME = "FIRST_NAME";
    public final static String TRAINERS_LAST_NAME = "LAST_NAME";
    public final static String TRAINERS_SUBJECT = "SUBJECT";

    // Column names for Assignments table
    public final static String ASSIGNMENTS_ID = "ASSIGNMENT_ID";
    public final static String ASSIGNMENTS_TITLE = "ASSIGNMENT_TITLE";
    public final static String ASSIGNMENTS_DESCRIPTION = "ASSIGNMENT_DESCRIPTION";
    public final static String ASSIGNMENTS_SUB_DATE_TIME = "SUB_DATE_TIME";
    public final static String ASSIGNMENTS_ORAL_MARK = "ORAL_MARK";
    public final static String ASSIGNMENTS_TOTAL_MARK = "TOTAL_MARK";

    // Column names for Courses table
    public final static String COURSES_ID = "COURSE_ID";
    public final static String COURSES_TITLE = "COURSE_TITLE";
    public final static String COURSES_STREAM = "COURSE_STREAM";
    public final static String COURSES_TYPE = "COURSE_TYPE";
    public final static String COURSES_START_DATE = "START_DATE";
    public final static String COURSES_END_DATE = "END_DATE";

    //Column names for Trainers_per_course Table
    public final static String TRAINERS_PC_TRAINER_ID = "TRAINER_ID";
    public final static String TRAINERS_PC_COURSE_ID = "COURSE_ID";

    //Column names for Assignments_per_student Table
    public final static String ASSIGNMENTS_PER_STUDENT_SUB_DATE_TIME = "STUDENT_SUB_DATE_TIME";
    public final static String ASSIGNMENTS_PER_STUDENT_ORAL_MARK = "STUDENT_ORAL_MARK";
    public final static String ASSIGNMENTS_PER_STUDENT_TOTAL_MARK = "STUDENT_TOTAL_MARK";

}
