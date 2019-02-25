import { Component, OnInit } from '@angular/core';
import { FakeDatabase, User, Permission } from '../FakeDatabase/FakeDatabase';
import { Board } from '../FakeDatabase/FakeDatabase';
import { Post } from '../FakeDatabase/FakeDatabase';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginServiceService } from 'src/app/services/login-service.service';
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
  posts : Post[] = [];
  invites : Permission[] = [];
  newPostText : String = "";

  username : string;
  password : string;

  constructor(private router: Router, private route: ActivatedRoute, private logserv:LoginServiceService) { 
    
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
    
    
    FakeDatabase.generateDatabase();
    this.selectedBoard = FakeDatabase.getBoard("Select Board");
    this.invites = FakeDatabase.getInvitesOfUser(this.selectedUser);
    this.update();
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

  acceptInvitation(invite : Permission) : void {
    invite.type = "user";
    this.update();
    if (this.invites.length == 0) {
      this.hideInvites();
    }
  }

  rejectInvitation(invite : Permission) : void {
    FakeDatabase.deletePermission(invite);
    this.update();
    if (this.invites.length == 0) {
      this.hideInvites();
    }
  }

  showInvites() : void {
    if (this.invites.length != 0) {
      let inviteDiv : HTMLDivElement = <HTMLDivElement> document.getElementById("invites");
      inviteDiv.setAttribute("style", "z-index: 1; display: block; position: absolute; top: 40px");
    }
  }

  hideInvites() : void {
    let inviteDiv : HTMLDivElement = <HTMLDivElement> document.getElementById("invites");
    inviteDiv.setAttribute("style", "z-index: 1; display: none");
  }

  ngOnInit() {
    
  }

}


