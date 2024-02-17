import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RankModel} from "../../models/rank/rank-model";
import {RankRxService} from "./rankRx-service";
import {RankRequestModel} from "../../models/rank/rankRequest-model";

@Injectable({
  providedIn: 'root'
})
export class RankService {

 private baseUrl="http://localhost:8080/api/ranking"
  constructor(private http:HttpClient,private RankServiceRx:RankRxService) { }

  getRanks(code:string){
   return this.http.get<RankModel[]>(this.baseUrl+`/competition?code=${code}`);
  }


  saveRank(rank:RankRequestModel){
   return this.http.post<RankModel>(this.baseUrl,rank);
  }

  deleteRank(code:string,num:number){
     return this.http.delete(this.baseUrl+`?code=${code}&num=${num}`,{responseType:"text"});
  }


  getPodium(code:string){
   return this.http.get<RankModel[]>(this.baseUrl+`/podium/${code}`)
  }

}
