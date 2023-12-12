package tr.com.provera.pameraapi.enumerate;

public enum TaskCategory {
    Analysis(1), Development(2),Test(3),Operation(4),Document(5);

    private final int id;

    TaskCategory(int id) {
        this.id = id;
    }
    public static TaskCategory valueOf(Integer id){
        if(id == null)
            return null;
        for(TaskCategory taskCategory : TaskCategory.values()){
            if(id.equals(taskCategory.getId()))
                return taskCategory;
        }
        return null;
    }

    public int getId(){
        return id;
    }

}
