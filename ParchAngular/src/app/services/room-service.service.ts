import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Room } from '../models/Room';
import { User } from '../models/User';
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
getRoomUsers(roomID:number):Observable<User>{
  return this.http.get<User>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getRoomUsers.do?roomID=" + roomID);
}
getUserRooms(username:string):Observable<Room>{
  return this.http.get<Room>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getUserRooms.do?username=" + username);
}
createRoom(username:string,roomname:string):Observable<Room>{
  return this.http.post<Room>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/createRoom.do","username=" + username + "&roomname=" + roomname,this.httpOptions);
}
inviteUser(roomID:number,inviter:string,invitee:string):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/inviteUser.do","roomID=" + roomID + "&inviter=" + inviter + "&invitee" + invitee,this.httpOptions);
}
acceptInvite(roomID:number,username:string):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/acceptInvite.do","roomID=" + roomID + "&username=" + username,this.httpOptions);
}




}