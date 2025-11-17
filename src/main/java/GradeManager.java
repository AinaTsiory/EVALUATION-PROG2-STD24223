import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GradeManager {
    // key: studentId + "#" + examId
    private final Map<String, Grade> store = new HashMap<>();

    private String key(Student s, Exam e) { return s.getId() + "#" + e.getId(); }

    public void putInitialGrade(Student student, Exam exam, double value, Instant at) {
        store.put(key(student, exam), new Grade(value, at));
    }

    public void addGradeChange(Student student, Exam exam, Instant when, double newValue, String reason) {
        Grade g = store.get(key(student, exam));
        if (g == null) throw new IllegalStateException("Aucune note initiale pour cet étudiant/examen");
        g.addChange(when, newValue, reason);
    }

    public double getExamGrade(Exam exam, Student student, Instant t) {
        Grade g = store.get(key(student, exam));
        if (g == null) return Double.NaN;
        return g.valueAt(t);
    }

    public double getCourseGrade(Course course, Student student, Instant t, Collection<Exam> allExams) {
        // filter exams belonging to the course
        List<Exam> exams = allExams.stream().filter(e -> e.getCourse().getId() == course.getId()).collect(Collectors.toList());
        double sumCoef = 0.0;
        double weighted = 0.0;
        for (Exam e : exams) {
            double val = getExamGrade(e, student, t);
            if (Double.isNaN(val)) continue; // ignore missing
            weighted += val * e.getCoefficient();
            sumCoef += e.getCoefficient();
        }
        if (sumCoef == 0.0) return Double.NaN;
        return weighted / sumCoef;
    }
}
