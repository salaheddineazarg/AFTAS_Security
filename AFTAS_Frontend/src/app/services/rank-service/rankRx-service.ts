import {Injectable} from "@angular/core";
import {Subject} from "rxjs";
import {RankModel} from "../../models/rank/rank-model";


@Injectable({
  providedIn: 'root'
})

export  class RankRxService{
  private rankSubject:Subject<RankModel[]> = new Subject<RankModel[]>();
  private rankOneSubject:Subject<RankModel> = new Subject<RankModel>();
  ranks$ = this.rankSubject.asObservable();
  rank$=this.rankOneSubject.asObservable();
  numMember!:number;


  getRanks(ranks:RankModel[]){
    this.rankSubject.next(ranks);
  }

  setOneRank(rank:RankModel){
    this.rankOneSubject.next(rank);
  }
}
