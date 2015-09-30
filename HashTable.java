/**
 * @author Kartikeya Sharma
 * @course Advanced Topics, Pingry
 * @version 9.30.15 / "Version 0"
 */

import java.util.List;
import java.util.ArrayList;

public class HashTable{
  
     /** Number of items stored in the hash table **/
     private int items;
     /** Ratio of items to total hash table capacity before rehashing is needed **/
     private static final double loadfactor = .5;
     /** The hash table **/
     private Object[] table;
    
     public HashTable(){
         this(100);
     }
    
     public HashTable(int capacity){
         items = 0;
         table = new Object[capacity];
     }
    
    /**
     * If the ratio of the items stored in the hash table and the capacity of the hash table exceed loadfactor ratio, table is rehashed.
     * If the "hashed" index is available, the Object is put in the hash table at that index. Otherwise, the function uses quadratic
     * probing to find the next available index in the hash table.
     *
     * @param Object obj: any object can be put into the hash table.
     */
    public void put(Object obj){
         if ((double)items/table.length>=loadfactor){
             rehash();
         }
         int index = (adder(obj.hashCode()))%table.length;
         if (table[index]==null){
             table[index] = obj;
         }
         else{
            boolean stop = false;
            int indexqp;
            for (int iadd = 1; stop==true; iadd*=2){
                indexqp = index+iadd;
                if (table[indexqp]==null)
                    table[indexqp]=obj;
                    stop=true;
            }
         }
         items+=1;
    }
    
     /**
      * Helper function of put(Object obj). Sums up the digits of the Object's hashcode.
      *
      * @param int num: the number whose digits are being summed up
      */
    
     public int adder (int num){
        int sum = 0;
        int digit = 0;
        while (num>0){
            digit = num%10;
            sum+=digit;
            num/=10;
        }
         return sum;
     }
    
     /**
      * Rehashes the entire table to a hash table with a doubled capacity.
      */
    
     public void rehash(){
         Object[] oldtable = table;
         table = new Object[table.length*2];
         for (int i = 0; i<oldtable.length; i++){
             put(oldtable[i]);
         }
     }
    
    
    /** 
     * Returns string representation of each slot in the hash table with indexes corresponding to Object memory addresses, or, in the case of
     * an object like String, its toString() method.
     * If no Object exists in a slot in the hash table, null is returned alongside the index.
     */
    
    public String toString(){
        String output = "";
        for (int i = 0; i<table.length; i++){
            output+="@" + i + ": " + table[i] + "; ";
        }
        return "String representation of hash table: " + output;
    }
    
    
    
    
    
    /** Ignore for Version 0 Submission **/
    
    /**
     Notes from Class on 9.29.15
     
     public class Association<K,V>
     {
        public K Key;
        public V value;
     
        // Wrap/Connect these two data types together.
        public Association (K k, V v){
     
        }
     }
     
     Verizon
     Name
     Address
     Balance
     Account #
     Social Security
     Telephone #
     
     HashTable<SS,Record> table...
     "sequential probing"
     Version 1
     ... update with generics
     ... 
     
     public void put(K key - where you want to put it, V value - what you put in the table)
     Can't make arrays with type generic
     @ Suppressed Warnings
     Quadratic probing
     */
    
 }