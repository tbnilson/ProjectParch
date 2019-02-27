import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { LoginServiceService } from './services/login-service.service';
import { PostingService } from './services/posting.service';
import { UsernameService } from './services/username.service';
import { BoardComponent } from './component/board/board.component';
import { ParchSnackbarComponent } from './component/parch-snackbar/parch-snackbar.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    BoardComponent,
    ParchSnackbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [LoginServiceService, UsernameService, PostingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
