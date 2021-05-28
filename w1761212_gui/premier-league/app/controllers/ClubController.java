package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.entities.Match;
import models.entities.SportsClub;
import models.entityLogics.DateComparator;
import models.entityLogics.GoalsScoreComparator;
import models.entityLogics.NoOfWinComparator;
import models.entityLogics.PointComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ClubService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClubController extends Controller {
    //Create a new logger
    private static final Logger logger = LoggerFactory.getLogger("controller");

    //Response method to send Sports Club details
    public Result getFootballList(){
        List<SportsClub> result = ClubService.getInstance().returnLoadedFootballList();
        if (result.isEmpty()){
            logger.info("getFootballList() : Football list is empty");
            return noContent();
        }else {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.convertValue(result, JsonNode.class);
            logger.info("getFootballList() : Football list successfully converted to json");
            return ok(jsonNode);
        }
    }

    //Response method to send sorted Football club list according to the points
    public Result listSortedPoint(){
        List<SportsClub> list = ClubService.getInstance().returnLoadedFootballList();
        if (list.isEmpty()){
            logger.info("listSortedPoint() : Football list is empty");
            return noContent();
        }else {

            Collections.sort(list,new PointComparator().reversed());
            logger.info("listSortedPoint() : Football list successfully sorted according to the points");
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.convertValue(list, JsonNode.class);
            logger.info("listSortedPoint() : Football list successfully converted to json");
            return ok(jsonNode);
        }
    }

    //Response method to send sorted Football club list according to the number of wins
    public Result listSortedWin(){
        List<SportsClub> list = ClubService.getInstance().returnLoadedFootballList();
        if (list.isEmpty()){
            logger.info("listSortedWin() : Football list is empty");
            return noContent();
        }else {
            Collections.sort(list,new NoOfWinComparator().reversed());
            logger.info("listSortedWin() : Football list successfully sorted according to the wins");
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.convertValue(list, JsonNode.class);
            logger.info("listSortedWin() : Football list successfully converted to json");
            return ok(jsonNode);
        }
    }

    //Response method to send sorted Football club list according to the number of goals score
    public Result listSortedGoalScore(){
        List<SportsClub> list = ClubService.getInstance().returnLoadedFootballList();
        if (list.isEmpty()){
            logger.info("controllers/CluController/listSortedGoalScore : Football list is empty");
            return noContent();
        }else {
            Collections.sort(list,new GoalsScoreComparator().reversed());
            logger.info("listSortedGoalScore() : Football list successfully sorted according to the goals score");
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.convertValue(list, JsonNode.class);
            logger.info("listSortedGoalScore() : Football list successfully converted to json");
            return ok(jsonNode);
        }
    }

    //Response method to send sorted Match list according to the Match played Date
    public Result sortedMatchList(){

        List<Match> matchList = ClubService.getInstance().returnLoadedMatchList();
        if (matchList.isEmpty()){
            logger.info("sortedMatchList() : Match list is empty");
            return noContent();
        }else {
            Collections.sort(matchList,new DateComparator());
            logger.info("sortedMatchList() : Match list successfully sorted according to the Match played date");
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.convertValue(matchList, JsonNode.class);
            logger.info("sortedMatchList() : Match list successfully converted to json");
            return ok(jsonNode);
        }
    }
    //Response method to send random generated Match
    public Result randomMatch(){
        Match match = ClubService.getInstance().generateRandomMatch();
        if (match == null){
            return noContent();
        }else {
            JsonNode jsonNode = Json.toJson(match);
            logger.info("randomMatch() : Generated Random Match successfully converted to json");
            return ok(jsonNode);
        }
    }
    //Response method to send search date played match details
    public Result selectedDateResult(String day ,String month, String year){
        List<Match> matchList = ClubService.getInstance().returnLoadedMatchList();
        if (matchList.isEmpty()){
            logger.info("selectedDateResult() : No Match details to display that selected day");
            return noContent();
        }else {
            List<Match> result = new ArrayList<>();    //ArrayList to store selected date match details
            int yearInt = Integer.parseInt(year);
            int monthInt = Integer.parseInt(month);
            int dayInt = Integer.parseInt(day);

            for (Match match : matchList) {
                if (yearInt == match.getMatchPlayedDate().getYear()) {
                    if (monthInt == match.getMatchPlayedDate().getMonth()) {
                        if (dayInt == match.getMatchPlayedDate().getDay()) {
                            result.add(match);
                        }
                    }

                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.convertValue(result, JsonNode.class);
            logger.info("controllers/CluController/selectedDateResult : Match list successfully converted to json");
            return ok(jsonNode);
        }
    }
}
