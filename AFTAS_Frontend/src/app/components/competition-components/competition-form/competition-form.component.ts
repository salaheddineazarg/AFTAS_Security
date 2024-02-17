import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CompetitionService} from "../../../services/competition-service/competition.service";
import {CompetitionModel} from "../../../models/competition/competition-model";
import {CompetitionRxService} from "../../../services/competition-service/competitionRx.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-competition-form',
  templateUrl: './competition-form.component.html',
  styleUrls: ['./competition-form.component.css']
})
export class CompetitionFormComponent implements OnInit{

  @Output() showModal:EventEmitter<Function> = new EventEmitter<Function>();

  constructor(private service:CompetitionService,private serviceRx:CompetitionRxService) {}

  form = new FormGroup({
          date:new FormControl('',Validators.required),
          startTime:new FormControl('',Validators.required),
          endTime:new FormControl('',Validators.required),
          numberOfParticipants:new FormControl(0,Validators.required),
         location:new FormControl('',Validators.required),
         amount:new FormControl(0,Validators.required)
  })

  ngOnInit() {
  }

  saveCompetition(){

    const newCompetition : CompetitionModel = {
      code:this.generateCompetitionCode(this.form.value.location ?? ''),
      date:this.form.value.date ?? '',
      startTime:this.form.value.startTime ?? '',
      endTime:this.form.value.endTime ?? '',
      numberOfParticipants:this.form.value.numberOfParticipants ?? 0,
      location:this.form.value.location ?? '',
      amount:this.form.value.amount ?? 0
    }
    this.service.saveCompetition(newCompetition).subscribe(response => {
      this.serviceRx.setOneCompetition(response)

      Swal.fire({
        position: "top-end",
        icon: "success",
        title: "Your work has been saved",
        showConfirmButton: false,
        background:"#07f104",
        iconColor:"black",
        toast:true,
        timer: 2500
      });
    });
    this.getFuntion()
  }



  getFuntion(){
    this.showModal.emit();
  }
generateCompetitionCode(location:string) {
    const pattern = location.toUpperCase().substring(0, 3);
    const currentDate = new Date().toLocaleDateString('en-GB', { month: '2-digit', day: '2-digit',year: '2-digit' }).replace(/\//g, '');
    const currentDateReversed = currentDate.split('').join('');
    return `${pattern}-${currentDateReversed}`;
  }


    getToday(): string {
        const today = new Date();
        const year = today.getFullYear();
        const month = (today.getMonth() + 1).toString().padStart(2, '0'); // Month starts from 0
        const day = today.getDate().toString().padStart(2, '0');
        return `${year}-${month}-${day}`;
    }


}
