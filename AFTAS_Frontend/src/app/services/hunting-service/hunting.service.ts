import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HuntingRequestModel} from "../../models/hunting/huntingRequest-model";
import {HuntingModel} from "../../models/hunting/hunting-model";

@Injectable({
  providedIn: 'root'
})
export class HuntingService {
  baseUrl="http://localhost:8080/api/hunting"
  constructor(private http:HttpClient) { }



  save(hunt:HuntingRequestModel){
    return this.http.post<HuntingModel>(this.baseUrl,hunt);
  }
}
