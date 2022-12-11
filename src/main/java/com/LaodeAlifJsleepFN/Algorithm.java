package com.LaodeAlifJsleepFN;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

/**
 * Algorithm class to filter, find, collect, paginate
 * from a list
 */
public class Algorithm {
    /**
     * Default constructor that's empty because all methods
     * are static
     */
    private Algorithm(){

    }

    /**
     * Collect method to collect a list filtered iterable
     * @param iterable is the source of iterable list to be filtered
     * @param value is the value that'll be used to filter iterable list
     * @return a filtered list
     * @param <T> Generic
     */
    public static <T> List<T> collect(Iterable <T> iterable, T value){
        Predicate<T> predicate = value::equals;
        return collect(iterable.iterator(), predicate);
    }

    /**
     * Method to collect a filtered list of iterable
     * @param iterable is the soure of iterable list to be filtered
     * @param predicate is a boolean expression from the lambda expression
     * @return a filtered list
     * @param <T> generic
     */
    public static <T> List<T> collect (Iterable <T> iterable, Predicate <T> predicate){
        return collect(iterable.iterator(), predicate);
    }

    /**
     * Method to collect filtered list of array
     * @param array is the soure of array to be filtered
     * @param value is the value to match with the data in array to filter
     * @return a filtered list
     * @param <T> generic
     */
    public static <T> List<T> collect(T[] array, T value){
        Predicate<T> predicate = value::equals;
        return collect(Arrays.stream(array).iterator(), predicate);
    }

    /**
     * Method to collect filtered a list of iterator
     * @param iterator is the source of iterator list to be filtered
     * @param value is the value to match with the data in list to filter
     * @return a filtered list
     * @param <T> generic
     */

    public static <T> List<T> collect(Iterator <T> iterator, T value){
        Predicate<T> predicate = value::equals;
        return collect(iterator, predicate);
    }

    /**
     * Method to collect filtered list of array
     * @param array is the source of array to be filtered
     * @param predicate is a boolean expression from the lambda expression
     * @return a filtered list
     * @param <T> generic
     */
    public static <T> List<T> collect(T[] array, Predicate<T> predicate){
        return collect(Arrays.stream(array).iterator(), predicate);
    }

    /**
     * Method to collect list of filtered iterator
     * @param iterator is the source of iterator to be filtered
     * @param predicate is the boolean expression from the lambda expression
     * @return a filtered list
     * @param <T> generic
     */

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

    /**
     * Method to count how many value in the list of iterator
     * @param iterator is the source of iterator list
     * @param value is the value to be counted
     * @return the number of value in iterator
     * @param <T>  generic
     */
    public static <T> int count(Iterator <T> iterator, T value){
        final Predicate<T> predicate = value::equals;
        return count(iterator, predicate);
    }

    /**
     *Method to count how many value in the array
     * @param array is source of array
     * @param value is the value to be counted
     * @return the number of value in array
     * @param <T> generic
     */
    public static <T> int count(T[] array , T value){
        final Iterator <T> it = Arrays.stream(array).iterator();
        return count(it, value);
    }

    /**
     * Method to count how value in the list of iterable
     * @param iterable is the source of iterable list
     * @param predicate is the boolean expression expressed in lambda expression
     * @return how many values in iterable list
     * @param <T> generic
     */
    public static <T> int count(Iterable <T> iterable, Predicate <T> predicate ){
        final Iterator<T> it = iterable.iterator();
        return count(it, predicate);
    }

    /**
     * method to count how many value in array
     * @param array is the source of array
     * @param predicate is the boolean expression expressed in lambda expression
     * @return how many values in array
     * @param <T> generic
     */

