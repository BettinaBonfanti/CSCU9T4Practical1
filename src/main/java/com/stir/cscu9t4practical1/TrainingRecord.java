// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import javax.swing.*;
import java.nio.file.NoSuchFileException;
import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
    public String addEntry(Entry e){
        String message = "";
        if (isUnique(e)){
            tr.add(e);
            message = "Record added";
        }
        else {
            System.out.println("Entry cannot be added twice");
            message = "Entry already added";
        }
        return message;
   } // addClass

    public boolean isUnique(Entry e){
        boolean isUnique = true;
        ListIterator<Entry> iter = tr.listIterator();
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getName().equalsIgnoreCase(e.getName()) && current.getDay()==e.getDay()
            && current.getMonth()==e.getMonth() && current.getYear()==e.getYear()){
                isUnique = false;
            }
        }
        return isUnique;
    }

   
   // look up the entry of a given day and month
    public String lookupEntry (int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "No entries found";
        if (!iter.hasNext()){
            result = "No entries found";
        }
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) {
                result = current.getEntry();
            }
        }
        return result;
    } // lookupEntry

    public String findAllByDate (int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        if (!iter.hasNext()){
            result = "No entries found";
        }
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y)
                result = result + current.getEntry();
            else{
                result = "No entries found";
            }
        }
        return result;
    }

    public String removeEntry (int d, int m, int y, String name) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        if (!iter.hasNext()){
            result = "No entries found";
        }
        while (iter.hasNext()) {
                Entry current = iter.next();
                if (current.getName().equalsIgnoreCase(name) && current.getDay()==d && current.getMonth()==m && current.getYear()==y) {
                    tr.remove(current);
                    result = "Entry deleted";
                }
        }
        return result;
    }
    public String weeklyDistance (String name) {
        ListIterator<Entry> iter = tr.listIterator();
        float result = 0;
        String weeklyDistance = "";
        if (!iter.hasNext()){
            result = 0;
        }
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.add(Calendar.DATE, -7);
        Calendar weekAgo = cal;
        for (Calendar currentDay = Calendar.getInstance(TimeZone.getDefault()); currentDay.after(weekAgo); currentDay.add(Calendar.DATE, -1)){
            while (iter.hasNext()) {
                Entry current = iter.next();
                if (current.getName().equalsIgnoreCase(name) && current.getDay()==currentDay.get(Calendar.DATE)
                        && current.getMonth()==currentDay.get(Calendar.MONTH) && current.getYear()==currentDay.get(Calendar.YEAR)) {
                    result = result + current.getDistance();
                }
            }
        }
        weeklyDistance = name + " weekly distance is: " + result;
        return weeklyDistance;
    }

    public String findByName (String name) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        if (!iter.hasNext()){
            result = "No entries found";
        }
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getName().equalsIgnoreCase(name)) {
                result = result + current.getEntry() ;
            }
        }
        return result;
    }
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord