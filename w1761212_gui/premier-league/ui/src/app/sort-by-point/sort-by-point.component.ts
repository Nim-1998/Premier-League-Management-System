import { Component, OnInit } from '@angular/core';
import {PremierLeagueService} from "../premier-league.service";
import {IFootballClub} from "../footballClub";

@Component({
  selector: 'app-sort-by-point',
  templateUrl: './sort-by-point.component.html',
  styleUrls: ['./sort-by-point.component.css']
})
export class SortByPointComponent implements OnInit {
  //Created an empty list

  //@ts-ignore
  public sortedPointList : IFootballClub[];
  public errorMessage = "";

  constructor(private premierLeagueService : PremierLeagueService) { } //Create an instance of PremierLeagueService

  ngOnInit(): void {
    this.premierLeagueService.getPointSortedList().subscribe(data => this.sortedPointList = data,
      //Handle Error message
      error => this.errorMessage = error

      );
  }
}
