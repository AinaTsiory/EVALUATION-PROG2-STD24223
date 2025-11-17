import java.time.Instant;

public class Teacher extends Person {
    private final String speciality;

    public Teacher(int id, String first_name, String last_name, Instant birthDate, String email, String phone, String speciality) {
        super(id, first_name, last_name, birthDate, email, phone);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }
}
