package Place;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Dick on 2017/3/16.
 */

public  final class LocationConstant {
    private LocationConstant() {};

    public static final int[] LocationConverter = {10000,1000};
    public static final String[] Loc0 = {"西一教学楼","西二教学楼","东一教学楼","东二教学楼","基础图书馆","医图","其他"};
    public static final ArrayList<String> Loc1 = new ArrayList<String>(Arrays.asList(Loc0));
}
