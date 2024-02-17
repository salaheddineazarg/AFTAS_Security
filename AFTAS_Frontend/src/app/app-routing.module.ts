import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CompetitionComponent} from "./components/competition-components/competition/competition.component";
import {
  CompetitionDetailsComponent
} from "./components/competition-components/competition-details/competition-details.component";

const routes: Routes = [
  {path:'',component:CompetitionComponent},
  {path:'competition/:code',component:CompetitionDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
