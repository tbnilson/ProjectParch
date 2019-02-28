import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UsernameService } from 'src/app/services/username.service';
import { Observable } from 'rxjs';
import { PostingService } from 'src/app/services/posting.service';


import { Board } from 'src/app/models/Board';
import { User } from 'src/app/models/User';
import { Post } from 'src/app/models/Post';
import { Permission } from 'src/app/models/Permission';
import { RoomServiceService } from 'src/app/services/room-service.service';

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
               private rServ : RoomServiceService) {
    
                
    //get username
    let cuno : Observable<String> = this.uServ.currentUsername;
    cuno.subscribe( (response) => {
      this.user = response;
      this.update();
    },
    (response) => {
      this.user = response;
      this.update();
    }
    );

    
  }

  
  getRecentMessages(roomID:number, start:number, numposts:number){
    let recentMessages: Observable<Array<Post>> = this.pServ.getRecentMessages(roomID,start,numposts);
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

  deletePost(id : Number) : void {
    //delete post
    this.update();
  }

  update() : void{
    //update boardlist, invites, posts
    this.rServ.getUserRooms(this.user + "").subscribe( (response) => {
      this.selectedUserBoards = response;
    },
    (response) => {
      console.log("failure");
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


  createBoard() : void {
    //create board
    this.rServ.createRoom(this.user + "" ,  this.newBoardText + "").subscribe( (response) => {
      this.selectedBoard = response;
    },
    (response) => {
      this.selectedBoard = response;
    }
    );

    this.showCreateBoard();
    this.update();

  }

  acceptInvitation(invite : Permission) : void {
    
    this.update();
    if (this.invites.length == 0) {
      this.showInvites();
    }
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
    
  }

}


