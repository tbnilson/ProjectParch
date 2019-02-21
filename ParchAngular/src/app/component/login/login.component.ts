import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  
  username:string;
  password:string;
  email:string;
  testusername:string="admin";
  testpassword:string="admin";
  User={
    username:this.username,
    password:this.password,
    email:this.password
    }
 answer:string;
Login(){
if(this.username==this.testusername&&this.password==this.testpassword){
  this.answer="it works";
}
else
this.answer="incorrect";
}
// Registration(){
//   this.User.username=this.newusername;
// this.User.password=this.newpassword;
//}

}
