package Place;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dick on 2017/3/7.
 */

public class Place implements GeoLocation, Mark,Serializable {
    private static final long serialVersionUID = -6919461967497580385L;
    private String name;
    private int[] location = new int[3];
    private Map JudgementMap = new HashMap();
    private ArrayList<String> Tags = new ArrayList();
    public String Decription ;
    /*数据库要求Tags不超过5个*/
    public Place(){
        name = "default";
        JudgementMap.put("Test_judgement",0);
    }

    public Place(String set_name){
        name = set_name;
        JudgementMap.put("Test_judgement1",0);

    }
    private  void setDescription(String Description){
        this.Decription = Description;
    }
    public String GetDescription(){
        return Decription;
    }

    public void setLocation(int[] Location) {
        this.location[0] = Location[0];
        this.location[1] = Location[1];
        this.location[2] = Location[2];
    }
    public int[] GetLocation(){
        return this.location;
    }

    public void setName(String name){
        this.name =name;}
    public  String GetName(){
        return name;
    }

    public ArrayList<String> GetLocationString(){
         ArrayList<String>  stringback = new ArrayList<String>() ;
        stringback.add(LocationConstant.Loc1.get(location[0]));
        stringback.add(Integer.toString(location[1]));
        stringback.add(Integer.toString(location[2]));

        return stringback;

    }
    public void SetJudgement(String index,int judgement) throws IllegalArgumentException{
        if(judgement<11&&judgement>0&&JudgementMap.containsKey(index)){
            JudgementMap.put(index,judgement);}
            else if(JudgementMap.containsKey(index) == false){
                throw new IllegalArgumentException("请输入正确的项目名称");
            }
            else if(judgement>10||judgement<1){
                throw new IllegalArgumentException(("请输入1到10之间的评分值"));
            }
        }


    public Map GetJudgement(){
        return JudgementMap;
    }

    public void SetTags(String tag){
        Tags.add(tag);
    }
    public ArrayList<String> GetTags(){
        return Tags;
    }


    public void setAll(String name, ArrayList<String> Tags,int[] Location){
        setName(name);
        setDescription("NULL");
        this.Tags = Tags;
        setLocation(Location);
        }

    }


