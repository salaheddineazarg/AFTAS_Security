import {Component, Input, OnInit} from '@angular/core';
import {RankRxService} from "../../../services/rank-service/rankRx-service";
import {RankService} from "../../../services/rank-service/rank.service";
import {ActivatedRoute} from "@angular/router";
import {switchMap} from "rxjs";
import {MemberModel} from "../../../models/member-model";
import {RankModel} from "../../../models/rank/rank-model";
import {RankRequestModel} from "../../../models/rank/rankRequest-model";
import {CompetitionRxService} from "../../../services/competition-service/competitionRx.service";
import Swal from "sweetalert2";
import {CompetitionService} from "../../../services/competition-service/competition.service";

@Component({
  selector: 'app-rank',
  templateUrl: './rank.component.html',
  styleUrls: ['./rank.component.css']
})
export class RankComponent implements OnInit{

  members:MemberModel[];
  code:string="";
  colors:string[]=[];
  huntModal=false;
  numberOfParticipants:number=0 ;

  constructor(private serviceRx:RankRxService,private service:RankService,private router:ActivatedRoute,private serviceCompetitionRx:CompetitionRxService,private serviceCompetition:CompetitionService) {
    this.serviceRx.ranks$.subscribe(
      data =>{
        this.members = data.map(item => item.member)

      }
    )
   this.serviceRx.rank$.subscribe(
     rank => {
       this.members.push(rank.member)
     }
   )
  }



  getRanksMember(){
    this.router.paramMap.pipe(
      switchMap(params => {
        this.code = params.get("code");
        return this.service.getRanks(this.code)
      })
    ).subscribe(
      (data)=>{
         this.serviceRx.getRanks(data);
      }
    )
  }


  showHuntModal(num:number){
    this.huntModal=!this.huntModal;
    this.serviceRx.numMember = num;
  }



    generateRandomColor(){
        const letters = '0123456789ABCDEF';
        for (let j = 0; j <8; j++) {
            let color = '#';
            for (let i = 0; i < 3; i++) {
                const darkValue = Math.floor(Math.random() * 13);
                color += letters[darkValue];
            }
            this.colors.push(color);
        }
    }



  saveRank(){
    const rank:RankRequestModel ={
      competition_code:this.code,
      member_num:this.serviceCompetitionRx.num
    }

    this.serviceCompetition.getCompetition(this.code).subscribe(
      competition=>{

       if (competition.numberOfParticipants > this.members.length){
         this.service.saveRank(rank).subscribe(
           data =>{
             this.serviceRx.setOneRank(data);
             Swal.fire({
               position: "top-end",
               icon: "success",
               title: "The Rank id Added",
               showConfirmButton: false,
               background:"#07f104",
               iconColor:"black",
               toast:true,
               timer: 1500
             });
           },error => {
             console.error(error);
           }
         )
       }else {
         Swal.fire({
           position: "top-end",
           icon: "error",
           title: "You Cant Add rank",
           showConfirmButton: false,
           background:"red",
           iconColor:"black",
           toast:true,
           timer: 1500
         });
       }

      }
    )

  }



  deleteRank(num:number){
      Swal.fire({
          position: "top-end",
          icon: "info",
          title: "Do you want to delete it",
          color:"white",
          showConfirmButton: true,
          confirmButtonText:"Delete",
          confirmButtonColor:"red",
          denyButtonText:"Cancel",
          denyButtonColor:"black",
          showCancelButton:true,
          background:"black",
          toast:true,
      }).then((result) => {
          if (result.isConfirmed) {
              this.service.deleteRank(this.code,num).subscribe(
                response =>{
                  this.getRanksMember()
                    Swal.fire({
                        position: "top-end",
                        icon: "success",
                        title: response,
                        showConfirmButton: false,
                        background:"#07f104",
                        iconColor:"black",
                        toast:true,
                        timer: 2500
                    });
                },error => {
                      console.log(error);
                      Swal.fire({
                          position: "top-end",
                          icon: "error",
                          title: "The Rank didn't deleted",
                          showConfirmButton: false,
                          toast:true,
                          background:"red",
                          timer: 2500
                      });
                }
              )
          }
      })
  }


  ngOnInit() {
    this.getRanksMember();
    this.generateRandomColor();
  }

}
