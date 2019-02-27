import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/User';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { UsernameService } from 'src/app/services/username.service';
import {MatSnackBar} from '@angular/material';
import { ParchSnackbarComponent } from '../parch-snackbar/parch-snackbar.component';




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private logserv: LoginServiceService, private router: Router, private usern: UsernameService, private snackBar: MatSnackBar ) {
    console.log("here");
  }

  ngOnInit() {
    this.usern.currentUsername.subscribe(user => this.username = user)
  }

  username: string;
  password: string;
  email: string;
  curuser: User;

  answer: string;

  setUsername() {
    this.usern.changeUsername(this.username);
  }


  Login() {
    let verify: Observable<boolean> = this.logserv.login(this.username, this.password);
    verify.subscribe(

      (response) => {
        console.log(response);
        if (response) {
          this.setUsername();
          this.answer = "Successful login"
          this.router.navigateByUrl("board");
        }
        else {
          this.answer = "Invalid login";
        }

      }
      ,

      (response) => {
        console.log(response);
        this.answer = "Fail";

      }

    );

  };
  newemail: string;
  newusername: string;
  newpassword: string;

  check: string;
  Register() {
    let reg: Observable<boolean> = this.logserv.register(this.newusername, this.newpassword, this.newemail);
    reg.subscribe(

      (response) => {
        console.log(response);
        if (response) {
          this.answer = "You successfully registered";
          this.snackBar.openFromComponent( ParchSnackbarComponent, {
            duration: 3000,
            verticalPosition: 'top',
            data: {message: "You successfully registered your account!"}
          })
        }
        else {
          this.answer = "Unsuccessful login";
        }
      }
      ,
      (response) => {
        console.log(response);
        this.answer = "Fail";
      }
    );

  };

}

