import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { UsernameService } from 'src/app/services/username.service';
import { Observable } from 'rxjs';
import { RoomServiceService } from 'src/app/services/room-service.service';

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
  selectedBoard : Board = null;
  selectedUserBoards : Board[] = [];
  user : String;
  selectedPosts : Post[] = [];
  users : User[] = [];
  posts : Post[] = [];
  invites : Permission[] = [];
  newBoardText : String = "";
  newPostText : String = "";

  username : string;
  password : string;
  roomname : string;


  constructor(private router: Router, private route: ActivatedRoute, private uServ : UsernameService,
     private usern: UsernameService) {
    this.update();

    //get username
    let cuno : Observable<String> = uServ.currentUsername;
    cuno.subscribe( (response) => {
      this.user = response;
    },
    (response) => {
      this.user = response;
    }
    );

    //get user boards

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
  }

  selectBoard(boardText : String) {
    //change selected board
    this.update();
  }

  showCreateBoard() : void {
    
    if (document.getElementById("createBoard").getAttribute("style") == "display: inline-block") {
      document.getElementById("createBoard").setAttribute("style", "display: none");
    }
    else {
      console.log("test");
      document.getElementById("createBoard").setAttribute("style", "display: inline-block");
    }
  }


  createBoard() : void {
    //create board
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
    this.usern.currentUsername.subscribe(user => this.username = user)
    console.log(this.username);
    if(this.username == ""){
      this.router.navigateByUrl("login");
    }
    
  }

}

