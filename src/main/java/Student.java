import javax.swing.*;
import java.time.Instant;

public class Student extends Person {
    private final Group group;
    private final Tutor  tutor;

    public Student(int id, String first_name, String last_name, Instant birthDate, String email, String phone, Group group, Tutor tutor) {
        super(id, first_name, last_name, birthDate, email, phone);
        this.group = group;
        this.tutor = tutor;
    }

    public Group getGroup() {
        return group;
    }

    public Tutor getTutor() {
        return tutor;
    }
}
