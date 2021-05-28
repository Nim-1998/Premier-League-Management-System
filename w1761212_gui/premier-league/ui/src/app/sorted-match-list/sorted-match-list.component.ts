import { Component, OnInit } from '@angular/core';
import {PremierLeagueService} from "../premier-league.service";
import {IMatchDetail} from "../matchDetail";

@Component({
  selector: 'app-sorted-match-list',
  templateUrl: './sorted-match-list.component.html',
  styleUrls: ['./sorted-match-list.component.css']
})
export class SortedMatchListComponent implements OnInit {

  //Created an empty list

  //@ts-ignore
  public sortedMatchList : IMatchDetail[];
  public errorMessage = "";

  constructor(private premierLeagueService : PremierLeagueService) { }//Create an instance of PremierLeagueService

  ngOnInit(): void {
    this.premierLeagueService.getSortedMatchList().subscribe(data => this.sortedMatchList = data,
      error => this.errorMessage = error);
  }
}
