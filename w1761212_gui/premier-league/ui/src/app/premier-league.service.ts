import { Injectable } from '@angular/core';
import {HttpClient , HttpErrorResponse} from "@angular/common/http";
import {IFootballClub} from "./footballClub";
import { Observable , throwError as ObservableThrowError } from 'rxjs';
import  {catchError} from 'rxjs/operators'; //Import catch operator
import {IMatchDetail} from "./matchDetail";


@Injectable({
  providedIn: 'root'
})
export class PremierLeagueService {

  //Api Base URL

  private baseUrl : string = "http://localhost:9000";

  constructor(private http : HttpClient) { }       //Create an instance of HttpClient

  //Make a request to get Football club list
  getFootballClubList() : Observable<IFootballClub[]>{
    return this.http.get<IFootballClub[]>(this.baseUrl+"/footballList").pipe(catchError(this.errorHandler));
  }

  //Make a request to get sorted FootballClub list according to Points
  getPointSortedList() : Observable<IFootballClub[]>{
    return this.http.get<IFootballClub[]>(this.baseUrl+"/footballList/sortedPoints").pipe(catchError(this.errorHandler));  //pass URL as an argument
  }

  //Make a request to get sorted FootballClub List according to Wins
  getWinSortedList() : Observable<IFootballClub[]>{
    return this.http.get<IFootballClub[]>(this.baseUrl+"/footballList/sortedWins").pipe(catchError(this.errorHandler));
  }

  //Make a request to get sorted FootballClub list according to the Goal score
  getGoalScoreSortedList() : Observable<IFootballClub[]>{
    return this.http.get<IFootballClub[]>(this.baseUrl+"/footballList/sortedGoalScore").pipe(catchError(this.errorHandler));
  }

  //Make a request to generate random Match
  getRandomMatch() : Observable<IMatchDetail> {
    return this.http.get<IMatchDetail>(this.baseUrl+"/randomMatch").pipe(catchError(this.errorHandler));
  }

  //Make a request to get entered date Match details
  getSearchMatchDetail(day:string,month:string,year:string): Observable<IMatchDetail[]>{
    return this.http.get<IMatchDetail[]>(this.baseUrl+"/searchDateMatchDetails?day="+day+"&month="+month+"&year="+year).pipe(catchError(this.errorHandler));
  }

  //Make a request to get sorted Match List according to Match played Date
  getSortedMatchList() : Observable<IMatchDetail[]> {
    return this.http.get<IMatchDetail[]>(this.baseUrl+"/sortedMatchList-byDate").pipe(catchError(this.errorHandler));
  }

  //Define errorHandler()
  errorHandler(error : HttpErrorResponse){
    return ObservableThrowError(error.message || "Server Error") //throw an exception message
  }
}
