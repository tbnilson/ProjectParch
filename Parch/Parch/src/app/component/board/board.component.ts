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

  text : Array<string>  = [];
  selectedBoard : Board = null;
  selectedUser : String = "";
  posts : Post[] = [];
  newPostText : String = "Post Text Here";

  constructor() { 
    this.selectedUser = "Austin";
    FakeDatabase.generateBoards();
    FakeDatabase.generatePosts();
    this.selectedBoard = FakeDatabase.getBoard("Board0");
    this.posts = FakeDatabase.getPostsOfBoard(this.selectedBoard);
    for (let i : number = 0; i < this.posts.length; i++) {
      this.text.push(this.posts[i].user + ": " + this.posts[i].text);
    }

  }

  createPost() : void {
    FakeDatabase.createPost(this.selectedBoard, this.selectedUser, this.newPostText);
    this.posts = FakeDatabase.getPostsOfBoard(this.selectedBoard);
    this.text = [];
    for (let i : number = 0; i < this.posts.length; i++) {
      this.text.push(this.posts[i].user + ": " + this.posts[i].text);
    }
  }

  ngOnInit() {

  }

}


