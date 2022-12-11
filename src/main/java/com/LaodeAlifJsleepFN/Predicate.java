package com.LaodeAlifJsleepFN;

/**
 * An interface to do lambda expression of predicate
 * @param <T> generic
 */
public interface Predicate<T> {
    /**
     * To check whether the parameter is true if applied to predicate
     * @param pred_obj is the variable that'll get checked
     * @return true if the variable is true according to predicate
     * give in the lambda expression
     */
    boolean predicate(T pred_obj);
}