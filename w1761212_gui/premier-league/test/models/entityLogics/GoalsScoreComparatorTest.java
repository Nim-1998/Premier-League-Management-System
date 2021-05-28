package models.entityLogics;

import junit.framework.TestCase;
import models.entities.FootballClub;
import models.entities.SportsClub;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GoalsScoreComparatorTest extends TestCase {

    LeagueManager leagueManager = PremierLeagueManager.getInstance();
    List<SportsClub> testList = new ArrayList<>();  //Create an empty List
    public void testCompare() {
        FootballClub footballClub1 = new FootballClub("1","AAA","AA",10);
        footballClub1.setNoOfGoalsScored(5);
        FootballClub footballClub2 = new FootballClub("2","BBB","BB",5);
        footballClub2.setNoOfGoalsScored(12);
        FootballClub footballClub3 = new FootballClub("3","CCC","CC",6);
        footballClub3.setNoOfGoalsScored(2);
        FootballClub footballClub4 = new FootballClub("4","DDD","DD",3);
        footballClub4.setNoOfGoalsScored(0);

        List<SportsClub> orderedList = new ArrayList<>();
        orderedList = Arrays.asList(footballClub2,footballClub1,footballClub3,footballClub4);

        //Test Point comparator
        if (leagueManager instanceof PremierLeagueManager){
            PremierLeagueManager manager = (PremierLeagueManager) leagueManager;

            manager.createFootballClub(footballClub1);
            manager.createFootballClub(footballClub2);
            manager.createFootballClub(footballClub3);
            manager.createFootballClub(footballClub4);

            testList = manager.returnFootballClubList();
            Collections.sort(testList,new GoalsScoreComparator().reversed());
            Assert.assertEquals(orderedList,testList);
        }

    }
}