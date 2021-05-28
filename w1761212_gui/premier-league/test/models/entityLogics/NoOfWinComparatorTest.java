package models.entityLogics;

import junit.framework.TestCase;
import models.entities.FootballClub;
import models.entities.SportsClub;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NoOfWinComparatorTest extends TestCase {

    LeagueManager leagueManager = PremierLeagueManager.getInstance();
    List<SportsClub> testList = new ArrayList<>();  //Create an empty List
    public void testCompare() {


            FootballClub footballClub1 = new FootballClub("1","AAA","AA",10);
            footballClub1.setNoOfWinMatch(2);
            FootballClub footballClub2 = new FootballClub("2","BBB","BB",5);
            footballClub2.setNoOfWinMatch(10);
            FootballClub footballClub3 = new FootballClub("3","CCC","CC",6);
            footballClub3.setNoOfWinMatch(0);
            FootballClub footballClub4 = new FootballClub("4","DDD","DD",3);
            footballClub4.setNoOfWinMatch(5);

            List<SportsClub> orderedList = new ArrayList<>();
            orderedList = Arrays.asList(footballClub2,footballClub4,footballClub1,footballClub3);

            //Test Point comparator
            if (leagueManager instanceof PremierLeagueManager){
                PremierLeagueManager manager = (PremierLeagueManager) leagueManager;

                manager.createFootballClub(footballClub1);
                manager.createFootballClub(footballClub2);
                manager.createFootballClub(footballClub3);
                manager.createFootballClub(footballClub4);

                testList = manager.returnFootballClubList();
                Collections.sort(testList,new NoOfWinComparator().reversed());
                Assert.assertEquals(orderedList,testList);
            }

        }
}