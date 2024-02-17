import {Injectable} from "@angular/core";
import {Subject} from "rxjs";
import {MemberModel} from "../../models/member-model";


@Injectable({
  providedIn: 'root'
})

export class MemberRxService{

  private memberSubject :Subject<MemberModel[]> = new Subject<MemberModel[]>();
  private memberOneSubject:Subject<MemberModel>= new Subject<MemberModel>()
  members$=this.memberSubject.asObservable();
  member$=this.memberOneSubject.asObservable()



  getMemmbers(members:MemberModel[]){
    this.memberSubject.next(members);
  }

  setOneMember(member:MemberModel){
    this.memberOneSubject.next(member);
  }
}
