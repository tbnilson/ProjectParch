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
import { MatSnackBarModule } from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { SanitizeStrPipe } from './pipes/sanitize-str.pipe';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    BoardComponent,
    ParchSnackbarComponent,
    SanitizeStrPipe
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule
  ],
  providers: [LoginServiceService, UsernameService, PostingService],
  bootstrap: [AppComponent],
  entryComponents: [ParchSnackbarComponent]
})
export class AppModule { }
