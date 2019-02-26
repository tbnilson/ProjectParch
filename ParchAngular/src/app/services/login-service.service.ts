import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private http:HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/x-www-form-urlencoded',
      'X-Requested-With': 'XMLHttpRequest'
    })
  };


  getUser(username:string):Observable<User>{
    return this.http.get<User>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getUser.do?username=" + username);
  }

  login(username:string, password:string):Observable<boolean>{
    return this.http.get<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/login.do?username=" + username + "&password=" + password);
  }

  //http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/registerUser.do?username=testregs&password=testpass&email=asdad@test.com
  register(username:string, password:string, email:string):Observable<boolean>{
    return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/registerUser.do","username=" + username + "&password=" + password + "&email=" + email,this.httpOptions);
  }

}
