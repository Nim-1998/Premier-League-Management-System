package models.entityLogics;


import models.entities.FootballClub;
import models.entities.Match;
import models.entities.SportsClub;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;


public class PremierLeagueManager implements LeagueManager{
    private final List<SportsClub> footballClubList=new ArrayList<>();    //Create Football Club collection
    public static final int MAX_CLUB=20;


    //Create a collection to store played match details
    private final List<Match> playedMatchDetailCollection =new ArrayList<>();

    //Create a static variable
    private static PremierLeagueManager instance=null;

    //Create private constructor
    private PremierLeagueManager(){}

    //Lazy initialization : Use to avoid more than one instance creation from PremierLeagueManager for a class
    public static PremierLeagueManager getInstance(){
        if (instance==null){
            //Only checks if the "instance" is null
            synchronized (PremierLeagueManager.class){
                //Confirm instance is null
                if (instance==null){
                    instance=new PremierLeagueManager();
                }
            }
        }
        return instance;
    }


    //Override createFootballClub(SportsClub sportsClub) method
    public void createFootballClub(SportsClub sportsClub){
        if (footballClubList.contains(sportsClub)){
            System.out.println("There is a club already registered with this Register number or Club name. Please re-enter! ");
        }else if (footballClubList.size()==MAX_CLUB){
            System.out.println("There is no more space available for this club ");
        }else {
            footballClubList.add(sportsClub);
            //noOfAvailable-=1;
            System.out.println();
            System.out.println("Created Football club successfully added to the Premier League");
            System.out.println();
            System.out.println("Available space for new clubs in the premier league : "+(MAX_CLUB-footballClubList.size()));
        }
    }

    //Override deleteClub(String clubRegisterNo) method
    public void deleteClub(String clubRegisterNo) {
        boolean findClub=false;
        if (footballClubList.isEmpty()){
            System.out.println("Premier league is empty. There is no any football club details available.");
        }else {
            for (SportsClub  sportsClub : footballClubList){
                if (sportsClub.getClubRegisterNo().equals(clubRegisterNo)){
                    findClub=true;
                    footballClubList.remove(sportsClub);
                   // noOfAvailable+=1;
                    System.out.println("You selected club successfully deleted from the premier league");
                    System.out.println("Delete club details : "+sportsClub.toString());
                    System.out.println("Available space for new clubs in the premier league : "+(MAX_CLUB-footballClubList.size()));
                    break;
                }
            }
            if (!findClub){
                System.out.println("The club you want to delete is not in the premier league");
            }
        }
    }

    //Override displaySelectedClub(String clubRegisterNo) method
    public void displaySelectedClub(String clubRegisterNo) {
        boolean findSelectedClub=false;
        if (footballClubList.isEmpty()){
            System.out.println("Premier league is empty. There is no any football club details available.");
        }else {
            for (SportsClub  club : footballClubList){
                if (club.getClubRegisterNo().equals(clubRegisterNo)){
                    if (club instanceof FootballClub) {
                        findSelectedClub=true;
                        FootballClub footballClub = (FootballClub) club;      //Type Casting
                        System.out.println("Selected Football club statistics : ");
                        System.out.println("Club name : "+footballClub.getClubName());
                        System.out.println("Number of previous played matches : "+footballClub.getNoOfPlayedMatch());
                        System.out.println("Number of matches played in the season : "+footballClub.calculateTotalMatch());
                        System.out.println("Number of \"win\" matches in this season : "+footballClub.getNoOfWinMatch());
                        System.out.println("Number of \"draw\" matches in this season : "+footballClub.getNoOfDrawMatch());
                        System.out.println("Number of \"defeat\" matches in this season : "+footballClub.getNoOfDefeatMatch());
                        System.out.println("Number of \"goals\" scored in this season : "+footballClub.getNoOfGoalsScored());
                        System.out.println("Number of against received goals in this season: "+footballClub.getNoOfGoalsReceived());
                        System.out.println("Goal Difference : "+footballClub.getGoalDifference());
                        System.out.println("Number of \"points\" received in this season : "+footballClub.getCurrentPoint());
                        break;
                    }
                }
            }
            if (!findSelectedClub){
                System.out.println("You selected club not in the list");
            }
        }
    }

    //Override displayPremierLeagueTable() method
    public void displayPremierLeagueTable() {
        if (footballClubList.isEmpty()){
            System.out.println("Registered Football clubs are not in the premier league at this time");
        }else {
            List<SportsClub> footballClubTemperList=new ArrayList<>(footballClubList);
            Collections.sort(footballClubTemperList,Collections.reverseOrder());
            System.out.println("Premier League Table : ");
            for (SportsClub sportsClub : footballClubTemperList) {
                if (sportsClub instanceof FootballClub) {
                    FootballClub footballClub=(FootballClub) sportsClub;     //Type Casting
                    System.out.println("Club Name : "+sportsClub.getClubName()+"  Statistics in this season [ Played Matches : "+footballClub.calculateTotalMatch()+
                            " Win : "+footballClub.getNoOfWinMatch()+
                            " Draw : "+footballClub.getNoOfDrawMatch()+" Defeat : "+footballClub.getNoOfDefeatMatch()+
                            " Goals Score : "+footballClub.getNoOfGoalsScored()+" Goals Against Received : "+footballClub.getNoOfGoalsReceived()+
                            " Goal Difference : "+footballClub.getGoalDifference()+" Points : "+footballClub.getCurrentPoint()+" ]");
                }
            }
        }
        System.out.println();
    }

