import {IFootballClub} from "./footballClub";
import {IDate} from "./date";

export interface IMatchDetail {
  matchPlayedDate : IDate,
  matchPlayedClub : IFootballClub[],
  team01GoalScore : number,
  team02GoalScore : number
}
