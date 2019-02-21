import { Component, OnInit } from '@angular/core';
import { FakeDatabase, User } from '../FakeDatabase/FakeDatabase';
import { Board } from '../FakeDatabase/FakeDatabase';
import { Post } from '../FakeDatabase/FakeDatabase';

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
  boards : Board[] = []
  newPostText : String = "";

  constructor() { 
    
    FakeDatabase.generateDatabase();
    this.selectedUser = FakeDatabase.getUser("User0");
    this.selectedUserBoards = FakeDatabase.getBoardsOfUser(this.selectedUser);
    this.selectedBoard = FakeDatabase.getBoard("Select Board");
    this.boards = FakeDatabase.getBoards();
    
  }

  createPost() : void {
    FakeDatabase.createPost(this.selectedBoard, this.selectedUser, this.newPostText);
    this.newPostText = "";
    this.updatePosts();
  }

  deletePost(id : Number) : void {
    if (FakeDatabase.getPermissionOfUserBoard(this.selectedUser, this.selectedBoard).type != "admin") {
      return;
    }
    FakeDatabase.deletePost(id);
    this.updatePosts();
  }

  updatePosts() : void{
    this.posts = FakeDatabase.getPostsOfBoard(this.selectedBoard);
  }

  selectBoard(boardText : String) {

    this.selectedBoard = FakeDatabase.getBoard(boardText);
    if (this.selectedBoard.name == "Select Board") {
      
      let postText : HTMLInputElement = <HTMLInputElement> document.getElementById("postText");
      postText.disabled = true;
      let newPostButton : HTMLButtonElement = <HTMLButtonElement> document.getElementById("newPostButton");
      newPostButton.disabled = true;
    }
    //Block access to boards where there is no permission.
    // else if (FakeDatabase.getPermissionOfUserBoard(this.selectedUser, FakeDatabase.getBoard(boardText)) == null) {
    //     this.selectedBoard = FakeDatabase.getBoard("Select Board");
    //     let boardSelection : HTMLSelectElement =  <HTMLSelectElement> document.getElementById("boards");
    //     boardSelection.selectedIndex = 0;
    //     let postText : HTMLInputElement = <HTMLInputElement> document.getElementById("postText");
    //     postText.disabled = true;
    //     let newPostButton : HTMLButtonElement = <HTMLButtonElement> document.getElementById("newPostButton");
    //     newPostButton.disabled = true;
    // }
    else {
      let postText : HTMLInputElement = <HTMLInputElement> document.getElementById("postText");
      postText.disabled = false;
      let newPostButton : HTMLButtonElement = <HTMLButtonElement> document.getElementById("newPostButton");
      newPostButton.disabled = false;
    }
    
    this.updatePosts();
  }



  ngOnInit() {

  }

}


