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

  //gets an array of the lates posts in a room, starting at "start" (which is zero indexed at the most recent post)
  getRecentMessages(roomID:number, start:number, numposts:number):Observable<Array<Post>>{
    return this.http.get<Array<Post>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getMessagesBefore.do?" + 
    "roomID=" + roomID +
    "&start=" + start + 
    "&num=" + numposts);
  }

  editMessage(postID:number, editor:string, newmessage:string):Observable<boolean>{
    return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/editMessage.do",
    "postID=" + postID + 
    "&username=" + editor + 
    "&message=" + newmessage,
    this.httpOptions);
  }

  deleteMessage(postID:number, editor:string):Observable<boolean>{
    return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/deleteMessage.do",
    "postID=" + postID + 
    "&username=" + editor,
    this.httpOptions);
  }

}
