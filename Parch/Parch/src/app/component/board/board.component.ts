import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})


export class BoardComponent implements OnInit {

  private posts : string[][];
  text : Array<string>  = [];

  constructor() { 
    this.posts = [["Idiot0", "post0"], ["Idiot1", "post1"], ["Idiot2", "post2"],
     ["Idiot3", "post3"]];
    
    for (let i : number = 0; i < this.posts.length; i++) {
      this.text.push(this.posts[i][0] + ": " + this.posts[i][1]);
    }

  }

  ngOnInit() {

  }

}
