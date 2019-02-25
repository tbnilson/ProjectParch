import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/User';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { Observable } from 'rxjs';




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private logserv:LoginServiceService) { }

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
        this.answer = "Successful login";
      }
      else{
        this.answer = "Invalid login";
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
  let reg:Observable<boolean> = this.logserv.register(this.newusername, this.newpassword, this.newemail);
  reg.subscribe(

    (response)=>{
      console.log(response);
      if(response){
        this.answer = "You successfully registered";
      }
      else{
        this.answer = "Unsuccessful login";
      }
    }
    ,
    (response)=>{
      console.log(response);
      this.answer = "Fail";
    }
  );
  
}; 

}

