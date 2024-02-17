import {Component, OnInit, ViewChild} from '@angular/core';
import {CompetitionModel} from "../../../models/competition/competition-model";
import {CompetitionService} from "../../../services/competition-service/competition.service";
import {ActivatedRoute} from "@angular/router";
import {switchMap} from "rxjs";
import {RankComponent} from "../../rank-components/rank/rank.component";
import {CompetitionRxService} from "../../../services/competition-service/competitionRx.service";
import {RankService} from "../../../services/rank-service/rank.service";
import {RankModel} from "../../../models/rank/rank-model";

@Component({
  selector: 'app-competition-details',
  templateUrl: './competition-details.component.html',
  styleUrls: ['./competition-details.component.css']
})
export class CompetitionDetailsComponent implements  OnInit{
  competition!:CompetitionModel;
  num!:number;
  private id:string ="";
  podium:RankModel[]=[];
  displayPodium=false;
  @ViewChild('RankComponent',{static:false}) rankComponent :RankComponent;

  constructor(private service:CompetitionService,private serviceRx:CompetitionRxService,private router:ActivatedRoute,private serviceRank:RankService) {
  }

  ngOnInit() {
    this.getOneCompetition();
  }


    getNum(num:any) {
      this.serviceRx.num = num;
      this.runFunctionSave();
     }


     runFunctionSave(){
      if(this.rankComponent){
          this.rankComponent.saveRank();
      }

     }




    getOneCompetition() {
    this.router.paramMap.pipe(
      switchMap(params => {
        this.id = params.get("code");
        return this.service.getCompetition(this.id);
      })
    ).subscribe(
      data => {
        this.competition = data;
        if(parseInt(data.date)<Date.now()){

        }
      }
    );
  }


  getPodium(code:string){
  this.serviceRank.getPodium(code).subscribe(
    data =>{
     this.podium = data;
     this.displayPodium=true;
    }
  )
  }





}
