import gestion.models.GestionModels.*;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    public void testGetExamGrade_and_changes() {
        // Arrange
        Tutor tutor = new Tutor(1, "Rabe", "Solo", Instant.parse("1980-01-01T00:00:00Z"), "tutor@example.com", "0340000000", "mère");
        Student student = new Student(10, "Rakoto", "Faly", Instant.parse("2003-05-05T00:00:00Z"), "s@example.com", "0341111111", "G1", tutor);
        Teacher teacher = new Teacher(2, "Rana", "Lala", Instant.parse("1975-03-03T00:00:00Z"), "t@example.com", "0322222222", "back-end");
        Course prog2 = new Course(100, "PROG2", 6, teacher);

        Exam e1 = new Exam(1000, "PROG2 - Contrôle 1", Instant.parse("2025-06-01T09:00:00Z"), 2.0, prog2);
        Exam e2 = new Exam(1001, "PROG2 - Contrôle 2", Instant.parse("2025-07-01T09:00:00Z"), 3.0, prog2);

        GradeManager gm = new GradeManager();

        Instant t0 = Instant.parse("2025-06-01T10:00:00Z");
        gm.putInitialGrade(student, e1, 10.0, t0); // initial at t0 -> 10
        gm.putInitialGrade(student, e2, 15.0, t0); // initial at t0 -> 15

        Instant t1 = Instant.parse("2025-06-10T12:00:00Z");
        gm.addGradeChange(student, e1, t1, 12.0, "réévaluation");

        Instant checkBetween = Instant.parse("2025-06-05T00:00:00Z");
        double gradeE1_between = gm.getExamGrade(e1, student, checkBetween);
        assertEquals(10.0, gradeE1_between, 1e-9);

        Instant checkAfter = Instant.parse("2025-06-20T00:00:00Z");
        double gradeE1_after = gm.getExamGrade(e1, student, checkAfter);
        assertEquals(12.0, gradeE1_after, 1e-9);

        double gradeE2 = gm.getExamGrade(e2, student, checkAfter);
        assertEquals(15.0, gradeE2, 1e-9);

        double courseBetween = gm.getCourseGrade(prog2, student, checkBetween, List.of(e1, e2));
        assertEquals(13.0, courseBetween, 1e-9);

        double courseAfter = gm.getCourseGrade(prog2, student, checkAfter, List.of(e1, e2));
        assertEquals(13.8, courseAfter, 1e-9);
    }

    @Test
    public void testMissingGrades_returnNaN() {
        Tutor tutor = new Tutor(1, "R", "T", Instant.parse("1980-01-01T00:00:00Z"), "tutor@example.com", "0340000000", "père");
        Student student = new Student(11, "Solo", "Ana", Instant.parse("2004-02-02T00:00:00Z"), "s2@example.com", "0342222222", "G2", tutor);
        Teacher teacher = new Teacher(3, "Teacher", "X", Instant.parse("1970-01-01T00:00:00Z"), "teach@example.com", "0323333333", "front-end");
        Course prog1 = new Course(200, "PROG1", 5, teacher);
        Exam e = new Exam(2000, "PROG1 - Exam", Instant.parse("2025-05-01T09:00:00Z"), 1.0, prog1);

        GradeManager gm = new GradeManager();

        double examVal = gm.getExamGrade(e, student, Instant.parse("2025-05-02T00:00:00Z"));
        assertTrue(Double.isNaN(examVal));

        double courseVal = gm.getCourseGrade(prog1, student, Instant.parse("2025-05-02T00:00:00Z"), List.of(e));
        assertTrue(Double.isNaN(courseVal));
    }
}

