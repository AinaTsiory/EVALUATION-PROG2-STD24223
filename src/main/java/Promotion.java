import java.util.List;

public class Promotion {
    private final List<Group> groups;

    public Promotion(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
