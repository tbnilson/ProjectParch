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

  //A json string representing the newly created Post object, or an error message.
  postMessage(username:string, roomID:number, message:string):Observable<Post>{
    return this.http.post<Post>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/postMessage.do",
    "username="+username+
    "&roomID="+roomID+
    "&message="+message,
    this.httpOptions);
  }
  //a json array representing all posts created after postID in the same room, or an error message otherwise.
  getNewMessages(postID:number):Observable<Array<Post>>{
    return this.http.get<Array<Post>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getNewMessages.do?postID="+postID);
  }

  //a json array of posts in the specified room. start specifies the number of the first post to be displayed, and num is the number of posts in the array
  getMessagesBefore(start:number,num:number,roomID:number):Observable<Array<Post>>{
    return this.http.get<Array<Post>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getMessagesBefore.do?"+
    "start="+start+
    "&num="+num+
    "&roomID="+roomID);
  }

  //"true" if the post was successfully edited, "false" otherwise. Can only edit a post if you are the user who made the post, or have admin permissions in the room.
  editMessage(postID:number, editor:string, newmessage:string):Observable<boolean>{
    return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/editMessage.do",
    "postID=" + postID + 
    "&username=" + editor + 
    "&message=" + newmessage,
    this.httpOptions);
  }

  //"true" if the post was successfully deleted, "false" otherwise. Can only delete a post if you are the user who made the post, or have admin permissions in the room.
  deleteMessage(postID:number, editor:string):Observable<boolean>{
    return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/deleteMessage.do",
    "postID=" + postID + 
    "&username=" + editor,
    this.httpOptions);
  }

}
