import { Component, OnInit } from '@angular/core';
import { FakeDatabase } from './FakeDatabase';
import { Board } from './FakeDatabase';
import { Post } from './FakeDatabase';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})


export class BoardComponent implements OnInit {

  postText : String[]  = [];
  boardText : String[] = [];
  selectedBoard : Board = null;
  selectedUser : String = "";
  posts : Post[] = [];
  boards : Board[] = []
  newPostText : String = "";

  constructor() { 
    this.selectedUser = "Austin";
    FakeDatabase.generateBoards();
    FakeDatabase.generatePosts();
    this.selectedBoard = FakeDatabase.getBoard("Board0");
    this.updatePosts();
    this.boards = FakeDatabase.getBoards();
    for (let i : number = 0; i < this.boards.length; i++) {
      this.boardText.push(this.boards[i].name);
    }

  }

  createPost() : void {
    FakeDatabase.createPost(this.selectedBoard, this.selectedUser, this.newPostText);
    this.updatePosts();
  }

  updatePosts() : void{
    this.posts = FakeDatabase.getPostsOfBoard(this.selectedBoard);
    this.postText = [];
    for (let i : number = 0; i < this.posts.length; i++) {
      this.postText.push(this.posts[i].user + ": " + this.posts[i].text);
    }
  }

  selectBoard(boardText : String) {
    this.selectedBoard = FakeDatabase.getBoard(boardText);
    this.updatePosts();
  }

  ngOnInit() {

  }

}


