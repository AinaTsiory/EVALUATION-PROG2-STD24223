import java.time.Instant;

public class Tutor extends Person{
    private final String description;

    public Tutor(int id, String first_name, String last_name, Instant birthDate, String email, String phone, String description) {
        super(id, first_name, last_name, birthDate, email, phone);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
