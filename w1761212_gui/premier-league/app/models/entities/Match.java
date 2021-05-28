package models.entities;


import java.io.Serializable;

public class Match implements Serializable {
    private Date matchPlayedDate;
    private SportsClub[] matchPlayedClub;   //This Array contains two SportsClub objects who played match
    private int team01GoalScore;
    private int team02GoalScore;

    public Match(Date matchPlayedDate,SportsClub[] matchPlayedClub) {
        this.matchPlayedDate = matchPlayedDate;
        this.matchPlayedClub = matchPlayedClub;
        this.team01GoalScore=0;
        this.team02GoalScore=0;
    }

    //setter methods
    public void setMatchPlayedDate(Date matchPlayedDate) {
        this.matchPlayedDate = matchPlayedDate;
    }
    public void setMatchPlayedClub(SportsClub[] matchPlayedClub) {
        this.matchPlayedClub = matchPlayedClub;
    }
    public void setTeam01GoalScore(int team01GoalScore) {
        this.team01GoalScore = team01GoalScore;
    }

    public void setTeam02GoalScore(int team02GoalScore) {
        this.team02GoalScore = team02GoalScore;
    }

    //getter methods
    public Date getMatchPlayedDate() {
        return matchPlayedDate;
    }
    public SportsClub[] getMatchPlayedClub() {
        return matchPlayedClub;
    }
    public int getTeam01GoalScore() {
        return team01GoalScore;
    }
    public int getTeam02GoalScore() {
        return team02GoalScore;
    }

    //Create a method to update match played teams statistics
    public void updateClubStatistics(){

        FootballClub team01 = new FootballClub() ;
        if (matchPlayedClub[0] instanceof FootballClub) {
            team01 = (FootballClub) matchPlayedClub[0];     //Type Casting
        }
        FootballClub team02=new FootballClub() ;
        if (matchPlayedClub[1] instanceof FootballClub) {
            team02 = (FootballClub) matchPlayedClub[1];     //Type Casting
        }

        if (this.team01GoalScore==this.team02GoalScore){
            //Update team's point
            team01.setCurrentPoint(1);
            team02.setCurrentPoint(1);
            //Update number of Draw matches
            team01.setNoOfDrawMatch(1);
            team02.setNoOfDrawMatch(1);
        }else if (this.team01GoalScore>this.team02GoalScore){
            //Update match winner's point
            team01.setCurrentPoint(3);
            //Update number of Win matches
            team01.setNoOfWinMatch(1);
            //Update number of Defeat matches
            team02.setNoOfDefeatMatch(1);
        }else {
            //Update match winner's point
            team02.setCurrentPoint(3);
            //Update number of Win matches
            team02.setNoOfWinMatch(1);
            //Update number of Defeat matches
            team01.setNoOfDefeatMatch(1);
        }
        //Set against received goals
        team01.setNoOfGoalsReceived(this.team02GoalScore);
        team02.setNoOfGoalsReceived(this.team01GoalScore);
    }

}
