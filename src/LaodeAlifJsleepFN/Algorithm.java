package LaodeAlifJsleepFN;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

public class Algorithm {
    private Algorithm(){

    }

    public static <T> List<T> collect(Iterable <T> iterable, T value){
        Predicate<T> predicate = value::equals;
        return collect(iterable.iterator(), predicate);
    }

    public static <T> List<T> collect (Iterable <T> iterable, Predicate <T> predicate){
        return collect(iterable.iterator(), predicate);
    }

    public static <T> List<T> collect(T[] array, T value){
        Predicate<T> predicate = value::equals;
        return collect(Arrays.stream(array).iterator(), predicate);
    }

    public static <T> List<T> collect(Iterator <T> iterator, T value){
        Predicate<T> predicate = value::equals;
        return collect(iterator, predicate);
    }
    public static <T> List<T> collect(T[] array, Predicate<T> predicate){
        return collect(Arrays.stream(array).iterator(), predicate);
    }

    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> predicate){
        List<T> list = new ArrayList<T>();
        while(iterator.hasNext()){
            T obj = iterator.next();
            if(predicate.predicate(obj)){
                list.add(obj);
            }
        }
        return list;
    }
    public static <T> int count(Iterator <T> iterator, T value){
        final Predicate<T> predicate = value::equals;
        return count(iterator, predicate);
    }

    public static <T> int count(T[] array , T value){
        final Iterator <T> it = Arrays.stream(array).iterator();
        return count(it, value);
    }

    public static <T> int count(Iterable <T> iterable, Predicate <T> predicate ){
        final Iterator<T> it = iterable.iterator();
        return count(it, predicate);
    }

    public static <T> int count(T[] array, Predicate <T> predicate){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, predicate);
    }

    public static <T> int count(Iterator <T> iterator, Predicate <T> predicate){
        int count = 0;
        while(iterator.hasNext()){
            T current = iterator.next();
            if(predicate.predicate(current)){
                count++;
            }
        }
        return count;
    }

    public static <T> int count(Iterable <T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return count(it, value);
    }

    public static <T> boolean exists(Iterable <T> iterable, T value){
        Iterator<T> it = iterable.iterator();
        return exists (it, value);
    }

    public static <T> boolean exists(Iterable <T> iterable, Predicate <T> predicate){
        Iterator <T> it = iterable.iterator();
        return exists(it, predicate);
    }

    public static <T> boolean exists(T[] array, Predicate <T> predicate){
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, predicate);
    }

    public static <T> boolean exists(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists (it, value);
    }

    public static <T> boolean exists(Iterator<T> iterator, T value){
        final Predicate<T> predicate = value::equals;
        return exists (iterator, predicate);
    }

    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> predicate){
        while (iterator.hasNext()){
            T current = iterator.next();
            if(predicate.predicate(current)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T find(T[] array, Predicate<T> predicate){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, predicate);
    }

    public static <T> T find(Iterable<T> iterable, Predicate<T> predicate){
        final Iterator<T> it = iterable.iterator();
        return find(it, predicate);
    }

    public static <T> T find(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    public static <T> T find(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    public static <T> T find(Iterator<T> iterator, T value){
        final Predicate<T> predicate = value::equals;
        return find(iterator, predicate);
    }

    public static <T> T find(Iterator<T> iterator, Predicate<T> predicate){
        while(iterator.hasNext()) {
            T current = iterator.next();
            if (predicate.predicate(current)) {
                return current;
            }
        }
        return null;
    }

    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> predicate){
        return paginate(Arrays.stream(array).iterator(), page, pageSize, predicate);
    }

    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> predicate){
        List<List<T>> newList = new ArrayList<List<T>>();
        List<T> filteredList = new ArrayList<T>();
        while(iterator.hasNext()){
            T obj = iterator.next();
            if(predicate.predicate(obj)){
                filteredList.add(obj);
            }
        }
        for(int i = 0; i < filteredList.size(); i += pageSize){
            newList.add(new ArrayList<T>(filteredList.subList(i, Math.min(filteredList.size(), i + pageSize))));
        }
        return newList.get(page);

    }

}