    //Override addPlayedMatch(Match match) method
    public void addPlayedMatch(Match match) {
        playedMatchDetailCollection.add(match);
        System.out.println("Played match details successfully recorded");
    }

    //Create a new method to check match played teams are registered in Football club List
    public  SportsClub[]  returnMatchPlayedTeam(String team1_RegisterNo, String team2_RegisterNo) {
        SportsClub[] matchPlayedTeam = new SportsClub[2];
        if (footballClubList.isEmpty()) {
            System.out.println("Premier league is empty. Can not add match details");
        } else {
            for (SportsClub sportsClub : footballClubList) {
                if (sportsClub.getClubRegisterNo().equals(team1_RegisterNo)) {
                    matchPlayedTeam[0] = sportsClub;
                    continue;
                }
                if (sportsClub.getClubRegisterNo().equals(team2_RegisterNo)) {
                    matchPlayedTeam[1] = sportsClub;
                    continue;
                }
            }
        }
        return matchPlayedTeam;
    }

    //Override saveSportsClubData(File fileName) method to  save all Sports Club details in to the file
    public void saveSportsClubData(File  fileName ,List<SportsClub> sportsClubList) {
        fileName.delete();
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream(fileName,true);
            objectOutputStream=new ObjectOutputStream(fileOutputStream);


            for (SportsClub sportsClub:sportsClubList){
                objectOutputStream.writeObject(sportsClub);
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("FileNotFound Exception occurs");
        } catch (IOException ioException){
            System.out.println("IOException occurs");
        }finally {
            try {
                objectOutputStream.flush();
                fileOutputStream.close();
                objectOutputStream.close();
            }catch (NullPointerException nullPointerException){
                System.out.println("NullPointerException occurs");
            }catch (IOException ioExceptionHandle){
                System.out.println("IOException occurs");
            }
        }
        System.out.println("All Sports Club data successfully save to the File");
    }

    //Override  saveMatchData(File fileName)  method to  save all played matches details in to the file
    public void saveMatchData(File  fileName, List<Match> matchList) {
        fileName.delete();
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream(fileName,true);
            objectOutputStream=new ObjectOutputStream(fileOutputStream);


            for (Match match:matchList){
                objectOutputStream.writeObject(match);
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("FileNotFound Exception");
        } catch (IOException ioException){
            System.out.println("IOException occurs");
        }finally {
            try {
                objectOutputStream.flush();
                fileOutputStream.close();
                objectOutputStream.close();
            }catch (NullPointerException nullPointerException){
                System.out.println("NullPointerException occurs");
            }catch (IOException ioExceptionHandle){
                System.out.println("IOException occurs");
            }
        }
        System.out.println("All match related data successfully save to the File");
    }

    //Override  loadSportsClubData(File fileName) method to load previous save Sports Club data from file
    public void loadSportsClubData(File fileName) {
        FileInputStream fileInputStream=null;
        ObjectInputStream objectInputStream=null;
        footballClubList.clear();

        try {
            fileInputStream=new FileInputStream(fileName);
            objectInputStream=new ObjectInputStream(fileInputStream);

            for (;;){
                try {
                    SportsClub sportsClub = (SportsClub) objectInputStream.readObject();

                    footballClubList.add(sportsClub);
                }catch (EOFException eofException){
                    break;
                }
            }
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("FileNotFound Exception occurs");
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Exception occurs");
        } finally {
            try {
                fileInputStream.close();
                objectInputStream.close();
            } catch (NullPointerException nullPointerException){
                System.out.println("NullPointerException occurs");
            } catch (IOException ioException) {
                System.out.println("IOException occurs");
            }
        }
        System.out.println("All Sports club data successfully load to the program");
    }

    //Override loadMatchData(File fileName) method to load previous save played match data from file
    public void loadMatchData(File fileName) {
        FileInputStream fileInputStream=null;
        ObjectInputStream objectInputStream=null;

        playedMatchDetailCollection.clear();
        try {
            fileInputStream=new FileInputStream(fileName);
            objectInputStream=new ObjectInputStream(fileInputStream);

            for (;;){
                try {
                    Match match = (Match) objectInputStream.readObject();
                    playedMatchDetailCollection.add(match);
                }catch (EOFException eofException){
                    break;
                }
            }
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("FileNotFound Exception occurs");
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Exception occurs");
        } finally {
            try {
                fileInputStream.close();
                objectInputStream.close();
            } catch (NullPointerException nullPointerException){
                System.out.println("NullPointerException occurs");
            } catch (IOException ioException) {
                System.out.println("IOException occurs");
            }
        }
        System.out.println("All match data successfully load to the program");
    }

    //Create a new method to check club register number is already created or not
    public boolean checkClubRegisterNo(String registerNo){
        boolean available=false;
        for (SportsClub sportsClub:footballClubList){
            if (sportsClub.getClubRegisterNo().equals(registerNo)){
                System.out.println("There is a club already register with this register number. Please re-enter");
                available=true;
            }
        }
        return available;
    }

    //Create a new method to check club name is already created or not
    public boolean checkClubName(String name){
        boolean available=false;
        for (SportsClub sportsClub:footballClubList){
            if (sportsClub.getClubName().equals(name)){
                System.out.println("There is a club already register with this club name. Please re-enter");
                available=true;
            }
        }
        return available;
    }

    //Create a method to return footballClubList
    public List<SportsClub> returnFootballClubList(){
        return footballClubList;
    }

    //Create a method to return playedMatchDetailCollection
    public List<Match> returnPlayedMatchList(){
        return playedMatchDetailCollection;
    }


}