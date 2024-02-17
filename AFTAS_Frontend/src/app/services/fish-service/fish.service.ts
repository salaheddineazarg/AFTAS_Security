import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FishModel} from "../../models/fish/fish-model";

@Injectable({
  providedIn: 'root'
})
export class FishService {
    baseUrl="http://localhost:8080/api/fish"
  constructor(private http:HttpClient) { }


  getFish(){
      return this.http.get<FishModel[]>(this.baseUrl);
  }
}
