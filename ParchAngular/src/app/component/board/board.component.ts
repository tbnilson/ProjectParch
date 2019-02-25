import { Component, OnInit } from '@angular/core';
import { FakeDatabase, User, Permission } from '../FakeDatabase/FakeDatabase';
import { Board } from '../FakeDatabase/FakeDatabase';
import { Post } from '../FakeDatabase/FakeDatabase';
import { Router, ActivatedRoute } from '@angular/router';

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

  constructor(private router: Router, private route: ActivatedRoute) { 
    
    // let username : string = route.snapshot.paramMap["username"];
    // let password : string = route.snapshot.paramMap["password"];
    // if (username == null) {
    //   this.router.navigateByUrl("login");
    // }
    
    
    FakeDatabase.generateDatabase();
    this.selectedUser = FakeDatabase.getUser("User0");
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


