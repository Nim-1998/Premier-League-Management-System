package models.entities;

import java.util.Objects;
import java.io.Serializable;

public class FootballClub extends SportsClub implements Comparable<SportsClub> , Serializable {
    //This season related statistics of FootballClub
    private int noOfWinMatch;
    private int noOfDrawMatch;
    private int noOfDefeatMatch;
    private int currentPoint;
    private int noOfGoalsScored;      //Total number of goals scored by club
    private int noOfGoalsReceived;    //Total number of against goals received
    private int noOfTotalSeriousPlayedMatch;


    //Constructor
    public FootballClub(){}	//Default Constructor

    public FootballClub(String clubRegisterNo, String clubName, String clubLocation, int noOfPlayedMatch){
        super(clubRegisterNo, clubName, clubLocation, noOfPlayedMatch);

        this.noOfWinMatch=0;
        this.noOfDrawMatch=0;
        this.noOfDefeatMatch=0;
        this.currentPoint=0;
        this.noOfGoalsScored=0;
        this.noOfGoalsReceived=0;
        this.noOfTotalSeriousPlayedMatch=0;

    }

    //setter methods
    public void setNoOfWinMatch(int winMatch){
        this.noOfWinMatch+=winMatch;
    }
    public void setNoOfDrawMatch(int drawMatch) {
        this.noOfDrawMatch += drawMatch;
    }
    public void setNoOfDefeatMatch(int defeatMatch) {
        this.noOfDefeatMatch += defeatMatch;
    }
    public void setCurrentPoint(int point) {
        this.currentPoint += point;
    }
    public void setNoOfGoalsScored(int goal) {
        this.noOfGoalsScored += goal;
    }
    public void setNoOfGoalsReceived(int againstGoal){
        this.noOfGoalsReceived += againstGoal;
    }


    //getter methods
    public int getNoOfWinMatch() {
        return noOfWinMatch;
    }
    public int getNoOfDrawMatch() {
        return noOfDrawMatch;
    }
    public int getNoOfDefeatMatch() {
        return noOfDefeatMatch;
    }
    public int getCurrentPoint() {
        return currentPoint;
    }
    public int getNoOfGoalsScored() {
        return noOfGoalsScored;
    }
    public int getNoOfGoalsReceived() {
        return noOfGoalsReceived;
    }
    public int getNoOfTotalSeriousPlayedMatch() {
        return calculateTotalMatch();
    }


    //Calculate total number of matches played by club in the season
    public int calculateTotalMatch(){
        return (this.noOfWinMatch+this.noOfDefeatMatch+this.noOfDrawMatch);
    }

    //toString() method to return String representation of Objects
    public String toString(){
        return (super.toString()+" This season statistics [ "+
                " , No of total matches played by club in the season : "+calculateTotalMatch()+
                " , No of win matches : "+noOfWinMatch+
                " , No of Draw matches : "+ noOfDrawMatch+
                " , No of Defeat matches : "+noOfDefeatMatch+
                " , Current Points : "+currentPoint+
                " , No of Scored Goals : "+noOfGoalsScored+
                " , No of Against received goals : "+noOfGoalsReceived+
                " , Goal Difference : "+getGoalDifference()+"  ]");
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
        FootballClub footballClub=(FootballClub) object;        //Type Casting
        return (this.noOfWinMatch == footballClub.noOfWinMatch &&
                this.noOfDrawMatch == footballClub.noOfDrawMatch &&
                this.noOfDefeatMatch == footballClub.noOfDefeatMatch &&
                this.currentPoint == footballClub.currentPoint &&
                this.noOfGoalsScored == footballClub.noOfGoalsScored &&
                this.noOfGoalsReceived == footballClub.noOfGoalsReceived);
    }
    //hashCode() method to return an integer  value of Object address
    public int hashCode(){
        return Objects.hash(super.hashCode(), noOfWinMatch, noOfDrawMatch, noOfDefeatMatch, currentPoint, noOfGoalsScored,noOfGoalsReceived);
    }

    //Calculate goal difference of object
    public int getGoalDifference(){
        return (this.getNoOfGoalsScored()-this.getNoOfGoalsReceived());
    }

    public int compareTo(SportsClub sportsClub) {
        FootballClub footballClub=new FootballClub();
        if (sportsClub instanceof FootballClub){
            footballClub=(FootballClub) sportsClub;       //Type Casting
        }
        if (this.getCurrentPoint()==footballClub.getCurrentPoint()){
            if (this.getGoalDifference()==footballClub.getGoalDifference()){
                return  0;
            }else if (this.getGoalDifference()>footballClub.getGoalDifference()){
                return +1;
            }else {
                return -1;
            }
        }else if (this.getCurrentPoint()>footballClub.getCurrentPoint()){
            return +1;
        }else {
            return -1;
        }
    }
}