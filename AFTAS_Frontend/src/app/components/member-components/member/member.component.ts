import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MemberService} from "../../../services/member-service/member.service";
import {MemberRxService} from "../../../services/member-service/memberRx-service";
import {MemberModel} from "../../../models/member-model";
import {RankRxService} from "../../../services/rank-service/rankRx-service";
import {RankModel} from "../../../models/rank/rank-model";

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit{
  @Output() num:EventEmitter<number> = new EventEmitter<number>();
  @Output() saveRankFromMember:EventEmitter<Function> =new EventEmitter<Function>();
  members:MemberModel[]=[];
  ranks:number[]=[];
  colors:string[]=[];
  imageUrl=""


  constructor(private service:MemberService,private serviceRX:MemberRxService,private rankServiceRx:RankRxService) {
    this.serviceRX.member$.subscribe(
      member =>{
        this.members.push(member);
      }
    )
  }



  getRank(){
    this.rankServiceRx.ranks$.subscribe(
      data => {
        this.ranks =data.map(data => data.member.num);

      }
    )
  }

  sendNum(num:any){
    this.saveRankFromMember.emit()
    this.num.emit(num);

  }


  getMembers(){
    this.serviceRX.members$.subscribe(
      data => {
        this.members = data;
        this.members = this.members.filter(member => !this.ranks.includes(member.num));
        console.log(this.members);
      }
    );
  }





  searchMember(event:Event){
    const value = (event.target as HTMLSelectElement).value
    this.service.searchMember(value).subscribe(
        data =>{
          this.serviceRX.getMemmbers(data)
        }
    )
  }
  getCountry(country: string){
    console.log(country)
    this.service.getConutry(country).subscribe(
      data => {
        this.imageUrl = data[0].flags.png
        console.log(this.imageUrl)
      },
      error => {
        console.error("Error fetching country data:", error);
      }
    );
    console.log("image",this.imageUrl);
    return this.imageUrl;
  }


  generateRandomColor() {
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

  ngOnInit() {
    this.getRank()
    this.service.getMembers()
    this.getMembers();
    this.generateRandomColor()

  }
}
