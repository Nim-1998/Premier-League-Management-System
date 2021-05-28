package models.entities;


import java.util.Objects;

public class UniversityFootballClub extends FootballClub{
    private String universityName;
    private String deanName;

    public UniversityFootballClub(String clubRegisterNo, String clubName, String clubLocation, int noOfPlayedMatch,String universityName,String deanName){
        super(clubRegisterNo, clubName, clubLocation, noOfPlayedMatch);

        this.universityName=universityName;
        this.deanName=deanName;
    }

    //setter methods
    public void setUniversityName(String name){
        this.universityName=name;
    }
    public void setDeanName(String deanName){
        this.deanName=deanName;
    }

    //getter methods
    public String getUniversityName(){
        return universityName;
    }
    public String getDeanName(){
        return deanName;
    }

    //toString() method to return String representation of Objects
    public String toString(){
        return (super.toString()+
                " , University Name : "+universityName+
                " , Dean Name : "+deanName);
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
        UniversityFootballClub university = (UniversityFootballClub) object;        //type casting
        return (this.universityName.equals(university.universityName) &&
                this.deanName.equals(university.deanName));
    }
    //hashCode() method to return an integer  value of Object address
    public int hashCode(){
        return Objects.hash(super.hashCode(), universityName, deanName);
    }
}