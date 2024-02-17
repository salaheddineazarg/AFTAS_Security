import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {HuntingService} from "../../../services/hunting-service/hunting.service";
import {FishService} from "../../../services/fish-service/fish.service";
import {FishModel} from "../../../models/fish/fish-model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {HuntingRequestModel} from "../../../models/hunting/huntingRequest-model";
import {switchMap} from "rxjs";
import Swal from "sweetalert2";
import {RankRxService} from "../../../services/rank-service/rankRx-service";

@Component({
  selector: 'app-hunting-form',
  templateUrl: './hunting-form.component.html',
  styleUrls: ['./hunting-form.component.css']
})
export class HuntingFormComponent implements OnInit{
  fishs:FishModel[]=[];
  code!:string;
  @Output() close:EventEmitter<Function>=new EventEmitter<Function>();
  @Input() memberNum!:number;

  constructor(private service:HuntingService,private serviceFish:FishService,private serviceRankRx:RankRxService,private router:ActivatedRoute) {
   this.router.paramMap.subscribe(value =>{
     this.code = value.get("code");
    })
  }


  ngOnInit() {
    this.getFish()

  }

  getFish(){
    this.serviceFish.getFish().subscribe(
      data =>{
        this.fishs=data;
      }
    )
  }

  formHunt = new FormGroup({
    fish:new FormControl("",Validators.required),
    numberOfFish:new FormControl(0,Validators.required)
  })


  saveHunt(){

    const newHunt:HuntingRequestModel = {
      fish_name:this.formHunt.value.fish,
      numberOfFish:this.formHunt.value.numberOfFish,
      competition_code:this.code,
      member_num:this.serviceRankRx.numMember
    }
    console.log(newHunt);
    this.service.save(newHunt).subscribe(
      data => {
        console.log(data);
        this.getModalFunction()
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
        Swal.fire({
          position: "top-end",
          icon: "error",
          title: "The Rank didn't work",
          showConfirmButton: false,
          background:"red",
          iconColor:"black",
          toast:true,
          timer: 1500
        });
      }
    )

  }

  getModalFunction(){
    this.close.emit();
  }

}