    public static <T> int count(T[] array, Predicate <T> predicate){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, predicate);
    }

    /**
     * Method to count value in a list of iterator
     * @param iterator is the source of iterator
     * @param predicate is the boolean expression expressed in lambda expression
     * @return the numbers of value in iterator
     * @param <T> generic
     */

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

    /**
     * Method to count values in iterable list
     * @param iterable is the source of iterable list
     * @param value is the value that'll be counted
     * @return the numbers of value in iterable list
     * @param <T> generic
     */

    public static <T> int count(Iterable <T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return count(it, value);
    }

    /**
     * Method to check whether the value is exist in array
     * @param array the source of array
     * @param value is the value that'll be checked
     * @return true if value exist in array, else false
     * @param <T> generic
     */
    public static <T> boolean exists(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }

    /**
     * Method to check whether the value is exist in iterable list
     * @param iterable the source of iterable list
     * @param value is the value that'll be checked
     * @return true if value is exist in iterable list, else false
     * @param <T> generic
     */
    public static <T> boolean exists(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    /**
     * Method to check whether the value is exists in the list of iterator
     * @param iterator is the source of iterator list
     * @param value is the value that'll be checked
     * @return true if value exists in iterator list, else false
     * @param <T> generic
     */
    public static <T> boolean exists(Iterator<T> iterator, T value) {
        final Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    /**
     * Method to check whether the value exist in array
     * @param array is the source of array
     * @param pred is is the boolean expression expressed in lambda expression
     * @return true if values found in array
     * @param <T> generic
     */
    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    /**
     * Method to check whether the value exist in iterable list
     * @param iterable is the source of iterable list
     * @param pred is the boolean expression expressed in lambda expression
     * @return true if the value exist in iterable list, else false
     * @param <T> generic
     */
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    /**
     * Method to check whether the value exists in iterator list
     * @param iterator is the source of iterator list
     * @param pred is the boolean expression expressed in lambda expression
     * @return true if the value is exists in iterator list, else false
     * @param <T> generic
     */
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()){
            T current = iterator.next();
            if(pred.predicate(current))
                return true;
        }
        return false;
    }

    /**
     * Method to find value inside array
     * @param array is the source of array
     * @param predicate is the boolean expression expressed in lambda expression
     * @return the founded value
     * @param <T> generic
     */
    public static <T> T find(T[] array, Predicate<T> predicate){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, predicate);
    }

    /**
     * Method to find value in iterable list
     * @param iterable is the iterable list source
     * @param predicate is the boolean expression expressed in lambda expression
     * @return the founded value
     * @param <T> generic
     */
    public static <T> T find(Iterable<T> iterable, Predicate<T> predicate){
        final Iterator<T> it = iterable.iterator();
        return find(it, predicate);
    }

    /**
     * Method to find value in array
     * @param array is the source of array
     * @param value is the value to be found in array
     * @return the value of the founded value
     * @param <T> generic
     */
    public static <T> T find(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    /**
     * Method to find value in iterable list
     * @param iterable is the source of iterable list
     * @param value is the value to be found in iterable list
     * @return the founded value
     * @param <T> generic
     */
    public static <T> T find(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    /**
     * Method to find value in iterator list
     * @param iterator is the source of iterator list
     * @param value is the value to be found in iterator list
     * @return the founded value
     * @param <T> generic
     */
    public static <T> T find(Iterator<T> iterator, T value){
        final Predicate<T> predicate = value::equals;
        return find(iterator, predicate);
    }

    /**
     * Method to find value in iterator list
     * @param iterator is the source of iterator list
     * @param predicate is the boolean expression expressed in lambda expression
     * @return the founded value
     * @param <T> generic
     */
    public static <T> T find(Iterator<T> iterator, Predicate<T> predicate){
        while(iterator.hasNext()) {
            T current = iterator.next();
            if (predicate.predicate(current)) {
                return current;
            }
        }
        return null;
    }

    /**
     * Method to filter and paginate list
     * @param array is the source of array to paginate
     * @param page is the page number
     * @param pageSize is the size of page
     * @param predicate is the boolean expression expressed in lambda expression
     * @return filtered and paginated list
     * @param <T> generic
     */
    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> predicate){
        return paginate(Arrays.stream(array).iterator(), page, pageSize, predicate);
    }

    /**
     * Method to filter and paginate list
     * @param iterable the source of iterable list to paginate
     * @param page the page number
     * @param pageSize the size of the page
     * @param predicate is the boolean expression expressed in lambda expression
     * @return filtered and paginated list
     * @param <T> generic
     */
    public static <T> List <T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> predicate){
        return paginate(iterable.iterator(), page, pageSize, predicate);
    }

    /**
     * Method to filter and paginate list
     * @param iterator the source of iterator list to paginate
     * @param page the page number
     * @param pageSize the size of the page
     * @param pred is the boolean expression expressed in lambda expression
     * @return filtered and paginated list
     * @param <T> generic
     */
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred){
        List<List<T>> newList = new ArrayList<List<T>>();
        List<T> filteredList = new ArrayList<T>();
        T obj;
        while(iterator.hasNext()){
            obj = iterator.next();
            if(pred.predicate(obj)){
                filteredList.add(obj);
            }
        }
        for(int i = 0; i < filteredList.size(); i += pageSize){
            newList.add(new ArrayList<T>(filteredList.subList(i, Math.min(filteredList.size(), i + pageSize))));
        }
        return newList.get(page);
    }

}



