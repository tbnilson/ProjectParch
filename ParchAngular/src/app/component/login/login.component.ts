import { Component, OnInit } from '@angular/core';
import { defineBase } from '@angular/core/src/render3';
import { checkAndUpdateElementInline } from '@angular/core/src/view/element';
import { User } from 'src/app/models/User'
import { checkBinding } from '@angular/core/src/view/util';
//import { userInfo } from 'os';


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

 answer:string;
  //User("admin@admin.com","admin","admin",)
 db: Array<User>=[(new User("admin","admin","admin"))];
 checkDB(uid){
   if(this.username==uid.username&&this.password==uid.password){
     this.answer="it works";
     return uid.username;
   }
   else
    return null;
 };
 x:User;
Login(){
  for(let i=0;i<this.db.length;i++){
    this.checkDB(this.db[i]);
  }
//this.db.forEach(this.checkDB(User));
} ;
 newemail:string;
 newusername:string;
 newpassword:string; 

check:string;
Register(){
  this.db.push(new User(this.newemail,this.newusername,this.newpassword,));
  this.check="registered"
  console.log(this.db);
}; 

}

