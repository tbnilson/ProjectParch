import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UsernameService } from 'src/app/services/username.service';
import { Observable } from 'rxjs';

import { RoomServiceService } from 'src/app/services/room-service.service';
import { ParchSnackbarComponent } from '../parch-snackbar/parch-snackbar.component';
import { MatSnackBar } from '@angular/material';
import { PostingService } from 'src/app/services/posting.service';



import { Board } from 'src/app/models/Board';
import { User } from 'src/app/models/User';
import { Post } from 'src/app/models/Post';
import { Permission } from 'src/app/models/Permission';


@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})


export class BoardComponent implements OnInit {
  selectedBoard : Board = new Board(-1, "Select Board");
  selectedUserBoards : Board[] = [];
  user : string = "";
  selectedPosts : Post[] = [];
  users : User[] = [];
  posts : Post[] = [];
  invites : Permission[] = [];
  newBoardText : string = "";
  newPostText : string = "";

  



  constructor(private router: Router, 
               private route: ActivatedRoute, 
               private uServ : UsernameService, 
               private pServ : PostingService, 
               private rServ : RoomServiceService,
               private snackBar: MatSnackBar) {
    
                

    //get username
    let cuno : Observable<string> = this.uServ.currentUsername;
    cuno.subscribe( (response) => {
      this.user = response;
      this.update();
    },
    (response) => {
      this.router.navigateByUrl("login");
    }
    );

    
  }

  completeSetup() : void {
    let userAdmin : boolean = false;
    let userMod : boolean = false;
    let targetAdmin : boolean = false;
    let targetMod : boolean = false;
    let targetUser : boolean = false;

    if (!userMod) {
      document.getElementById("permissionShow").setAttribute("style", "display: none");
    }
    // else if (!userAdmin) {
    //   document.getElementById("modOption").setAttribute("style", "display: none");
    //   document.getElementById("removeModOption").setAttribute("style", "display: none");
    // } 
    this.update();
  }

  update() : void{
    //update boardlist, invites, posts
    this.rServ.getUserRooms(this.user + "").subscribe( (response) => {
      this.selectedUserBoards = response;
    },
    (response) => {
    }
    );

    if (this.selectedBoard.roomID != -1) {
      this.getMessagesBefore(0, 1000, this.selectedBoard.roomID);
    }

  }

