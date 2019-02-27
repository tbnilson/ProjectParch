import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { UsernameService } from 'src/app/services/username.service';
import { Observable } from 'rxjs';
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
  selectedUser : User;
  selectedPosts : Post[] = [];
  users : User[] = [];
  posts : Post[] = [];
  invites : Permission[] = [];
  newBoardText : String = "";
  newPostText : String = "";

  username : string;
  password : string;

  constructor(private router: Router, private route: ActivatedRoute, private logserv:LoginServiceService,
     private usern: UsernameService) {
    this.update();
  }

  confirmUser() : void {
    this.username = this.password = null;

    try {
      this.username = this.router.url.split("?")[1].split("&")[0].split("=")[1];
      this.password = this.router.url.split("?")[1].split("&")[1].split("=")[1];
      let loggedIn : Observable<Boolean> = this.logserv.login(this.username, this.password);
      loggedIn.subscribe(
        (response)=>{
          console.log(response);
          if(!response){
            this.router.navigateByUrl("login");
          }
        }
        ,
        (response)=>{
          console.log(response);
          this.router.navigateByUrl("login");
        }
      );
      
    }
    catch (e) {

    }
    if (this.username == null) {
      this.router.navigateByUrl("login");
    }
    else {
      this.selectedUser = new User(this.username, this.password);
    }
  }

  createPost() : void {
    this.update();
  }

  deletePost(id : Number) : void {
    this.update();
  }

  update() : void{

  }

  selectBoard(boardText : String) {
    
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
    //do database things
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

  createInvite() : void {
    
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


