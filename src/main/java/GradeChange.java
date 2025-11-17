import java.time.Instant;

public class GradeChange {
    private final Instant when;
    private final double value;
    private final String reason;

    public GradeChange(Instant when, double value, String reason) {
        this.when = when;
        this.value = value;
        this.reason = reason;
    }

    public Instant getWhen() {
        return when;
    }

    public double getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }
}