  //----------------------------User Operations
  getRoomUsers(roomID:number){
    this.rServ.getRoomUsers(roomID).subscribe(
      (response)=>{
        //response is A json array of all users that are admins, moderators, or users of a room.
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }

  //----------------------------Board Operations
  selectBoard(boardText : String) {
    //select board
    for (let i = 0 ; i < this.selectedUserBoards.length; i++) {
      if (this.selectedUserBoards[i].roomname == boardText) {
        this.selectedBoard = this.selectedUserBoards[i];
      }
    }
    this.update();
    document.getElementById("postText").removeAttribute("disabled");
    document.getElementById("newPostButton").removeAttribute("disabled");
  }

  showCreateBoard() : void {
    
    if (document.getElementById("createBoard").getAttribute("style") == "display: inline-block") {
      document.getElementById("createBoard").setAttribute("style", "display: none");
    }
    else {
      document.getElementById("createBoard").setAttribute("style", "display: inline-block");
    }
  }

  createBoard() : void {
    //create board
    this.rServ.createRoom(this.user + "" ,  this.newBoardText + "").subscribe( (response) => {
      this.selectedBoard = response;
      this.update();
    },
    (response) => {
      this.selectedBoard = response;
      this.update();
    }
    );

    this.showCreateBoard();
  }

  deleteRoom(admin:string,roomID:number){
    this.rServ.deleteRoom(admin,roomID).subscribe(
      (response)=>{
        //response is "true" if the room was successfully deleted, "false" otherwise.
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }
  
  //----------------------------Post Operations
  getNewMessages(postID:number){
    this.pServ.getNewMessages(postID).subscribe(
      (response)=>{
        //response is a json array representing all posts created after postID in the same room
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }

  postMessage(){
    this.pServ.postMessage(this.user + "", this.selectedBoard.roomID, this.newPostText + "").subscribe(
      (response)=>{
        //response is the post they added to the DB
        //so you can probably just update 
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }

  getMessagesBefore(start:number, num:number,roomID:number){
    let recentMessages: Observable<Array<Post>> = this.pServ.getMessagesBefore(start,num,roomID);
    recentMessages.subscribe(
      (response)=>{
        this.posts = response;
      }
      ,
      (response)=>{
        this.posts = [];

      }
    )
  }

  editPost(id:number,newmessage:string) : void {
    let editPost: Observable<boolean>=this.pServ.editMessage(id,this.user,newmessage);
    editPost.subscribe(
      (response)=>{
        console.log(response)
        this.update();
      },
      (response)=>{
        this.snackBar.openFromComponent( ParchSnackbarComponent, {
          duration: 3000,
          verticalPosition: 'top',
          horizontalPosition: 'center',
          data: {message: "The post could not be edited"}
        })
      })
  }

  deletePost(id : number) : void {
    console.log(id);
    //delete post
    let deletePost: Observable<boolean>=this.pServ.deleteMessage(id, this.user + "");
    deletePost.subscribe(
      (response)=>{
        console.log(response)
        this.update();
      },
      (response)=>{
        console.log(response);
        // this.snackBar.openFromComponent( ParchSnackbarComponent, {
        //   duration: 3000,
        //   verticalPosition: 'top',
        //   horizontalPosition: 'center',
        //   data: {message: "The post could not be deleted"}
        // })
      }
    )
    this.update();
  }

  //----------------------------Permission Operations
  banUser(roomID:number,admin:string,banneduser:string){
    this.rServ.banUser(roomID,admin,banneduser).subscribe(
      (response)=>{
        //response is "true" if the user was successfully banned, "false" otherwise.
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }

  getBannedUsers(roomID:number){
    this.rServ.getBannedUsers(roomID).subscribe(
      (response)=>{
        //response is a list of User objects which have been banned from the specified room.
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }

  unBanUser(roomID,admin,banneduser){
    this.rServ.unBanUser(roomID,admin,banneduser).subscribe(
      (response)=>{
        //response is "true" if the user was given "invited" permissions, "false" otherwise
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }

  getInvites(username:string){
    this.rServ.getInvites(username).subscribe(
      (response)=>{
        //response is a list of Permission object for the specified user 
        //where their permissions are set as "invited". Can return an empty list.
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }

  acceptInvitation(roomID:number, username:string){
    this.rServ.acceptInvite(roomID, username).subscribe(
      (response)=>{
        if(response){
          this.update();
        }
        else{
          //user is banned
          
        }
      }
      ,
      (response)=>{
        console.log(response);

      }
    )
    
  }

  inviteUser(roomID:number, inviter:string, invitee:string){
    this.rServ.inviteUser(roomID,inviter,invitee).subscribe(
      (response)=>{
        if(response){
          //true when invite sent successfully
        }
        else{
          //false if the user is already in the room or banned
        }

      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }

  rejectInvitation(roomID:number,username:string) : void {
    this.rServ.rejectInvite(roomID,username).subscribe(
      (response)=>{
        //response is "true" if the user's invitation was successfuly rejected, "false" otherwise.
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }

  showInvites() : void {
    let inviteDiv : HTMLDivElement = <HTMLDivElement> document.getElementById("invites");
    if (inviteDiv.getAttribute("style") == "z-index: 1; display: block; position: absolute; top: 60px") {
      inviteDiv.setAttribute("style", "z-index: 1; display: none");
    }
    else if (this.invites.length != 0) {
      inviteDiv.setAttribute("style", "z-index: 1; display: block; position: absolute; top: 60px");
    }
  }

  makeModerator(roomID:number,admin:string,user:string){
    this.rServ.makeModerator(roomID,admin,user).subscribe(
      (response)=>{
        //response is "true" if the user was successfully made into a mod, "false" otherwise.
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
  }

  changePermission() : void {
    //create invite
  }

  showInviteCreator() : void {
    let inviteDiv : HTMLDivElement = <HTMLDivElement> document.getElementById("inviteCreator");
    if (inviteDiv.getAttribute("style") == 
    "z-index: 1; display: block; position: absolute; top: 60px; left: 300px") {
      inviteDiv.setAttribute("style", "z-index: 1; display: none");
    }
    else {
      inviteDiv.setAttribute("style", "z-index: 1; display: block; position: absolute; top: 60px; left: 300px");
    }
  }

  ngOnInit() {
    if(this.user == ""){
      this.router.navigateByUrl("login");
    }
  }

}


