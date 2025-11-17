import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Grade {
    private final double initialValue;
    private final Instant initialAt;
    private final List<GradeChange> history = new ArrayList<>();

    public Grade(double initialValue, Instant initialAt) {
        this.initialValue = initialValue;
        this.initialAt = initialAt;
    }

}
