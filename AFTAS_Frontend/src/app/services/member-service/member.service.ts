import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MemberModel} from "../../models/member-model";
import {MemberRxService} from "./memberRx-service";

@Injectable({
  providedIn: 'root'
})
export class MemberService {
  private baseUrl="http://localhost:8080/api/member"
  constructor(private  http :HttpClient,private memberServiceRx :MemberRxService) { }


     getMembers() {
     return this.http.get<MemberModel[]>(this.baseUrl).subscribe(
       data =>{
        this.memberServiceRx.getMemmbers(data);
       }
     )

     }


     searchMember(name:string){
      return this.http.get<MemberModel[]>(this.baseUrl+`/search?keyword=${name}`);
     }


     getConutry(country:string){
     return  this.http.get<any>(`https://restcountries.com/v3.1/name/${country}?fullText=true`)
     }

     getCounties(){
      return this.http.get<any>("https://restcountries.com/v3.1/all");
     }

     saveMember(member:MemberModel){
      return this.http.post<MemberModel>(this.baseUrl,member);
     }

}
