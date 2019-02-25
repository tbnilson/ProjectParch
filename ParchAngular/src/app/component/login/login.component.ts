import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/User';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private logserv:LoginServiceService, private router : Router) { }

  ngOnInit() {
  }
  
  username:string;
  password:string;
  email:string;
  curuser:User;

 answer:string;
 
 
 
Login(){
  let verify:Observable<boolean> = this.logserv.login(this.username, this.password);
  verify.subscribe(

    (response)=>{
      console.log(response);
      if(response){
        this.answer = "Successful login"
        this.router.navigateByUrl("board?username=" + this.username + "&password=" + this.password);
      }
      else{
        this.answer = "Invalid login"
      }

    }
    ,

    (response)=>{
      console.log(response);
      this.answer = "Fail";

    }
  
  );
  
} ;
 newemail:string;
 newusername:string;
 newpassword:string; 

check:string;
Register(){
  
}; 

}

