import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { Board } from '../models/Board';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RoomServiceService {

  constructor(private http:HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/x-www-form-urlencoded',
      'X-Requested-With': 'XMLHttpRequest'
    })
}
//http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/.do?
getRoomUsers(roomID:number):Observable<Array<User>>{
  return this.http.get<Array<User>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getRoomUsers.do?roomID=" + roomID);
}
getUserRooms(username:string):Observable<Array<Board>>{
  return this.http.get<Array<Board>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getUserRooms.do?username=" + username);
}
createRoom(username:string,roomname:string):Observable<Board>{
  return this.http.post<Board>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/createRoom.do","username=" + username + "&roomname=" + roomname,this.httpOptions);
}
inviteUser(roomID:number,inviter:string,invitee:string):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/inviteUser.do","roomID=" + roomID + "&inviter=" + inviter + "&invitee" + invitee,this.httpOptions);
}
acceptInvite(roomID:number,username:string):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/acceptInvite.do","roomID=" + roomID + "&username=" + username,this.httpOptions);
}




}