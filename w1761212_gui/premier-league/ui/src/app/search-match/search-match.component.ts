import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {PremierLeagueService} from "../premier-league.service";
import {IMatchDetail} from "../matchDetail";

@Component({
  selector: 'app-search-match',
  templateUrl: './search-match.component.html',
  styleUrls: ['./search-match.component.css']
})
export class SearchMatchComponent implements OnInit {

  public day : string ="";
  public month : string ="";
  public year : string ="";

  //@ts-ignore
  public selectedResultList : IMatchDetail[];
  public errorMessage = "";
  constructor(private premierLeagueService : PremierLeagueService) { } //Create an instance of PremierLeagueService

  ngOnInit(): void {
  }

  getDate(value1: string, value2: string, value3: string): void {
    if (value1.length==0 || value2.length==0 || value3.length==0) {
      alert("Please Enter Date")
    } else if (value1.match("^[a-zA-Z]+$") || value2.match("^[a-zA-Z]+$") || value3.match("^[a-zA-Z]+$")) {
      alert("Invalid Input recheck values")
  }else {
      console.log(value1);
      this.day=value1;
      console.log(value2);
      this.month=value2;
      console.log(value3);
      this.year=value3;
      this.premierLeagueService.getSearchMatchDetail(this.day,this.month,this.year).subscribe(data => this.selectedResultList = data,
        error => this.errorMessage=error);

    }
  }
}
