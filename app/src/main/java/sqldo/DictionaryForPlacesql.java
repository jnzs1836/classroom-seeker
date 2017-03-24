package sqldo;

/**
 * Created by Dick on 2017/3/24.
 */

public class DictionaryForPlacesql {
    private long id;
    private String name;
    public DictionaryForPlacesql(long id,String name){
        this.id = id;
        this.name = name;
    }
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}
