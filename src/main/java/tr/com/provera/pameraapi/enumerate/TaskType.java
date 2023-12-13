package tr.com.provera.pameraapi.enumerate;

public enum TaskType {
    Task(1);
    private final int id;


    TaskType(int id) {
        this.id = id;
    }

    public static TaskType valueOf(Integer id){
        if(id == null)
            return null;
        for(TaskType taskType : TaskType.values()){
            if (id.equals(taskType.getId()))
                return taskType;
        }

        return null;
    }

    public int getId() {
        return id;
    }

}
