package event;

import Place.Place;

import static Place.LocationConstant.Loc1;


public class QueryEvent {
    private Place place;
    public QueryEvent(Place place){
        this.place = place;
    }

public String getName(){
    return place.GetName();
}
public String getLoc(){
    int[] Location = place.GetLocation();
    String Loco1 = Loc1.get(Location[0]);
    String Loco2 = Integer.toString(Location[1]) + "floor";
    String Loco3 = Integer.toString(Location[2]);
    return (Loco1+Loco2+Loco3);

}
public String FunctionForTest(){
   int[] teste =       place.GetLocation();
    String s = String.valueOf(teste[2]);
    return s;
}
public Place getPlace(){
    return this.place;
}

public String getTag(){
    return place.GetTags().get(0);
}
}
