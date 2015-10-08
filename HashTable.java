import java.util.List;
import java.util.ArrayList;

/**
 * @author Kartikeya Sharma
 * @course Advanced Topics, Pingry
 * @version 9.30.15 / "Version 0"
 * @description A hash table adds and removes objects in constant time. Each object is stored in the form of an entry, which is comprised of an inputted key and value.
 */

public class HashTable<K,V>{
  
     /** Number of items stored in the hash table **/
     private int count;
     /** Ratio of items to total hash table capacity before rehashing is needed **/
     private double loadfactor = .5;
     /** The hash table **/
     private Entry[] table;
    
     /** Default construction of hash table of size 100. **/
     public HashTable(){
         this(100);
     }
    
     /** Construction of hash table with capacity 100. 
      *
      * @param int capacity
      */
    
     public HashTable(int capacity){
         count = 0;
         table = new Entry[capacity];
     }
    
    /**
     * Creates an Entry object with the passed in key-value pair and passes entry into putEntry to be stored into the hash table.
     * 
     * @param K key
     * @param V value
     */
    
    public void put(K key, V value){
         Entry<K,V> entry = new Entry(key, value);
         putEntry(entry);
    }
     
    /**
     * Puts Entry e into the hash table.
     * If the ratio of the items stored in the hash table and the capacity of the hash table exceed loadfactor ratio, the table is rehashed.
     * If the "hashed" index is available, the Entry is put in the hash table at that index. Otherwise, the function sequentially
     * finds the next available index in the hash table.
     *
     * @param Entry e: an Entry, consisting of a key and value, that will be put into the hash table.
     */
    
    private void putEntry(Entry<K,V> entry){
        if ((double)count/table.length>=loadfactor){
            rehash();
        }
        int index = entry.getKey().hashCode()%table.length;
        table[getNextAvailIndex(index)]=entry;
        count+=1;
    }
    
    /**
     * Helper function that receives the next available index to place an entry given an index. 
     * Returns the same index if the index is already empty.
     
     * @param int index: the index being checked for availability to store an Entry object into the hash table.
     */
        
    private int getNextAvailIndex(int index){
        boolean stop = false;
        while (stop==false){
            if (table[index]==null){
                return index;
                stop=true;
            }
            if (index>=table.length){
                index=-1;
            }
            index+=1;
        }
    }
    
    /**
     * Private class for hash table to associate key and value in an object format for convenient storage in hash table. Contains getter methods for both key and value.
     */
    
    private class Entry<K,V>{
        public K key;
        public V value;
        
        public Entry(K k, V v){
            key = k;
            value = v;
        }
        
        public K getKey(){
            return key;
        }
        
        public V getValue(){
            return value;
        }
    }
    
    /**
     * Removes the Entry containing the inputted key from the hash table and returns the value stored in that Entry object, i.e. the value corresponding to the key in the hash table.
     *
     * @param K key
     */
    
    public V remove(K key){
        if (get(key)==null){
            return null;
        }
        else{
            V storevalue = get(key);
            table[keyIndexFinder(key)]=null;
            return storevalue;
        }
    }
    
    /**
     * Gets value of the corresponding key of an Entry object in hash table.
     *
     * @param K key
     */
    
    public V get (K key){
        if(keyIndexFinder(key)==-1){
            return null;
        }
        else{
            return table[keyIndexFinder(key)].getValue();
        }
    }
    
    /**
     * Checks if key exists in an Entry object of hash table. Uses helper function keyIndexFinder; if keyIndexFinder returns -1, the index does
     * not exist, in which case the function would return false. Else, containsKey returns true.
     *
     * @param K key
     */
    
    public boolean containsKey(K key){
        if (keyIndexFinder(key)==-1){
            return false;
        }
        else{
            return true;
        }
    }
    
    
    /**
     * Finds the index at which an Entry object containing key is located in the hash table.
     *
     * @param K key
     */
    
    public int keyIndexFinder(K key){
        boolean stop = false;
        for (int index = key.hashCode()%table.length; stop==false; index++){
            if (table[index].getKey().equals(key)){
                return index;
            }
            else if (table[index]==null){
                return -1;
            }
            if (index>=table.length){
                index=-1;
            }
        }
        return -1;
    }
    
    /**
     * Checks if value exists in an Entry object of hash table.
     *
     * @param V value
     */
    
    public boolean containsValue(V value){
        for (int index = 0; index<table.length; index++){
            if (table[index].getValue().equals(value)){
                return true;
            }
        }
        return false;
    }
     /**
      * Rehashes the entire table to a hash table with a doubled capacity.
      */
    
     public void rehash(){
         Entry[] oldtable = table;
         table = new Entry[table.length*2];
         for (int i = 0; i<oldtable.length; i++){
             putEntry(oldtable[i]);
         }
     }
    
    
    /** 
     * Returns String representation of each slot in the hash table with indexes corresponding to Object memory addresses, or, in the case of
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