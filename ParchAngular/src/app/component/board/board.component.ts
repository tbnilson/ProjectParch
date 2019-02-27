import { Component, OnInit } from '@angular/core';
import { FakeDatabase, User, Permission } from '../FakeDatabase/FakeDatabase';
import { Board } from '../FakeDatabase/FakeDatabase';
import { Post } from '../FakeDatabase/FakeDatabase';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { UsernameService } from 'src/app/services/username.service';
import { Observable } from 'rxjs';

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
  testing : boolean = true;   //set if real database is not attached

  username : string;
  password : string;

  constructor(private router: Router, private route: ActivatedRoute, private logserv:LoginServiceService, private usern: UsernameService) { 
    if (this.testing) {
      FakeDatabase.generateDatabase();
      this.selectedUser = FakeDatabase.users[0];
    }
    else {
      this.confirmUser();
    }
    this.users = FakeDatabase.users;
    this.selectedBoard = FakeDatabase.getBoard("Select Board");
    this.invites = FakeDatabase.getInvitesOfUser(this.selectedUser);
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
    FakeDatabase.createPost(this.selectedBoard, this.selectedUser, this.newPostText);
    this.newPostText = "";
    this.update();
  }

  deletePost(id : Number) : void {
    if (FakeDatabase.getPermissionOfUserBoard(this.selectedUser, this.selectedBoard).type != "admin") {
      return;
    }
    FakeDatabase.deletePost(id);
    this.update();
  }

  update() : void{
    this.posts = FakeDatabase.getPostsOfBoard(this.selectedBoard);
    this.invites = FakeDatabase.getInvitesOfUser(this.selectedUser);
    this.selectedUserBoards = FakeDatabase.getBoardsOfUser(this.selectedUser);
  }

  selectBoard(boardText : String) {

    this.selectedBoard = FakeDatabase.getBoard(boardText);
    if (this.selectedBoard.name == "Select Board") {
      
      let postText : HTMLInputElement = <HTMLInputElement> document.getElementById("postText");
      postText.disabled = true;
      let newPostButton : HTMLButtonElement = <HTMLButtonElement> document.getElementById("newPostButton");
      newPostButton.disabled = true;
    }
    else {
      let postText : HTMLInputElement = <HTMLInputElement> document.getElementById("postText");
      postText.disabled = false;
      let newPostButton : HTMLButtonElement = <HTMLButtonElement> document.getElementById("newPostButton");
      newPostButton.disabled = false;
    }
    
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
    invite.type = "user";
    this.update();
    if (this.invites.length == 0) {
      this.showInvites();
    }
  }

  rejectInvitation(invite : Permission) : void {
    FakeDatabase.deletePermission(invite);
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


