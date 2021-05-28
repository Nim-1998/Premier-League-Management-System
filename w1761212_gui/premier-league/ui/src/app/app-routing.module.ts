import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomePageComponent} from "./home-page/home-page.component";
import {SortByPointComponent} from "./sort-by-point/sort-by-point.component";
import {RandomMatchComponent} from "./random-match/random-match.component";
import {SearchMatchComponent} from "./search-match/search-match.component";
import {SortedMatchListComponent} from "./sorted-match-list/sorted-match-list.component";
import {ClubDetailComponent} from "./club-detail/club-detail.component";
import {SortByWinComponent} from "./sort-by-win/sort-by-win.component";
import {SortByGoalScoreComponent} from "./sort-by-goal-score/sort-by-goal-score.component";

const routes: Routes = [
  {path : '' , redirectTo : '/home-page' , pathMatch : 'full'},
  {path : 'home-page' , component : HomePageComponent},
  {path : 'club-detail' , component : ClubDetailComponent},
  {path : 'sort-by-point' , component : SortByPointComponent},
  {path : 'sort-by-win' , component : SortByWinComponent},
  {path : 'sort-by-goal-score' , component : SortByGoalScoreComponent},
  {path : 'random-match' , component :  RandomMatchComponent},
  {path : 'search-match' , component : SearchMatchComponent},
  {path : 'sorted-match-list' , component : SortedMatchListComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponent = [HomePageComponent,SortByPointComponent,RandomMatchComponent,
  SearchMatchComponent,SortedMatchListComponent,ClubDetailComponent,SortByWinComponent,SortByGoalScoreComponent]

