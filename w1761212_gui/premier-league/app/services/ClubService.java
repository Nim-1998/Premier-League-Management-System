package services;

import models.entities.Date;
import models.entities.FootballClub;
import models.entities.Match;
import models.entities.SportsClub;
import models.entityLogics.LeagueManager;
import models.entityLogics.PremierLeagueManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ClubService {
    //Get an instance of PremierLeagueManager class
    LeagueManager leagueManager = PremierLeagueManager.getInstance();
    static  PremierLeagueManager  manager;

    //Create a static variable
    private static ClubService instance =null;
    //Create private constructor
    private ClubService(){}

    //Lazy initialization : Use to avoid more than one instance creation from PremierLeagueManager for a class
    public static ClubService getInstance(){
        if (instance == null){
            synchronized (ClubService.class){
                if (instance == null){
                    instance = new ClubService();
                }
            }
        }
        return instance;
    }
    File sportsClubDataFile=new File("../sportsClubDataFile.txt");
    File matchDataFile=new File("../matchDataFile.txt");



    //Load Football Club details from the file
    public List<SportsClub> returnLoadedFootballList(){
        List<SportsClub> footballList =new ArrayList<>();

        FileInputStream fileInputStream =null;
        ObjectInputStream objectInputStream=null;


        try {
            fileInputStream = new FileInputStream(sportsClubDataFile);
            objectInputStream = new ObjectInputStream(fileInputStream);

            for (;;){
                try {
                    SportsClub sportsClub = (SportsClub) objectInputStream.readObject();
                    footballList.add(sportsClub);
                }catch (EOFException eofException){
                    break;
                }
            }

        } catch (FileNotFoundException fileNotFound) {
            System.out.println("FileNotFound Exception occurs");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception occurs");
        }
        finally {
            try {
                fileInputStream.close();
                objectInputStream.close();
            } catch (NullPointerException nullPointerException){
                System.out.println("NullPointerException occurs");
            } catch (IOException ioException) {
                System.out.println("IOException occurs");
            }
        }

        return footballList;
    }

    //Load played match details from the file
    public List<Match> returnLoadedMatchList(){
        List<Match> matchList=new ArrayList<>();

        FileInputStream fileInputStream =null;
        ObjectInputStream objectInputStream=null;

        try {
            fileInputStream = new FileInputStream(matchDataFile);
            objectInputStream = new ObjectInputStream(fileInputStream);

            for (;;){
                try {
                    Match match = (Match) objectInputStream.readObject();
                    matchList.add(match);
                }catch (EOFException eofException){
                    break;
                }
            }

        } catch (FileNotFoundException fileNotFound) {
            System.out.println("FileNotFound Exception occurs");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception occurs");
        }
        finally {
            try {
                fileInputStream.close();
                objectInputStream.close();
            } catch (NullPointerException nullPointerException){
                System.out.println("NullPointerException occurs");
            } catch (IOException ioException) {
                System.out.println("IOException occurs");
            }
        }
        return  matchList;

    }

    //Generate random match
    public Match generateRandomMatch(){
        Match randomMatchObject = null;
        List<SportsClub> footballClubList = returnLoadedFootballList();
        List<Match> matchList = returnLoadedMatchList();
        int footballClubSize=returnLoadedFootballList().size();    //Get  footballClubList size
        if (footballClubSize >= 2) {
            //Create an Array to store two clubs who are played random generated Match
            SportsClub[] twoRandomTeamList = new SportsClub[2];

            //Generate Teams Goal Score
            //Generate random number between [0,11)
            int team01Goal = (int) (Math.random() * (11) + 0);
            int team02Goal = (int) (Math.random() * (11) + 0);

            //Generate random number to select two different Football clubs
            int randomNumber1 = (int) ((Math.random() * (footballClubSize)) + 0);
            SportsClub randomTeam1 = footballClubList.get(randomNumber1);
            twoRandomTeamList[0] = randomTeam1;
            //Set goals score
            if (randomTeam1 instanceof FootballClub) {
                FootballClub randomFootballTeam1 = (FootballClub) randomTeam1;   //Type Casting
                randomFootballTeam1.setNoOfGoalsScored(team01Goal);
            }

            int randomNumber2;
            do {
                randomNumber2 = (int) ((Math.random() * (footballClubSize)) + 0);
            } while (randomNumber2 == randomNumber1);

            SportsClub randomTeam2 = footballClubList.get(randomNumber2);
            twoRandomTeamList[1] = randomTeam2;
            //Set goals score
            if (randomTeam2 instanceof FootballClub) {
                FootballClub randomFootballTeam2 = (FootballClub) randomTeam2;    //Type Casting
                randomFootballTeam2.setNoOfGoalsScored(team02Goal);
            }

            //Generate random match played Date
            int matchPlayedDay = 1 + (int) (Math.random() * ((30 - 1) + 1));
            int matchPlayedMonth = 1 + (int) (Math.random() * ((12 - 1) + 1));
            int matchPlayedYear = LocalDate.now().getYear();     //Set local year

            //Create Match object and Pass random generated values
            randomMatchObject = new Match(new Date(matchPlayedDay, matchPlayedMonth, matchPlayedYear), twoRandomTeamList);
            randomMatchObject.setTeam01GoalScore(team01Goal);
            randomMatchObject.setTeam02GoalScore(team02Goal);

            //Update Football club's statistics
            randomMatchObject.updateClubStatistics();
            //Add create match object to the Match Collection
            matchList.add(randomMatchObject);

        }
        //Save Match details to the File
        if (leagueManager instanceof PremierLeagueManager) {
            manager = (PremierLeagueManager) leagueManager;
            manager.saveSportsClubData(sportsClubDataFile,footballClubList);
            manager.saveMatchData(matchDataFile, matchList);
        }
        return  randomMatchObject;
    }


}
