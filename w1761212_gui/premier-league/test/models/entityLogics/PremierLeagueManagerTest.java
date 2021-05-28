package models.entityLogics;

import junit.framework.TestCase;
import models.entities.Date;
import models.entities.FootballClub;
import models.entities.Match;
import models.entities.SportsClub;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PremierLeagueManagerTest extends TestCase {

    LeagueManager leagueManager = PremierLeagueManager.getInstance();
    boolean test1 = false;
    boolean test2 = false;

    List<SportsClub> testList = new ArrayList<>();  //Create an empty List

    SportsClub sportsClub1= new FootballClub("2","EFGH","PQR",10);
    SportsClub sportsClub2 = new FootballClub("1","ABCD","XYZ",8);

    public void testCreateFootballClub() {
        if (leagueManager instanceof PremierLeagueManager){
            PremierLeagueManager manager = (PremierLeagueManager) leagueManager;
            testList = manager.returnFootballClubList();
            manager.createFootballClub(sportsClub1);
            for (SportsClub sportsClub:testList){
                if (sportsClub.equals(sportsClub1)){
                    test1= true;
                }
            }
            Assert.assertEquals(true,test1);
        }

    }

    public void testDeleteClub() {
        if (leagueManager instanceof PremierLeagueManager){
            PremierLeagueManager manager = (PremierLeagueManager) leagueManager;
            testList = manager.returnFootballClubList();
            manager.createFootballClub(sportsClub2);
            for (SportsClub sportsClub:testList){
                if (sportsClub.equals(sportsClub2)){
                    test1= true;
                }
            }
            Assert.assertEquals(true,test1);
            manager.deleteClub("1");
            for (SportsClub sportsClub:testList){
                if (sportsClub.equals(sportsClub2)){
                    test2= true;
                }
            }
            Assert.assertEquals(false,test2);
        }
    }

    public void testDisplaySelectedClub() {
        if (leagueManager instanceof PremierLeagueManager){
            PremierLeagueManager manager = (PremierLeagueManager) leagueManager;
            testList = manager.returnFootballClubList();
            manager.createFootballClub(sportsClub1);
            manager.createFootballClub(sportsClub2);
            manager.displaySelectedClub("2");
            for (SportsClub sportsClub:testList){
                if (sportsClub.equals(sportsClub1)){
                    test1= true;
                }
            }
            Assert.assertEquals(true,test1);
        }
    }

    public void testDisplayPremierLeagueTable() {
        FootballClub footballClub1 = new FootballClub("1","AAA","AA",10);
        footballClub1.setCurrentPoint(6);
        FootballClub footballClub2 = new FootballClub("2","BBB","BB",5);
        footballClub2.setCurrentPoint(-1);
        FootballClub footballClub3 = new FootballClub("3","CCC","CC",6);
        footballClub3.setCurrentPoint(0);
        FootballClub footballClub4 = new FootballClub("4","DDD","DD",3);
        footballClub4.setCurrentPoint(2);

        //Add these clubs to empty list with descending order of points
        List<SportsClub> orderedList = new ArrayList<>();
        orderedList =Arrays.asList(footballClub1,footballClub4,footballClub3,footballClub2);

        //Test Point comparator
        if (leagueManager instanceof PremierLeagueManager){
            PremierLeagueManager manager = (PremierLeagueManager) leagueManager;

            manager.createFootballClub(footballClub1);
            manager.createFootballClub(footballClub2);
            manager.createFootballClub(footballClub3);
            manager.createFootballClub(footballClub4);

            manager.displayPremierLeagueTable();
            testList = manager.returnFootballClubList();
            Collections.sort(testList,new PointComparator().reversed());
            Assert.assertEquals(orderedList,testList);
        }
    }

    public void testAddPlayedMatch() {
        SportsClub footballClub1 = new FootballClub("1","AAA","AA",10);

        SportsClub footballClub2 = new FootballClub("2","BBB","BB",5);

        Date date = new Date(1,4,2021);
        SportsClub[] team =  new SportsClub[2];
        team[0] = footballClub1;
        team[1]=footballClub2;
        Match match = new Match(date,team);
        if (leagueManager instanceof PremierLeagueManager){
            PremierLeagueManager manager = (PremierLeagueManager) leagueManager;

            manager.addPlayedMatch(match);
            List<Match> matchList = manager.returnPlayedMatchList();
            for (Match match1 : matchList){
                if (match1.equals(match)){
                    test1= true;
                }
            }
            Assert.assertEquals(true,test1);
        }
    }
}