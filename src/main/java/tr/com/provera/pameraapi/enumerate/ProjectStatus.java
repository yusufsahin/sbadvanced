package tr.com.provera.pameraapi.enumerate;

/**
 * Enum representing the status of a project.
 */
public enum ProjectStatus {
    INITIATION(1), PLANNING(2), EXECUTION(3), MONITOR(4), CLOSED(5);

    private final int id;

    /**
     * Constructor for ProjectStatus enum.
     * @param id the unique identifier for the status.
     */
    ProjectStatus(int id) {
        this.id = id;
    }

    /**
     * Returns the ProjectStatus corresponding to the given ID.
     *
     * @param id The ID of the ProjectStatus.
     * @return The corresponding ProjectStatus, or null if no matching status is found.
     */
    public static ProjectStatus valueOf(int id) {
        for (ProjectStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        return null;
    }

    /**
     * Gets the ID of the ProjectStatus.
     *
     * @return The ID of the status.
     */
    public int getId() {
        return id;
    }
}
