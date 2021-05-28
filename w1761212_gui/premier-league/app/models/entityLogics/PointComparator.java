package models.entityLogics;

import models.entities.SportsClub;

import java.util.Comparator;

public class PointComparator implements Comparator<SportsClub> {

    public int compare(SportsClub sportsClub1, SportsClub sportsClub2) {
        return (sportsClub1.compareTo(sportsClub2));
    }
}
