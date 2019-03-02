import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { Board } from '../models/Board';
import { Permission } from '../models/Permission'
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

//A json array representing all users in the database.
getAllUsers():Observable<Array<User>>{
  return this.http.get<Array<User>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getAllUsers.do");
}

//A json array of all users that are admins, moderators, or users of a room.
getRoomUsers(roomID:number):Observable<Array<User>>{
  return this.http.get<Array<User>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getRoomUsers.do?roomID=" + roomID);
}

//A json array of all rooms where the specified user is an admin, moderator, or user.
getUserRooms(username:string):Observable<Array<Board>>{
  return this.http.get<Array<Board>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getUserRooms.do?username=" + username);
}

//A json string representing the newly created room, or "false" otherwise.
createRoom(username:string,roomname:string):Observable<Board>{
  return this.http.post<Board>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/createRoom.do",
  "username="+username+
  "&roomname="+roomname,
  this.httpOptions);
}

//A json array of Parameter objects for the room.
getRoomPerms(roomID:number):Observable<Array<Permission>>{
  return this.http.get<Array<Permission>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getRoomPerms.do?roomID=" + roomID)
}

//"true" if the invitee was successfully given "invited" permissions to roomID, "false otherwise. Will return "false" if invitee already has permissions in roomID.
inviteUser(roomID:number,inviter:string,invitee:string):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/inviteUser.do",
  "roomID="+roomID+
  "&inviter="+inviter+
  "&invitee="+invitee,
  this.httpOptions);
}

//"true" if the user's permissions in roomID were successfully changed to "user", "false" otherwise.
acceptInvite(roomID:number,username:string):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/acceptInvite.do",
  "roomID="+roomID+
  "&username="+username,
  this.httpOptions);
}

// "true" if the user's invitation was successfuly rejected, "false" otherwise.
rejectInvite(roomID:number,username:string):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/rejectInvite.do",
  "roomID="+roomID+
  "&username="+username,
  this.httpOptions);
}

// "true" if the user was successfully made into a mod, "false" otherwise.
makeModerator(roomID:number,admin:string,user:string):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/makeModerator.do",
  "roomID="+roomID+
  "&admin="+admin+
  "&user="+user,
  this.httpOptions);
}

//a list of Permission object for the specified user where their permissions are set as "invited". Can return an empty list.
getInvites(username:string):Observable<Array<Permission>>{
  return this.http.get<Array<Permission>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getInvites.do?username="+username);
}

//"true" if the room was successfully deleted, "false" otherwise.
deleteRoom(admin:string,roomID:number):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/deleteRoom.do",
  "admin="+admin+
  "&roomID="+roomID,
  this.httpOptions);
}

//"true" if the user was successfully banned, "false" otherwise.
banUser(roomID:number,admin:string,banneduser:string):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/banUser.do",
  "roomID="+roomID+
  "&admin="+admin+
  "&banneduser="+banneduser,
  this.httpOptions);
}

//returns a list of User objects which have been banned from the specified room.
getBannedUsers(roomID:number):Observable<Array<User>>{
  return this.http.get<Array<User>>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/getBannedUsers.do?roomID="+roomID);
}

//"true" if the user was given "invited" permissions, "false" otherwise
unBanUser(roomID:number,admin:string,banneduser:string):Observable<boolean>{
  return this.http.post<boolean>("http://ec2-18-204-216-193.compute-1.amazonaws.com:8080/Project2/unBanUser.do",
  "roomID="+roomID+
  "&admin="+admin+
  "&banneduser="+banneduser,
  this.httpOptions);
}


}