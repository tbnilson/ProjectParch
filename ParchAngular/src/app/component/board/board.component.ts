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
  user : String = "";
  selectedPosts : Post[] = [];
  users : User[] = [];
  posts : Post[] = [];
  invites : Permission[] = [];
  newBoardText : String = "";
  newPostText : String = "";

  username : string;
  password : string;
  roomname : string;



  constructor(private router: Router, 
               private route: ActivatedRoute, 
               private uServ : UsernameService, 
               private pServ : PostingService, 
               private rServ : RoomServiceService,
               private snackBar: MatSnackBar) {
    
                

    //get username
    let cuno : Observable<String> = this.uServ.currentUsername;
    cuno.subscribe( (response) => {
      this.user = response;
      this.update();
    },
    (response) => {
      this.router.navigateByUrl("login");
    }
    );

    
  }

  
  getMessagesBefore(start:number, num:number,roomID:number){
    let recentMessages: Observable<Array<Post>> = this.pServ.getMessagesBefore(start,num,roomID);
    recentMessages.subscribe(
      (response)=>{
        this.posts = response;

      }
      ,
      (response)=>{
        console.log(response);

      }
    )
  }

  createPost() : void {
    
    //create post

    this.update();
  }
  editPost(id:number,newmessage:string) : void {
    let editPost: Observable<boolean>=this.pServ.editMessage(id,this.username,newmessage);
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
    //delete post
    let deletePost: Observable<boolean>=this.pServ.deleteMessage(id,this.username);
    deletePost.subscribe(
      (response)=>{
        console.log(response)
        this.update();
      },
      (response)=>{
        this.snackBar.openFromComponent( ParchSnackbarComponent, {
          duration: 3000,
          verticalPosition: 'top',
          horizontalPosition: 'center',
          data: {message: "The post could not be deleted"}
        })
      }
      
    )
    
  }

  update() : void{
    //update boardlist, invites, posts
    this.rServ.getUserRooms(this.user + "").subscribe( (response) => {
      this.selectedUserBoards = response;
    },
    (response) => {
    }
    );
  }

  selectBoard(boardText : String) {
    //select board
    for (let i = 0 ; i < this.selectedUserBoards.length; i++) {
      if (this.selectedUserBoards[i].roomname == boardText) {
        this.selectedBoard = this.selectedUserBoards[i];
      }
    }
    this.update();
  }

  showCreateBoard() : void {
    
    if (document.getElementById("createBoard").getAttribute("style") == "display: inline-block") {
      document.getElementById("createBoard").setAttribute("style", "display: none");
    }
    else {
      document.getElementById("createBoard").setAttribute("style", "display: inline-block");
    }
  }

  postMessage(username:string,roomID:number,message:string){
    this.pServ.postMessage(username,roomID,message).subscribe(
      (response)=>{
        //response is the post they added to the DB
        //so you can probably just update the
      }
      ,
      (response)=>{
        console.log(response);
      }
    )
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

  rejectInvitation(invite : Permission) : void {

    this.update();
    if (this.invites.length == 0) {
      this.showInvites();
    }
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


