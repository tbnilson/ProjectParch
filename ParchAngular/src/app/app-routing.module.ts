import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { BoardComponent } from './component/board/board.component';

const routes: Routes = [
  {path : 'login', component : LoginComponent},
  {path : 'board', component : BoardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
