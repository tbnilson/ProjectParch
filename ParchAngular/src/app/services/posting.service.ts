import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/Post';

@Injectable({
  providedIn: 'root'
})
export class PostingService {

  constructor(private http:HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/x-www-form-urlencoded',
      'X-Requested-With': 'XMLHttpRequest'
    })
  };

  getRecentMessages(roomID:number, start:number, numposts:number):Observable<Array<Post>>{
    return this.http.get<Array<Post>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getMessagesBefore.do?" + 
    "roomID=" + roomID +
    "")
  }

}
