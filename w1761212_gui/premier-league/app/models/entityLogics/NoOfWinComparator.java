package models.entityLogics;

import models.entities.FootballClub;
import models.entities.SportsClub;

import java.util.Comparator;

public class  NoOfWinComparator implements Comparator<SportsClub> {

    public int compare(SportsClub sportsClub1, SportsClub sportsClub2) {

        FootballClub footballClub1=new FootballClub();
        if (sportsClub1 instanceof FootballClub){
            footballClub1=(FootballClub) sportsClub1;    //Type Casting
        }
        FootballClub footballClub2=new FootballClub();
        if (sportsClub2 instanceof FootballClub){
            footballClub2=(FootballClub) sportsClub2;    //Type Casting
        }

        if (footballClub1.getNoOfWinMatch()==footballClub2.getNoOfWinMatch()){
            return 0;
        }else if (footballClub1.getNoOfWinMatch()>footballClub2.getNoOfWinMatch()){
            return +1;
        }else {
            return -1;
        }
    }
}
