import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public  class Grade {
    private final double initialValue;
    private final Instant initialAt;
    private final List<GradeChange> history = new ArrayList<>();

    public Grade(double initialValue, Instant initialAt) {
        this.initialValue = initialValue;
        this.initialAt = initialAt;
    }

    public void addChange(Instant when, double newValue, String reason) {
        Objects.requireNonNull(when);
        history.add(new GradeChange(when, newValue, reason));
        history.sort(Comparator.comparing(GradeChange::getWhen));
    }

    public synchronized double valueAt(Instant t) {
        if (t == null) throw new IllegalArgumentException("t ne peut pas être null");

        double current = Double.NaN;
        if (!initialAt.isAfter(t)) {
            current = initialValue;
        }

        GradeChange latest = null;
        for (GradeChange c : history) {
            if (!c.getWhen().isAfter(t)) latest = c;
            else break;
        }
        if (latest != null) current = latest.getValue();
        return current;
    }

    public List<GradeChange> getHistory() { return Collections.unmodifiableList(history); }
    public double getInitialValue() { return initialValue; }
    public Instant getInitialAt() { return initialAt; }
}

