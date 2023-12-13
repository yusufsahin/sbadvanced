package tr.com.provera.pameraapi.enumerate;

public enum TaskStatus {
    Cancelled(-1),Blocked(0),ToDo(1),InProgress(2),ToVerify(3),Done(4);

    private final int id;

    TaskStatus(int id) {
        this.id = id;
    }

    public static TaskStatus valueOf(Integer id){
        if(id == null)
            return null;

        for(TaskStatus taskStatus : TaskStatus.values()){
            if(id.equals(taskStatus.getId()))
                return taskStatus;
        }

        return null;
    }


    public int getId(){
        return id;
    }

}
