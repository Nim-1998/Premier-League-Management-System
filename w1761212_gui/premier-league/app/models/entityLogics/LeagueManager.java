package models.entityLogics;

import models.entities.Match;
import models.entities.SportsClub;

import java.io.File;
import java.util.List;

public interface LeagueManager{

    void createFootballClub(SportsClub sportsClub);
    void deleteClub(String clubRegisterNo);
    void displaySelectedClub(String clubRegisterNo);
    void displayPremierLeagueTable();
    void addPlayedMatch(Match match);
    void saveSportsClubData(File fileName, List<SportsClub> sportsClubList);
    void saveMatchData(File fileName , List<Match> matchList);
    void loadSportsClubData(File fileName);
    void loadMatchData(File fileName);
}
