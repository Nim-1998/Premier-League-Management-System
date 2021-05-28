package models.entityLogics;

import models.entities.Match;

import java.util.Comparator;

public class DateComparator  implements  Comparator<Match> {

    public int compare(Match match1, Match match2) {
        return (match1.getMatchPlayedDate().compareTo(match2.getMatchPlayedDate()));
    }
}

