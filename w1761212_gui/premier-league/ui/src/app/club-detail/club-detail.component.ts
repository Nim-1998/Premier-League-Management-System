import { Component, OnInit } from '@angular/core';
import {PremierLeagueService} from "../premier-league.service";
import {IFootballClub} from "../footballClub";

@Component({
  selector: 'app-club-detail',
  templateUrl: './club-detail.component.html',
  styleUrls: ['./club-detail.component.css']
})
export class ClubDetailComponent implements OnInit {


  //To prevent getting type errors I used a syntax. To find that I preferred below referenced website
  /*Malik, S. (2020). Ask Typescript to ignore an error. Medium. Available from https://winsmarts.com/ask-typescript-to-ignore-an-error-ad724b5b33eb [Accessed 1 January 2021].*/

  //@ts-ignore
  public footballList : IFootballClub[] ;
  public errorMessage = "";
  constructor(private premierLeagueService : PremierLeagueService) { } //Create an instance of PremierLeagueService

  ngOnInit(): void {
    this.premierLeagueService.getFootballClubList().subscribe(data => this.footballList = data,
      //Handle Error message
      error => this.errorMessage = error
    );
  }

}
