package tr.com.provera.pameraapi.enumerate;

public enum WorkitemCategory {
    Analysis(1),Development(2),Test(3),Operation(4),Document(5);

    private final int id;

    WorkitemCategory(int id) {
        this.id = id;
    }

    public static WorkitemCategory valueOf(Integer id) {
        if (id == null) {
            return null;
        }

        for (WorkitemCategory workitemCategory : WorkitemCategory.values()) {
            if (id.equals(workitemCategory.getId())) {
                return workitemCategory;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

}
