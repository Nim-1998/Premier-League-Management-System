import { Component, OnInit } from '@angular/core';
import {PremierLeagueService} from "../premier-league.service";
import {IFootballClub} from "../footballClub";

@Component({
  selector: 'app-sort-by-win',
  templateUrl: './sort-by-win.component.html',
  styleUrls: ['./sort-by-win.component.css']
})
export class SortByWinComponent implements OnInit {
  //Created an empty list

  //@ts-ignore
  public winSortedList : IFootballClub[] ;
  public errorMessage = "";
  constructor(private premierLeagueService : PremierLeagueService) { }//Create an instance of PremierLeagueService

  ngOnInit(): void {
    this.premierLeagueService.getWinSortedList().subscribe(data => this.winSortedList = data,
      //Handle Error message
      error => this.errorMessage = error

    );
  }
}
