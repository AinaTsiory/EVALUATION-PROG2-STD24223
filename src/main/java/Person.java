import java.time.Instant;

public class Person {
    private final int id;
    private final String first_name;
    private final String last_name;
    private final Instant birthDay;
    private final String email;
    private final String phone;

    public Person(int id, String first_name, String last_name, Instant birthDate, String email, String phone) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthDay = birthDate;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Instant getBirthDate() {
        return birthDay;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
