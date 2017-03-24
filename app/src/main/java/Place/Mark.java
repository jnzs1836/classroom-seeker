package Place;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Dick on 2017/3/7.
 */

public interface Mark {
    public abstract void SetJudgement(String index, int judgement) throws IllegalArgumentException;
    public abstract Map GetJudgement();
    public abstract void SetTags(String tag);
    public ArrayList<String> GetTags();
}
