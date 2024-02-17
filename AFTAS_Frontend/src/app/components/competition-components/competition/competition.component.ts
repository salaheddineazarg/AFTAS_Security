import {Component, Input, OnInit} from '@angular/core';
import {CompetitionService} from "../../../services/competition-service/competition.service";
import {CompetitionModel} from "../../../models/competition/competition-model";
import Swal from 'sweetalert2'
import {CompetitionRxService} from "../../../services/competition-service/competitionRx.service";


@Component({
  selector: 'app-competition',
  templateUrl: './competition.component.html',
  styleUrls: ['./competition.component.css']
})
export class CompetitionComponent implements OnInit{

  @Input() showPagination:boolean=false
  competitions!:CompetitionModel[];
  pages:number[]=[];
  colors:string[]=[];

  constructor(private service :CompetitionService,private  serviceRx :CompetitionRxService) {
    this.generateRandomColor()
    this.serviceRx.competition$.subscribe(response => {
      if (this.competitions.length < 4){
        this.competitions.push(response);
      }

    });
  }




  getCompetitions(){
    this.serviceRx.competitions$.subscribe(response => {
      this.competitions = response;
    });
  }

  getPagination(){
   return  this.serviceRx.competitionPagination$.subscribe(
        data =>{
          for ( let i=0 ; i<data.totalPages;i++){
            this.pages.push(1+i);
          }
        }
    )
  }


  deleteCompetition(code: string, i: number) {
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

    this.service.deleteCompetition(code).subscribe(

      response => {
        this.competitions = this.competitions.filter((competition, index) => index !== i);
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
          title: "The Competition didn't deleted",
          showConfirmButton: false,
          toast:true,
          background:"red",
          timer: 2500
        });
      }
    )
      } else if (result.isDenied) {
        Swal.fire("Changes are not saved", "", "info");
      }
    });

  }

  generateRandomColor() {
    const letters = '0123456789ABCDEF';
    for (let j = 0; j < 5; j++) {
      let color = '#';
      for (let i = 0; i < 3; i++) {
        const darkValue = Math.floor(Math.random() * 8); // Use 16 as it's hex
        color += letters[darkValue];
      }
      this.colors.push(color);
    }
  }

  isDateComparison(dateString: string, comparisonOperator: string): boolean {
    const competitionDate = new Date(dateString).getDate();
    console.log(competitionDate)
    const currentDate = new Date().getDate();

    if (comparisonOperator === '<') {
      return competitionDate < currentDate;
    } else if (comparisonOperator === '>') {
      return competitionDate> currentDate;
    } else if (comparisonOperator === '=') {
      return competitionDate === currentDate;
    }

    return false;
  }




  ngOnInit() {
    this.service.getCompetitions(0);
    this.getCompetitions();
    this.getPagination()
  }



}
