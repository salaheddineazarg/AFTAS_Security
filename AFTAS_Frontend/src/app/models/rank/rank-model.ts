import {CompetitionModel} from "../competition/competition-model";
import {MemberModel} from "../member-model";

export interface RankModel{
  competition:CompetitionModel,
  member:MemberModel,
  score?:number,
  rank?:number
}
