import { Component, OnInit } from '@angular/core';
import {PremierLeagueService} from "../premier-league.service";
import {IMatchDetail} from "../matchDetail";
import {IFootballClub} from "../footballClub";

@Component({
  selector: 'app-random-match',
  templateUrl: './random-match.component.html',
  styleUrls: ['./random-match.component.css']
})
export class RandomMatchComponent implements OnInit {
  //@ts-ignore
  public randomGeneratedMatch : IMatchDetail ;

  //@ts-ignore
  public footballList : IFootballClub[]  ;  //Created an empty list

  public errorMessage = "";
  public isNotMatchCreated : boolean = true;

  constructor(private premierLeagueService : PremierLeagueService) { }  //Create an instance of PremierLeagueService

  ngOnInit(): void {

  }
  viewUpdateTable() : void{
    this.premierLeagueService.getFootballClubList().subscribe(data => this.footballList =data ,
      error => this.errorMessage = error);
  }
  randomMatch(): void{

    this.isNotMatchCreated = false;
    this.premierLeagueService.getRandomMatch().subscribe(data => this.randomGeneratedMatch = data,
      //Handle Error message
      error => this.errorMessage = error
    );
  }
}
