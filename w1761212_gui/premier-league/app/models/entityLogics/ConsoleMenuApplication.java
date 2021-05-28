package models.entityLogics;

import java.net.URI;
import java.util.Scanner;
import java.io.File;

import models.entities.Date;
import models.entities.FootballClub;
import models.entities.Match;
import models.entities.SportsClub;

import java.time.LocalDate;


public class ConsoleMenuApplication {
    final static Scanner USER_INPUT=new Scanner(System.in);

    //Create an instance of PremierLeagueManager
    static  LeagueManager  leagueManager=PremierLeagueManager.getInstance();
    static  PremierLeagueManager  manager;

    public static void main(String[] args) {
        if (leagueManager instanceof PremierLeagueManager){
            manager=(PremierLeagueManager) leagueManager;  //Type Casting
        }


        //Load previous stored data from file
        File sportsClubDataFile=new File("../sportsClubDataFile.txt");
        File matchDataFile=new File("../matchDataFile.txt");

        if (sportsClubDataFile.exists()) {
            manager.loadSportsClubData(sportsClubDataFile);
        }
        if (matchDataFile.exists()){
            manager.loadMatchData(matchDataFile);
        }


        String selection="";
        while (!selection.equals("7")){
            displaySelectionMenu();        //Call method to display menu
            selection=USER_INPUT.next();
            switch (selection){
                case "1":
                    createFootballClub();
                    //Save data to the File
                    manager.saveSportsClubData(sportsClubDataFile,manager.returnFootballClubList());
                    break ;
                case "2":
                    deleteExistingClub();
                    //Save data to the File
                    manager.saveSportsClubData(sportsClubDataFile,manager.returnFootballClubList());
                    break ;
                case "3":
                    //Load previous stored data from file
                    if (sportsClubDataFile.exists()) {
                        manager.loadSportsClubData(sportsClubDataFile);
                    }
                    if (matchDataFile.exists()){
                        manager.loadMatchData(matchDataFile);
                    }

                    displaySelectedClub();
                    break ;
                case "4":
                    //Load previous stored data from file
                    if (sportsClubDataFile.exists()) {
                        manager.loadSportsClubData(sportsClubDataFile);
                    }
                    if (matchDataFile.exists()){
                        manager.loadMatchData(matchDataFile);
                    }

                    manager.displayPremierLeagueTable();
                    break ;
                case "5":
                    addPlayedMatchDetail();
                    //Save data to the File
                    manager.saveSportsClubData(sportsClubDataFile,manager.returnFootballClubList());
                    manager.saveMatchData(matchDataFile,manager.returnPlayedMatchList());
                    break ;
                case "6":
                    String frontEndUrl = "http://localhost:4200";
                    try {
                        java.awt.Desktop.getDesktop().browse(URI.create(frontEndUrl));
                    }catch (Exception e){
                        System.out.println("User Interface loading error");
                    }

                    break;
                case "7":
                    //Before end the program need to save all data into the File

                    manager.saveSportsClubData(sportsClubDataFile,manager.returnFootballClubList());
                    manager.saveMatchData(matchDataFile,manager.returnPlayedMatchList());
                    System.out.println("End of the Program. Thank you for using the Program.");
                    System.exit(0);
                    break ;
                default:
                    System.out.println("You selected an Invalid Number. Please recheck your selection ");
                    break;
            }
        }
    }


    //Create user selection menu list
    private static void displaySelectionMenu() {
        System.out.println();
        System.out.println("--------Premier League Championship Management System--------");
        System.out.println("Enter your selection number what you would like to do : ");
        System.out.println("1 : Create a new Football Club");
        System.out.println("2 : Delete an existing club");
        System.out.println("3 : Display selected club statistics");
        System.out.println("4 : Display sorted Premier League table");
        System.out.println("5 : Add played match details");
        System.out.println("6 : View Graphical User Interface");
        System.out.println("7 : Exit the program");
    }

    //Create a new FootballClub
    private static void createFootballClub() {
        SportsClub sportsClub;
        System.out.println("------Enter club details------");
        System.out.println();

        //Get unique club register number
        String registerNo;
        do {
            System.out.print("Enter club register number : ");
            registerNo=USER_INPUT.next();
        }while (manager.checkClubRegisterNo(registerNo));    //Call  checkClubRegisterNo() method

        //Get unique club name
        String name;
        do {
            System.out.print("Enter club name : ");
            name=USER_INPUT.next().toLowerCase();
        }while (manager.checkClubName(name));		//Call  checkClubName() method

        System.out.print("Enter club location name : ");
        String location=USER_INPUT.next();

        int previousPlayedMatchNo=validateUserIntInput("Enter number of previous played matches : ");     //Call validateUserInput() method

        sportsClub=new FootballClub(registerNo,name,location,previousPlayedMatchNo);    //Create a new FootballClub object
        manager.createFootballClub(sportsClub);         //Add created football club in to the premier league
        System.out.println();
    }

