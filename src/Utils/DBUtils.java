/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import schoolstructure.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Utils.DBTablesColumnsNames.*;

/**
 * Class containing database connectivity Utilities
 *
 * @author Nikos Syrios
 */
public class DBUtils {

    public final static int NO_ENTRIES_FOUND_ON_DB = -1500;
    private final static String DB_USER = "root";
    private final static String DB_PASS = "****";
    private final static String DB_NAME = "private_school";
    private final static String DB_URL = "jdbc:mysql://localhost/" + DB_NAME;
    private final static String DB_UPDATE_QUERY = "UPDATE %s SET %s";
    private final static String DB_SELECT_QUERY = "SELECT %s FROM %s;";
    private final static String DB_INSERT_QUERY = "INSERT INTO %s VALUES %s;";
    private final static String ASTERISC = "*";

    public static ArrayList<Student> getStudents() {
        ArrayList<Student> studentsAL = new ArrayList<>();
        String getStudentsQuery = String.format(DB_SELECT_QUERY, ASTERISC, STUDENTS_TABLE_NAME);
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selectStudentsPS
                     = con.prepareStatement(getStudentsQuery);
             ResultSet rs = selectStudentsPS.executeQuery();) {
            if (rs.next()) {
                do {
                    int currentStudentID = rs.getInt(STUDENTS_ID);
                    String currentStudentFirstName = rs.getString(STUDENTS_FIRST_NAME);
                    String currentStudentLastName = rs.getString(STUDENTS_LAST_NAME);
                    Date currentStudentDateOfBirth = rs.getDate(STUDENTS_DATE_OF_BIRTH);
                    LocalDate currentStudentDateOfBirthLD = currentStudentDateOfBirth.toLocalDate();
                    float currentStudentTuitionFees = rs.getFloat(STUDENTS_TUITION_FEES);
                    studentsAL.add(new Student(currentStudentID, currentStudentFirstName, currentStudentLastName,
                            currentStudentDateOfBirthLD, currentStudentTuitionFees));
                } while (rs.next());
            } else {
                studentsAL = new ArrayList<Student>() {
                    {
                        add(new Student(NO_ENTRIES_FOUND_ON_DB, null, null, null, 0));
                    }
                };
                return studentsAL;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentsAL;
    }

    public static ArrayList<Trainer> getTrainers() {
        ArrayList<Trainer> trainersAL = new ArrayList<>();
        String getTrainersQuery = String.format(DB_SELECT_QUERY, ASTERISC, TRAINERS_TABLE_NAME);
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selectTrainersPS
                     = con.prepareStatement(getTrainersQuery);
             ResultSet rs = selectTrainersPS.executeQuery();) {
            if (rs.next()) {
                do {
                    int currentTrainerID = rs.getInt(TRAINERS_ID);
                    String currentTrainerFirstName = rs.getString(TRAINERS_FIRST_NAME);
                    String currentTrainerLastName = rs.getString(TRAINERS_LAST_NAME);
                    String currentTrainerSubject = rs.getString(TRAINERS_SUBJECT);
                    trainersAL.add(new Trainer(currentTrainerID, currentTrainerFirstName, currentTrainerLastName,
                            currentTrainerSubject));
                } while (rs.next());
            } else {
                trainersAL = new ArrayList<Trainer>() {
                    {
                        add(new Trainer(NO_ENTRIES_FOUND_ON_DB, null, null, null));
                    }
                };
                return trainersAL;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainersAL;
    }

    public static ArrayList<Assignment> getAssignments() {
        ArrayList<Assignment> assignmentsAL = new ArrayList<>();
        String getAssignmentsQuery = String.format(DB_SELECT_QUERY, ASTERISC, ASSIGNMENTS_TABLE_NAME);
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selectAssignmentsPS
                     = con.prepareStatement(getAssignmentsQuery);
             ResultSet rs = selectAssignmentsPS.executeQuery();) {
            if (rs.next()) {
                do {
                    int currentAssignmentID = rs.getInt(ASSIGNMENTS_ID);
                    String currentAssignmentTitle = rs.getString(ASSIGNMENTS_TITLE);
                    String currentAssignmentDescription = rs.getString(ASSIGNMENTS_DESCRIPTION);
                    Timestamp currentAssignmentSubDatetime = rs.getTimestamp(ASSIGNMENTS_SUB_DATE_TIME);
                    LocalDateTime currentStudentDateOfBirthLD = currentAssignmentSubDatetime.toLocalDateTime();
                    float currentAssignmentOralMark = rs.getFloat(ASSIGNMENTS_ORAL_MARK);
                    float currentAssignmentTotalMark = rs.getFloat(ASSIGNMENTS_TOTAL_MARK);
                    assignmentsAL.add(new Assignment(currentAssignmentID, currentAssignmentTitle, currentAssignmentDescription,
                            currentStudentDateOfBirthLD, currentAssignmentOralMark, currentAssignmentTotalMark));
                } while (rs.next());
            } else {
                assignmentsAL = new ArrayList<Assignment>() {
                    {
                        add(new Assignment(NO_ENTRIES_FOUND_ON_DB, null, null, null, 0, 0));
                    }
                };
                return assignmentsAL;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assignmentsAL;
    }

    public static ArrayList<Course> getCourses() {
        ArrayList<Course> coursesAL = new ArrayList<>();
        String getCoursesQuery = String.format(DB_SELECT_QUERY, ASTERISC, COURSES_TABLE_NAME);
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selectAssignmentsPS
                     = con.prepareStatement(getCoursesQuery);
             ResultSet rs = selectAssignmentsPS.executeQuery();) {
            if (rs.next()) {
                coursesAL = new ArrayList<>();
                do {
                    int currentCourseID = rs.getInt(COURSES_ID);
                    String currentCourseTitle = rs.getString(COURSES_TITLE);
                    String currentCourseStream = rs.getString(COURSES_STREAM);
                    String currentCourseType = rs.getString(COURSES_TYPE);
                    Date currentCourseStartDate = rs.getDate(COURSES_START_DATE);
                    LocalDate currentCourseStartDateLD = currentCourseStartDate.toLocalDate();
                    Date currentCourseEndDate = rs.getDate(COURSES_END_DATE);
                    LocalDate currentCourseEndDateLD = currentCourseEndDate.toLocalDate();
                    Course currentCourse = new Course(currentCourseID, currentCourseTitle, currentCourseStream,
                            currentCourseType, currentCourseStartDateLD, currentCourseEndDateLD);
                    coursesAL.add(currentCourse);
                } while (rs.next());

            } else {
                coursesAL = new ArrayList<Course>() {
                    {
                        add(new Course(NO_ENTRIES_FOUND_ON_DB, null, null, null, null, null));
                    }
                };
                return coursesAL;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Course course : coursesAL) {
            int currentCourseId = course.getId();
            course.setAssignmnentsAL(getAssignmentsPerCourse(currentCourseId));
        }
        return coursesAL;
    }

    public static Course getCourse(int course_id) {
        Course course = new Course();
        String getCourseQuery = String.format(DB_SELECT_QUERY, ASTERISC, COURSES_TABLE_NAME + "WHERE COURSE_ID = ?");
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selectAssignmentsPS
                     = con.prepareStatement(getCourseQuery);) {
            selectAssignmentsPS.setInt(1, course_id);
            try (ResultSet rs = selectAssignmentsPS.executeQuery();) {
                if (rs.next()) {
                    do {
                        int currentCourseID = rs.getInt(COURSES_ID);
                        String currentCourseTitle = rs.getString(COURSES_TITLE);
                        String currentCourseStream = rs.getString(COURSES_STREAM);
                        String currentCourseType = rs.getString(COURSES_TYPE);
                        Date currentCourseStartDate = rs.getDate(COURSES_START_DATE);
                        LocalDate currentCourseStartDateLD = currentCourseStartDate.toLocalDate();
                        Date currentCourseEndDate = rs.getDate(COURSES_END_DATE);
                        LocalDate currentCourseEndDateLD = currentCourseEndDate.toLocalDate();
                        course = new Course(currentCourseID, currentCourseTitle, currentCourseStream,
                                currentCourseType, currentCourseStartDateLD, currentCourseEndDateLD);
                    } while (rs.next());
                } else {
                    return new Course(NO_ENTRIES_FOUND_ON_DB, null, null, null, null, null);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

    public static ArrayList<Student> getStudentsPerCourse(int course_id) {
        ArrayList<Student> studentsAL = new ArrayList<>();
        String select_students_per_course
                = String.format(DB_SELECT_QUERY, ASTERISC,
                "STUDENTS S"
                        + "INNER JOIN STUDENTS_PER_COURSE SPC USING (STUDENT_ID)"
                        + "INNER JOIN COURSES C USING (COURSE_ID)"
                        + "WHERE COURSE_ID = ?;");
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selStudentsPCPS = con.prepareStatement(select_students_per_course);) {
            selStudentsPCPS.setInt(1, course_id);
            try (ResultSet rs = selStudentsPCPS.executeQuery();) {
                if (rs.next()) {
                    do {
                        int currentStudentID = rs.getInt(STUDENTS_ID);
                        String currentStudentFirstName = rs.getString(STUDENTS_FIRST_NAME);
                        String currentStudentLastName = rs.getString(STUDENTS_LAST_NAME);
                        Date currentStudentDateOfBirth = rs.getDate(STUDENTS_DATE_OF_BIRTH);
                        LocalDate currentStudentDateOfBirthLD = currentStudentDateOfBirth.toLocalDate();
                        float currentStudentTuitionFees = rs.getFloat(STUDENTS_TUITION_FEES);
                        studentsAL.add(new Student(currentStudentID, currentStudentFirstName, currentStudentLastName,
                                currentStudentDateOfBirthLD, currentStudentTuitionFees));
                    } while (rs.next());
                } else {
                    studentsAL = new ArrayList<Student>() {
                        {
                            add(new Student(NO_ENTRIES_FOUND_ON_DB, null, null, null, 0));
                        }
                    };
                    return studentsAL;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentsAL;
    }

    public static ArrayList<Course> getCoursesPerStudent(int student_id) {
        ArrayList<Course> coursesAL = new ArrayList<>();
        String select_courses_per_student
                = String.format(DB_SELECT_QUERY,
                ASTERISC,
                "STUDENTS S"
                        + "INNER JOIN STUDENTS_PER_COURSE SPC USING (STUDENT_ID)"
                        + "INNER JOIN COURSES C USING (COURSE_ID)"
                        + "WHERE STUDENT_ID = ?;");
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selCoursePSPS = con.prepareStatement(select_courses_per_student);) {
            selCoursePSPS.setInt(1, student_id);
            try (ResultSet rs = selCoursePSPS.executeQuery();) {
                if (rs.next()) {
                    coursesAL = new ArrayList<>();
                    do {
                        int currentCourseID = rs.getInt(COURSES_ID);
                        String currentCourseTitle = rs.getString(COURSES_TITLE);
                        String currentCourseStream = rs.getString(COURSES_STREAM);
                        String currentCourseType = rs.getString(COURSES_TYPE);
                        Date currentCourseStartDate = rs.getDate(COURSES_START_DATE);
                        LocalDate currentCourseStartDateLD = currentCourseStartDate.toLocalDate();
                        Date currentCourseEndDate = rs.getDate(COURSES_END_DATE);
                        LocalDate currentCourseEndDateLD = currentCourseEndDate.toLocalDate();
                        coursesAL.add(new Course(currentCourseID, currentCourseTitle, currentCourseStream,
                                currentCourseType, currentCourseStartDateLD, currentCourseEndDateLD));
                    } while (rs.next());
                } else {
                    coursesAL = new ArrayList<Course>() {
                        {
                            add(new Course(NO_ENTRIES_FOUND_ON_DB, null, null, null, null, null));
                        }
                    };
                }
                return coursesAL;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coursesAL;
    }

    public static ArrayList<Trainer> getTrainersPerCourse(int course_id) {
        ArrayList<Trainer> trainersAL = new ArrayList<>();
        String select_trainers_per_course
                = String.format(DB_SELECT_QUERY,
                ASTERISC,
                "TRAINERS T \n"
                        + "INNER JOIN TRAINERS_PER_COURSE TPC USING (TRAINER_ID)\n"
                        + "INNER JOIN COURSES C USING (COURSE_ID)\n"
                        + "WHERE COURSE_ID = ?"
        );
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selTrainersPCPS = con.prepareStatement(select_trainers_per_course);) {
            selTrainersPCPS.setInt(1, course_id);
            try (ResultSet rs = selTrainersPCPS.executeQuery();) {
                if (rs.next()) {
                    do {
                        int currentTrainerID = rs.getInt(TRAINERS_ID);
                        String currentTrainerFirstName = rs.getString(TRAINERS_FIRST_NAME);
                        String currentTrainerLastName = rs.getString(TRAINERS_LAST_NAME);
                        String currentTrainerSubject = rs.getString(TRAINERS_SUBJECT);
                        trainersAL.add(new Trainer(currentTrainerID, currentTrainerFirstName, currentTrainerLastName,
                                currentTrainerSubject));
                    } while (rs.next());
                } else {
                    trainersAL = new ArrayList<Trainer>() {
                        {
                            add(new Trainer(NO_ENTRIES_FOUND_ON_DB, null, null, null));
                        }
                    };
                    return trainersAL;

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return trainersAL;
    }

    public static ArrayList<Assignment> getAssignmentsPerCourse(int course_id) {
        ArrayList<Assignment> assignmentsAL = new ArrayList<>();
        String select_assignments_per_course
                = String.format(DB_SELECT_QUERY,
                ASTERISC,
                "ASSIGNMENTS A \n"
                        + "INNER JOIN COURSES C USING (COURSE_ID)\n"
                        + "WHERE COURSE_ID = ? "
        );
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selAssignmentsPCPS = con.prepareStatement(select_assignments_per_course);) {
            selAssignmentsPCPS.setInt(1, course_id);
            try (ResultSet rs = selAssignmentsPCPS.executeQuery();) {
                if (rs.next()) {
                    do {
                        int currentAssignmentID = rs.getInt(ASSIGNMENTS_ID);
                        String currentAssignmentTitle = rs.getString(ASSIGNMENTS_TITLE);
                        String currentAssignmentDescription = rs.getString(ASSIGNMENTS_DESCRIPTION);
                        Timestamp currentAssignmentSubDatetime = rs.getTimestamp(ASSIGNMENTS_SUB_DATE_TIME);
                        LocalDateTime currentStudentDateOfBirthLD = currentAssignmentSubDatetime.toLocalDateTime();
                        float currentAssignmentOralMark = rs.getFloat(ASSIGNMENTS_ORAL_MARK);
                        float currentAssignmentTotalMark = rs.getFloat(ASSIGNMENTS_TOTAL_MARK);
                        assignmentsAL.add(new Assignment(currentAssignmentID, currentAssignmentTitle, currentAssignmentDescription,
                                currentStudentDateOfBirthLD, currentAssignmentOralMark, currentAssignmentTotalMark));
                    } while (rs.next());
                } else {
                    assignmentsAL = new ArrayList<Assignment>() {
                        {
                            add(new Assignment(NO_ENTRIES_FOUND_ON_DB, null, null, null, 0, 0));
                        }
                    };
                    return assignmentsAL;

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return assignmentsAL;
    }

    public static ArrayList<Student> getStudentsWithMultipleCourses() {
        ArrayList<Student> studentsAL = new ArrayList<>();
        ArrayList<Course> coursesAL = new ArrayList<>();
        String selStudentsWithMulipleCourses
                = String.format(DB_SELECT_QUERY, ASTERISC,
                "STUDENTS S\n"
                        + "INNER JOIN STUDENTS_PER_COURSE SPC USING (STUDENT_ID)\n"
                        + "INNER JOIN COURSES C USING (COURSE_ID)\n"
                        + "GROUP BY STUDENT_ID \n"
                        + "HAVING COUNT(*) > 1;"
        );
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selStudentsPS = con.prepareStatement(selStudentsWithMulipleCourses);) {
            try (ResultSet rs = selStudentsPS.executeQuery();) {
                if (rs.next()) {
                    do {
                        int currentStudentID = rs.getInt(STUDENTS_ID);
                        String currentStudentFirstName = rs.getString(STUDENTS_FIRST_NAME);
                        String currentStudentLastName = rs.getString(STUDENTS_LAST_NAME);
                        Date currentStudentDateOfBirth = rs.getDate(STUDENTS_DATE_OF_BIRTH);
                        LocalDate currentStudentDateOfBirthLD = currentStudentDateOfBirth.toLocalDate();
                        float currentStudentTuitionFees = rs.getFloat(STUDENTS_TUITION_FEES);
                        coursesAL = getCoursesPerStudent(currentStudentID);
                        studentsAL.add(new Student(currentStudentID, currentStudentFirstName, currentStudentLastName,
                                currentStudentDateOfBirthLD, currentStudentTuitionFees, coursesAL));
                    } while (rs.next());
                } else {
                    studentsAL = new ArrayList<Student>() {
                        {
                            add(new Student(NO_ENTRIES_FOUND_ON_DB, null, null, null, 0));
                        }
                    };
                    return studentsAL;

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return studentsAL;
    }

    public static ArrayList<StudentAssignment> getAssignmentsPerCoursePerStudent(int student_id) {
        ArrayList<StudentAssignment> studentAsssignmentsAL = new ArrayList<>();

        String selAssignmentsPerCoursePerStudent
                = String.format(DB_SELECT_QUERY, ASTERISC,
                "STUDENTS S \n"
                        + "INNER JOIN ASSIGNMENTS_PER_STUDENT APS USING (STUDENT_ID)\n"
                        + "INNER JOIN ASSIGNMENTS A USING (ASSIGNMENT_ID)\n"
                        + "INNER JOIN COURSES C USING (COURSE_ID)\n"
                        + "WHERE STUDENT_ID = ?;"
        );
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement selAssignmentsPS = con.prepareStatement(selAssignmentsPerCoursePerStudent);) {
            selAssignmentsPS.setInt(1, student_id);
            try (ResultSet rs = selAssignmentsPS.executeQuery();) {
                if (rs.next()) {
                    do {
                        int currentAssignmentID = rs.getInt(ASSIGNMENTS_ID);
                        String currentAssignmentTitle = rs.getString(ASSIGNMENTS_TITLE);
                        String currentAssignmentDescription = rs.getString(ASSIGNMENTS_DESCRIPTION);
                        Timestamp currentStudentSubDatetime = rs.getTimestamp(ASSIGNMENTS_PER_STUDENT_SUB_DATE_TIME);
                        LocalDateTime currentStudentSubDateTimeLDT = null;
                        if (currentStudentSubDatetime != null) {
                            currentStudentSubDateTimeLDT = currentStudentSubDatetime.toLocalDateTime();
                        }
                        Timestamp currentAssignmentSubDatetime = rs.getTimestamp(ASSIGNMENTS_SUB_DATE_TIME);
                        LocalDateTime currentAssignmentSubDateTimeLDT = null;
                        if (currentAssignmentSubDatetime != null) {
                            currentAssignmentSubDateTimeLDT = currentAssignmentSubDatetime.toLocalDateTime();
                        }
                        double currentStudentOralMark = rs.getFloat(ASSIGNMENTS_PER_STUDENT_ORAL_MARK);
                        double currentStudentTotalMark = rs.getFloat(ASSIGNMENTS_PER_STUDENT_TOTAL_MARK);
                        double currentAssignmentOralMark = rs.getFloat(ASSIGNMENTS_ORAL_MARK);
                        double currentAssignmentTotalMark = rs.getFloat(ASSIGNMENTS_TOTAL_MARK);

                        int currentCourseID = rs.getInt(COURSES_ID);
                        String currentCourseTitle = rs.getString(COURSES_TITLE);
                        String currentCourseStream = rs.getString(COURSES_STREAM);
                        String currentCourseType = rs.getString(COURSES_TYPE);
                        Date currentCourseStartDate = rs.getDate(COURSES_START_DATE);
                        LocalDate currentCourseStartDateLD = null;
                        if (currentCourseStartDate != null) {
                            currentCourseStartDateLD = currentCourseStartDate.toLocalDate();
                        }
                        Date currentCourseEndDate = rs.getDate(COURSES_END_DATE);
                        LocalDate currentCourseEndDateLD = null;
                        if (currentCourseEndDateLD != null) {
                            currentCourseEndDateLD = currentCourseEndDate.toLocalDate();
                        }
                        Course course = new Course(currentCourseID, currentCourseTitle, currentCourseStream,
                                currentCourseType, currentCourseStartDateLD, currentCourseEndDateLD);
                        StudentAssignment studentAssignment
                                = new StudentAssignment(currentAssignmentID, currentAssignmentTitle, currentAssignmentDescription, currentStudentSubDateTimeLDT, currentStudentOralMark, currentStudentTotalMark, course);
                        studentAssignment.setAssignmentSubDateTime(currentAssignmentSubDateTimeLDT);
                        studentAssignment.setAssignmentOralMark(currentAssignmentOralMark);
                        studentAssignment.setAssignmentTotalMark(currentAssignmentTotalMark);
                        studentAsssignmentsAL.add(studentAssignment);
                    } while (rs.next());
                } else {
                    studentAsssignmentsAL = new ArrayList<StudentAssignment>() {
                        {
                            add(new StudentAssignment(NO_ENTRIES_FOUND_ON_DB, null, null, null, 0, 0, null));
                        }
                    };
                    return studentAsssignmentsAL;

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return studentAsssignmentsAL;
    }

    public static boolean insertStudent(Student student) {
        String insertStudentQuery = String.format(DB_INSERT_QUERY, STUDENTS_TABLE_NAME, "(NULL,?, ?, ?, ?)");
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        LocalDate dateOfBirth = student.getDateOfBirth();
        float tuitionFees = (float) student.getTuitionFees();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement insertStudentPS = con.prepareStatement(insertStudentQuery, Statement.RETURN_GENERATED_KEYS);) {
            insertStudentPS.setString(1, firstName);
            insertStudentPS.setString(2, lastName);
            insertStudentPS.setDate(3, Date.valueOf(dateOfBirth));
            insertStudentPS.setFloat(4, tuitionFees);
            int rowsAffected = insertStudentPS.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Insert Complete" : "Insert aborted");
            if (rowsAffected > 0) {
                int studentId = -1;
                ResultSet rsIds = insertStudentPS.getGeneratedKeys();
                while (rsIds.next()) {
                    studentId = rsIds.getInt(1);
                }
                ArrayList<Course> coursesAL = student.getCourses();
                if (coursesAL != null && !coursesAL.isEmpty()) {
                    if (insertCoursesPerAttribute(coursesAL, STUDENT, studentId)
                            && insertAssignmentsPerCoursePerStudent(coursesAL, studentId)) {
                        System.out.println("Courses updated.");
                    }

                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean insertTrainer(Trainer trainer) {
        String insertTrainerQuery = String.format(DB_INSERT_QUERY, TRAINERS_TABLE_NAME, "(NULL,?, ?, ?)");
        String firstName = trainer.getFirstName();
        String lastName = trainer.getLastName();
        String subject = trainer.getSubject();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement insertTrainersPS = con.prepareStatement(insertTrainerQuery, Statement.RETURN_GENERATED_KEYS);) {
            insertTrainersPS.setString(1, firstName);
            insertTrainersPS.setString(2, lastName);
            insertTrainersPS.setString(3, subject);
            int rowsAffected = insertTrainersPS.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Insert Complete" : "Insert aborted");
            if (rowsAffected > 0) {
                ResultSet rsIds = insertTrainersPS.getGeneratedKeys();
                int trainerId = -1;
                while (rsIds.next()) {
                    trainerId = rsIds.getInt(1);
                }
                ArrayList<Course> coursesAL = trainer.getCourses();
                if (coursesAL != null && !coursesAL.isEmpty()) {
                    if (insertCoursesPerAttribute(coursesAL, TRAINER, trainerId)) {
                        System.out.println("Trainer Courses updated");
                        return true;
                    }
                }

            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean insertAssignment(Assignment assignment) {
        String insertStudentQuery = String.format(DB_INSERT_QUERY, ASSIGNMENTS_TABLE_NAME, "(NULL,?, ?, ?, ?, ?)");
        String title = assignment.getTitle();
        String description = assignment.getDescription();
        LocalDateTime subDateTime = assignment.getSubDateTime();
        float oralMark = (float) assignment.getOralMark();
        float totalMark = (float) assignment.getTotalMark();
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement insertPreparedStatement = con.prepareStatement(insertStudentQuery);) {
            insertPreparedStatement.setString(1, title);
            insertPreparedStatement.setString(2, description);
            insertPreparedStatement.setTimestamp(3, Timestamp.valueOf(subDateTime));
            insertPreparedStatement.setFloat(4, oralMark);
            insertPreparedStatement.setFloat(5, totalMark);
            Course course = assignment.getCourse();
            if (course != null && course != SchoolUtils.CANCEL_COMMAND_COURSE) {
                int courseId = course.getId();
                insertPreparedStatement.setInt(6, courseId);
            } else {
                insertPreparedStatement.setInt(6, Types.NULL);
            }
            int rowsAffected = insertPreparedStatement.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Insert Complete" : "Insert aborted");
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean insertCourse(Course course) {
        String insertStudentQuery = String.format(DB_INSERT_QUERY, COURSES_TABLE_NAME, "(NULL,?, ?, ?, ?, ?)");
        String title = course.getTitle();
        String stream = course.getStream();
        String type = course.getType();
        LocalDate startDate = course.getStartDate();
        LocalDate endDate = course.getEndDate();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement insertPreparedStatement = con.prepareStatement(insertStudentQuery);) {
            insertPreparedStatement.setString(1, title);
            insertPreparedStatement.setString(2, stream);
            insertPreparedStatement.setString(3, type);
            insertPreparedStatement.setDate(4, Date.valueOf(startDate));
            insertPreparedStatement.setDate(5, Date.valueOf(endDate));
            int rowsAffected = insertPreparedStatement.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Insert Complete" : "Insert aborted");
            if (rowsAffected > 0) {
                return true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean insertCoursesPerAttribute(ArrayList<Course> coursesAL, String attributeType, int attributeId) {
        String insertCoursesQuery;
        if (attributeType.equalsIgnoreCase(TRAINER)) {
            insertCoursesQuery = String.format(DB_INSERT_QUERY, TRAINERS_PER_COURSE_TABLE_NAME, "(?, ?)");
        } else if (attributeType.equalsIgnoreCase(STUDENT)) {
            insertCoursesQuery = String.format(DB_INSERT_QUERY, STUDENTS_PER_COURSE_TABLE_NAME, "(?, ?)");
        } else {
            System.out.println("Wrong attribute name while inserting courses");
            return false;
        }
        if (coursesAL != null && !coursesAL.isEmpty()) {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);) {
                for (Course course : coursesAL) {
                    int currentCourseID = course.getId();
                    try (PreparedStatement selAssignmentsPS = con.prepareStatement(insertCoursesQuery);) {
                        selAssignmentsPS.setInt(1, attributeId);
                        selAssignmentsPS.setInt(2, currentCourseID);
                        int rowsAffected = selAssignmentsPS.executeUpdate();
                        if (rowsAffected <= 0) {
                            return false;
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBUtils.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public static boolean insertAssignmentsPerCoursePerStudent(ArrayList<Course> coursesAL, int studentId) {
        String insertAssignmentQuery = String.format(DB_INSERT_QUERY, ASSIGNMENTS_PER_STUDENT_TABLE_NAME, "(NULL,?, ?, NULL, NULL, NULL)");
        ArrayList<Integer> assignmentIdAL = new ArrayList<>();
        int counter = 0;
        for (Course course : coursesAL) {
            ArrayList<Assignment> currentCourseAssignmentsAL = course.getAssignmnentsAL();
            for (Assignment assignment : currentCourseAssignmentsAL) {
                assignmentIdAL.add(assignment.getId());
            }
        }

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);) {
            int currentAssignmentId = -1;
            for (int i = 0; i < assignmentIdAL.size(); i++) {
                currentAssignmentId = assignmentIdAL.get(i);
                try (PreparedStatement insertPreparedStatement = con.prepareStatement(insertAssignmentQuery);) {
                    insertPreparedStatement.setInt(1, currentAssignmentId);
                    insertPreparedStatement.setInt(2, studentId);
                    int rowsAffected = insertPreparedStatement.executeUpdate();
                    counter += rowsAffected;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return counter == assignmentIdAL.size();
    }

    public static boolean updateAssignmentPerStudent(StudentAssignment studentAssingment, Student student) {
        String updateAssignmnetsStudentQuery
                = String.format(DB_UPDATE_QUERY,
                ASSIGNMENTS_PER_STUDENT_TABLE_NAME,
                APS_STUDENT_SUB_DATE_TIME + " = ? ," + APS_STUDENT_ORAL_MARK + " = ? ," + APS_STUDENT_TOTAL_MARK + " = ? "
                        + "WHERE " + APS_STUDENTS_ID + " = ? AND " + APS_ASSIGNMENT_ID + " = ?;");

        LocalDateTime subDateTime = studentAssingment.getAssignmentSubDateTime();
        float oralMark = (float) studentAssingment.getStudentOralMark();
        float totalMark = (float) studentAssingment.getAssignmentTotalMark();
        int studentId = student.getId();
        int assignmentId = studentAssingment.getId();
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement updatePS = con.prepareStatement(updateAssignmnetsStudentQuery);) {
            updatePS.setTimestamp(1, Timestamp.valueOf(subDateTime));
            updatePS.setFloat(2, oralMark);
            updatePS.setFloat(3, totalMark);
            updatePS.setInt(4, studentId);
            updatePS.setInt(5, assignmentId);
            int rowsAffected = updatePS.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
