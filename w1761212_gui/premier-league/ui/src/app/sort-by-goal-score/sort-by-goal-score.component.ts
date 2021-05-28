import { Component, OnInit } from '@angular/core';
import {PremierLeagueService} from "../premier-league.service";
import {IFootballClub} from "../footballClub";

@Component({
  selector: 'app-sort-by-goal-score',
  templateUrl: './sort-by-goal-score.component.html',
  styleUrls: ['./sort-by-goal-score.component.css']
})
export class SortByGoalScoreComponent implements OnInit {
  //Created an empty list

  //@ts-ignore
  public goalScoreList : IFootballClub[];
  public errorMessage = "";

  constructor(private premierLeagueService : PremierLeagueService) { } //Create an instance of PremierLeagueService

  ngOnInit(): void {
    this.premierLeagueService.getGoalScoreSortedList().subscribe(data => this.goalScoreList = data,
      //Handle Error message
      error => this.errorMessage = error

    );
  }
}