    //Delete an existing club
    private static void deleteExistingClub() {
        System.out.println("Enter club register number : ");
        String registerNo=USER_INPUT.next();
        manager.deleteClub(registerNo);
        System.out.println();
    }

    //Display selected club details
    private static void displaySelectedClub() {
        System.out.println("Enter club register number what you want to display statistics :");
        String selectedClubNo=USER_INPUT.next();
        manager.displaySelectedClub(selectedClubNo);
        System.out.println();
    }

    //Add played match details
    private static void addPlayedMatchDetail() {
        System.out.print("Enter team 01 register number : ");
        String team01RegisterNo=USER_INPUT.next();
        System.out.print("Enter team 02 register number : ");
        String team02RegisterNo=USER_INPUT.next();

        //Create an Array to store match played two SportsClub details
        SportsClub[] matchedPlayedTeam;
        //Call returnMatchPlayedTeam() method to check entered SportsClub register numbers are in Premier League
        matchedPlayedTeam=manager.returnMatchPlayedTeam(team01RegisterNo,team02RegisterNo);

        for (int i=0;i<matchedPlayedTeam.length;i++){
            if (matchedPlayedTeam[i]!=null){
                if (matchedPlayedTeam[i+1]!=null){
                    //Get Current year
                    LocalDate maximumDate=LocalDate.now();
                    int localYear=maximumDate.getYear();

                    int day;
                    do {
                        day = validateUserIntInput("Enter match played day : ");      //Call validateUserInput() method
                    } while (day>30 || day==0);
                    int month;
                    do {
                        month = validateUserIntInput("Enter match played month : ");    //Call validateUserInput() method
                    } while (month>12 || month==0);
                    int year;
                    int count=0;
                    do {
                        if (count>0) {
                            System.out.println("Invalid Input!");
                            System.out.print("Re-Enter match played year : ");
                            count+=1;
                        }else {
                            System.out.print("Enter match played year : ");
                            count=count+1;
                        }
                        while (!USER_INPUT.hasNextInt()) {
                            System.out.println("Invalid Input!");
                            System.out.print("Re-Enter match played year : ");
                            USER_INPUT.next();
                        }
                        year = USER_INPUT.nextInt();
                    } while(year!=localYear);    //Set permission to add current year match details

                    //Get teams goal score and update team's goal score value
                    int team01Goal= validateUserIntInput("Enter team 01 received goals score : ");    //Call validateUserInput() method
                    SportsClub team01=matchedPlayedTeam[0];
                    if (team01  instanceof  FootballClub){
                        FootballClub footballClubTeam01=(FootballClub) team01;    //Type Casting
                        footballClubTeam01.setNoOfGoalsScored(team01Goal);       //Set team 01 goalsScore
                    }

                    int team02Goal= validateUserIntInput("Enter team 02 received goals score : ");     //Call validateUserInput() method
                    SportsClub team02=matchedPlayedTeam[1];
                    if (team02  instanceof  FootballClub){
                        FootballClub footballClubTeam02=(FootballClub) team02;      //Type Casting
                        footballClubTeam02.setNoOfGoalsScored(team02Goal);         //Set team 02 goalsScore
                    }

                    //Create match instance and add match details
                    Date matchPlayedDate = new Date(day,month,year);      //Create new Date and pass values

                    Match playedMatch= new Match(matchPlayedDate,matchedPlayedTeam);
                    //Set two teams goals score
                    playedMatch.setTeam01GoalScore(team01Goal);
                    playedMatch.setTeam02GoalScore(team02Goal);

                    //Update Football club's statistics
                    playedMatch.updateClubStatistics();
                    //Add played match details to the records
                    manager.addPlayedMatch(playedMatch);

                    break;
                }
            }else {
                System.out.println("Please recheck you entered club register number");
                System.out.println();
                break;
            }
        }
    }

    //Create a new method to validate user integer inputs
    private static int validateUserIntInput(String massage){
        int userInput;
        int iterationCount=0;
        do {
            if (iterationCount>0) {
                System.out.print("Re-"+massage);
                iterationCount+=1;
            }else {
                System.out.print(massage);
                //count=count+1;
                iterationCount+=1;
            }
            while (!USER_INPUT.hasNextInt()) {
                System.out.println("Invalid Input!");
                System.out.print(massage);
                USER_INPUT.next();
            }
            userInput = USER_INPUT.nextInt();     //Assign valid user input to the variable

        } while (userInput < 0);             //Check user entered number less than zero
        return userInput;
    }


}
