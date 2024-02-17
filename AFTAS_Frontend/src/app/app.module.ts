import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CompetitionComponent } from './components/competition-components/competition/competition.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { CompetitionFormComponent } from './components/competition-components/competition-form/competition-form.component';
import { CompetitionDetailsComponent } from './components/competition-components/competition-details/competition-details.component';
import { MemberComponent } from './components/member-components/member/member.component';
import { RankComponent } from './components/rank-components/rank/rank.component';
import {NgOptimizedImage} from "@angular/common";
import { MemberFormComponent } from './components/member-components/member-form/member-form.component';
import { HuntingFormComponent } from './components/hunting-components/hunting-form/hunting-form.component';

@NgModule({
  declarations: [
    AppComponent,
    CompetitionComponent,
    CompetitionFormComponent,
    CompetitionDetailsComponent,
    MemberComponent,
    RankComponent,
    MemberFormComponent,
    HuntingFormComponent,

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
        NgOptimizedImage,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
