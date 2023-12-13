package tr.com.provera.pameraapi.enumerate;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum WorkitemState {
    Cancelled(-1),Blocked(0),InBackLog(1),WIP(2),ReadyToTest(3),InTest(4),Deployed(5),Released(6);

    private final int id;

    WorkitemState(int id) {
        this.id = id;
    }

    public static WorkitemState valueOf(Integer id) {
        if (id == null) {
            return null;
        }

        for (WorkitemState workitemState : WorkitemState.values()) {
            if (id.equals(workitemState.getId())) {
                return workitemState;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }
}

