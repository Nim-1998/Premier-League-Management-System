package models.entities;

import java.util.Objects;
import java.io.Serializable;

public abstract class SportsClub implements Comparable<SportsClub> , Serializable {
    private String clubRegisterNo;
    private String clubName;
    private String clubLocation;
    private int noOfPlayedMatch;     //Number of previous played matches

    //Constructor
    public SportsClub(){}     //Default Constructor

    public SportsClub(String clubRegisterNo, String clubName, String clubLocation,int playedMatch){
        this.clubRegisterNo=clubRegisterNo;
        this.clubName=clubName;
        this.clubLocation=clubLocation;
        this.noOfPlayedMatch=playedMatch;
    }

    //setter methods
    public void setClubName(String clubName){
        this.clubName=clubName;
    }
    public void setClubLocation(String clubLocation){
        this.clubLocation=clubLocation;
    }
    public void setNoOfPlayedMatch(int playedMatch){
        this.noOfPlayedMatch=playedMatch;
    }
    //After created Club there is no access permission to modify clubRegisterNo

    //getter methods
    public String getClubRegisterNo(){
        return clubRegisterNo;
    }
    public String getClubName(){
        return clubName;
    }
    public String getClubLocation(){
        return clubLocation;
    }
    public int getNoOfPlayedMatch(){
        return noOfPlayedMatch;
    }

    //toString() method to return String representation of Objects
    public String toString(){
        return ("Sports club Register No : "+clubRegisterNo+
                " , Sports club Name : "+clubName+
                " , Club Location : "+clubLocation+
                " , No of Previous played matches : "+noOfPlayedMatch);
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
        SportsClub sportsClub=(SportsClub) object;        //Type Casting
        return     (	this.clubRegisterNo.equals(sportsClub.clubRegisterNo) &&
                this.clubName.equals(sportsClub.clubName) &&
                this.clubLocation.equals(sportsClub.clubLocation) &&
                this.noOfPlayedMatch == sportsClub.noOfPlayedMatch);
    }
    //hashCode() method to return an integer  value of Object address
    public int hashCode(){
        return Objects.hash(clubRegisterNo, clubName, clubLocation, noOfPlayedMatch);
    }
}