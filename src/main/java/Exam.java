import java.time.Instant;

public class Exam {
    private final int id;
    private final String title;
    private final Instant date;
    private final double coefficient;
    private final Course course;

    public Exam(int id, String title, Instant date, double coefficient, Course course) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.coefficient = coefficient;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Instant getDate() {
        return date;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public Course getCourse() {
        return course;
    }
}
