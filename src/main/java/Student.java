import java.time.Instant;

public class Student extends Person {
    private final String group;
    private final Tutor  tutor;

    public Student(int id, String first_name, String last_name, Instant birthDate, String email, String phone, String group, Tutor tutor) {
        super(id, first_name, last_name, birthDate, email, phone);
        this.group = group;
        this.tutor = tutor;
    }

    public String getGroup() {
        return group;
    }

    public Tutor getTutor() {
        return tutor;
    }
}
