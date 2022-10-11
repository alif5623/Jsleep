package laodeAlifJsleepFN;
import java.util.HashMap;


public class Serializable
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
    /**
     * Constructor for objects of class Serializable
     */
    protected Serializable()
    {
        Integer temp = mapCounter.get(getClass());
        if(temp == null){
            temp = 0;
        }else{
            temp += 1;
        }
        id = temp;
        System.out.println("ID: " + id);
        mapCounter.put(getClass(), temp);
    }

    public int compareTo(Serializable serial){
        return this.id - serial.id;
    }

    public boolean equals (Object object){
        return object instanceof Serializable && mapCounter.get(getClass()) == ((Serializable) object).id;
    }

    public boolean equals(Serializable serial){
        if(serial.id == this.id){
            return true;
        }else{
            return false;
        }
    }

    public <T> Integer getClosingId(Class<T> tClass){
        return mapCounter.get(tClass);
    }

    public <T> Integer setClosingId(Class<T> tClass, int id){
        return mapCounter.put(tClass, id);
    }
}
