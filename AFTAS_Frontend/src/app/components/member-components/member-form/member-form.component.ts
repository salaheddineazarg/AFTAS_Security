import {Component, EventEmitter, Output} from '@angular/core';
import {MemberService} from "../../../services/member-service/member.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MemberModel} from "../../../models/member-model";
import {IdentityDocType} from "../../../enum/identityDocType";
import {MemberRxService} from "../../../services/member-service/memberRx-service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-member-form',
  templateUrl: './member-form.component.html',
  styleUrls: ['./member-form.component.css']
})
export class MemberFormComponent  {

  countries:any[]=[];
  @Output() showMemberModal:EventEmitter<Function> = new EventEmitter<Function>();

  constructor(private service:MemberService,private serviceRx:MemberRxService) {
    service.getCounties().subscribe(
      countries => {
     this.countries = countries.map(country => ({
          name: country.name.common,
        }));
      }
    )
  }
  formMember = new FormGroup({
    firstName:new FormControl('',Validators.required),
    lastName:new FormControl('',Validators.required),
    accessionDate:new FormControl('',Validators.required),
    country:new FormControl('',Validators.required),
    identityDoc:new FormControl('',Validators.required),
    identityNumber:new FormControl('',Validators.required)
  })


  saveMember(){

    const newMember:MemberModel = {
      num:this.generateNum(),
      name:this.formMember.value.firstName,
      familyName:this.formMember.value.lastName,
      accessionDate: new Date(this.formMember.value.accessionDate),
      nationality:this.formMember.value.country,
      identityDocumentType: this.formMember.value.identityDoc as IdentityDocType,
      identityNumber:this.formMember.value.identityNumber
    }
     this.service.saveMember(newMember).subscribe(
      member =>{
       this.serviceRx.setOneMember(member)
        this.getFunctionMember()
        Swal.fire({
          position: "top-end",
          icon: "success",
          title: "The member is added",
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
           title: "The member didn't added",
           showConfirmButton: false,
           background:"red",
           iconColor:"black",
           toast:true,
           timer: 1500
         });
         console.error(error);
       });



  }



  generateNum(): number {
    const min = 1000000000000000;
    const max = 9007199254740991;
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  getFunctionMember(){
    this.showMemberModal.emit();
  }
}
