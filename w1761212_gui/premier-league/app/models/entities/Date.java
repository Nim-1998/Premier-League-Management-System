package models.entities;

import java.util.Objects;
import java.io.Serializable;

public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year){
        this.day=day;
        this.month=month;
        this.year=year;
    }

    //setter methods
    public void setDay(int day){
        this.day=day;
    }
    public void setMonth(int month){
        this.month=month;
    }
    public void setYear(int year){
        this.year=year;
    }

    //getter methods
    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }

    //toString() method to return String representation of Objects
    public String toString(){
        return String.format("%02d/%02d/%04d",day,month,year);
    }

    //equals() method to compare objects
    public boolean equals(Object object){
        //First checks two objects have the same reference
        if (this ==object){
            return true;
        }
        if(object ==null || getClass() != object.getClass()){
            return false;
        }
        Date date=(Date) object;         //type casting
        return (this.day ==date.day && this.month==date.month && this.year==date.year);
    }
    //hashCode() method to return an integer  value of Object address
    public int hashCode(){
        return Objects.hash(day, month, year);
    }

    //Create a new method to get match played date in days
    public int getDateInDay(){
        return (this.year*365+this.month*30+this.day);
    }

    public int compareTo(Date date){
        if (this.getDateInDay()==date.getDateInDay()){
            return 0;
        }else if (this.getDateInDay()>date.getDateInDay()){
            return +1;
        }else {
            return -1;
        }
    }
}
