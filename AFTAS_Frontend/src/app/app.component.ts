import {Component, HostListener, OnInit} from '@angular/core';
import {CompetitionRxService} from "./services/competition-service/competitionRx.service";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {filter, switchMap} from "rxjs";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'AFTAS';
  modal=false;
  memberModal=false;
  display!:boolean;
  buttonDisplay=false;



    constructor(private router:Router) {

    }


  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.handleRouteChange(event.urlAfterRedirects);
      }
    });
  }

  handleRouteChange(url: string) {
    this.buttonDisplay = url === '/';
  }


  showModal(){
    this.modal =! this.modal;
  }

  showMemberModal(){
      this.memberModal =!this.memberModal;
  }


  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): boolean {
    const element = event.target as HTMLElement;
    const scrollTop = element.scrollTop;
    const visibleHeight = element.clientHeight;
    const scrollHeight = element.scrollHeight;
    const bottomOfScroll = scrollTop + visibleHeight >= scrollHeight - 100;
    if (bottomOfScroll) {

      return this.display = true;
    }else {
      return this.display = false;
    }

  }

}
