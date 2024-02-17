import {CompetitionModel} from "../competition/competition-model";
import {MemberModel} from "../member-model";
import {FishModel} from "../fish/fish-model";

export interface HuntingModel{
  id:number,
  numberOfFish:number,
  competition:CompetitionModel,
  fish:FishModel
  member:MemberModel
}
