package tr.com.provera.pameraapi.enumerate;

public enum WorkitemType {
    Request(1),UserStory ( 2),Bug ( 3),Ticket(4);
    private final int id;

    WorkitemType(int id) {
        this.id = id;
    }

    public static WorkitemType valueOf(Integer id) {
        if (id == null) {
            return null;
        }

        for (WorkitemType workitemType : WorkitemType.values()) {
            if (id.equals(workitemType.getId())) {
                return workitemType;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

}
