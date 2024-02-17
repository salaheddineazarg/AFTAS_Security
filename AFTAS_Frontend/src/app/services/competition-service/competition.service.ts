import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {CompetitionModel} from "../../models/competition/competition-model";
import {Observable, Subject} from "rxjs";
import {PaginationCompetitionModel} from "../../models/competition/pagination-competition-model";
import {CompetitionRxService} from "./competitionRx.service";


@Injectable({
  providedIn: 'root'
})


export class CompetitionService {

  private baseUrl: string = 'http://localhost:8080/api/competition';


  constructor(private http: HttpClient,private serviceRx:CompetitionRxService) {

  }

  getCompetitions(page:number) {
    if (!page){
      page = 0;
    }
   return  this.http.get<PaginationCompetitionModel>(this.baseUrl+`?size=4&page=${page}`).subscribe(
     (data)=>{
       this.serviceRx.getCompetition(data.content);
       this.serviceRx.getCompetitionPagination(data);
     }
   );
  }


   saveCompetition(competition:CompetitionModel){
    return this.http.post<CompetitionModel>(this.baseUrl,competition);
   }

  deleteCompetition(code: string): Observable<string> {
    return this.http.delete(`${this.baseUrl}/${code}`,{ responseType: 'text' });
  }

  getCompetition(code:string){
      return this.http.get<CompetitionModel>(`${this.baseUrl}/${code}`)
  }



}
