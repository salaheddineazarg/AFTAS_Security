import {Injectable} from "@angular/core";
import {BehaviorSubject, Subject} from "rxjs";
import {CompetitionModel} from "../../models/competition/competition-model";
import {PaginationCompetitionModel} from "../../models/competition/pagination-competition-model";


@Injectable({
  providedIn: 'root'
})

export class CompetitionRxService {
  private competitionsSubject: Subject<CompetitionModel[]> = new Subject<CompetitionModel[]>();
  private competitionSubjectOne: Subject<CompetitionModel> = new Subject<CompetitionModel>();
  private  competitionSubjectPagination : Subject<PaginationCompetitionModel> = new Subject<PaginationCompetitionModel>();
  num:number=0
  competitions$ = this.competitionsSubject.asObservable();
  competition$ = this.competitionSubjectOne.asObservable();
  competitionPagination$ = this.competitionSubjectPagination.asObservable();



   getCompetition(competitions:CompetitionModel[]){
     this.competitionsSubject.next(competitions);
   }


   getCompetitionPagination(competitionPagination:PaginationCompetitionModel){
     this.competitionSubjectPagination.next(competitionPagination);
   }

  setOneCompetition(competition:CompetitionModel){
    this.competitionSubjectOne.next(competition);
  }


}
