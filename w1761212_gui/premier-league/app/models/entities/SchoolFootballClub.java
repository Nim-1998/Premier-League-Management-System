package models.entities;

import java.util.Objects;

public class SchoolFootballClub extends FootballClub {
    private String schoolName;
    private String principalName;

    public SchoolFootballClub(String clubRegisterNo, String clubName, String clubLocation, int noOfPlayedMatch,String schoolName,String principalName) {
        super(clubRegisterNo, clubName, clubLocation, noOfPlayedMatch);

        this.schoolName=schoolName;
        this.principalName=principalName;
    }

    //setter methods
    public void setSchoolName(String name){
        this.schoolName=name;
    }
    public void setPrincipalName(String principalName){
        this.principalName=principalName;
    }

    //getter methods
    public String getSchoolName(){
        return schoolName;
    }
    public String getPrincipalName(){
        return principalName;
    }

    //toString() method to return String representation of Objects
    public String toString(){
        return (super.toString()+
                " , School Name : "+schoolName+
                " , Principal Name : "+principalName);
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
        if(!super.equals(object)){
            return false;
        }
        SchoolFootballClub school = (SchoolFootballClub) object;        //type casting
        return (this.schoolName.equals(school.schoolName) &&
                this.principalName.equals(school.principalName));
    }
    //hashCode() method to return an integer  value of Object address
    public int hashCode(){
        return Objects.hash(super.hashCode(), schoolName, principalName);
    }
}