import { BrowserTransferStateModule } from '@angular/platform-browser';

export class Board {
    name : String;

    constructor(name : String) {
        this.name = name;
    }
}

export class Post {
    static idGenerator : number = 0;

    id : number;
    board : Board;
    user : String;
    text : String;

    constructor(board : Board, user : String, text : String) {
        this.id = Post.idGenerator++;
        this.board = board;
        this.user = user;
        this.text = text;
    }

}

export class FakeDatabase {

    static boards : Board[];
    static posts : Post[];

    static generateBoards() : void {
        
        this.boards = [new Board("Board0"), new Board("Board1"), new Board("Board2")];
    }

    static generatePosts() : void {
        this.posts = [new Post(this.boards[0], "User0", "Post0"), 
        new Post(this.boards[0], "User1", "Post1"), new Post(this.boards[0], "User2", "Post2")];
    }

    static getPost(id : Number) : Post {
        for (let i :number = 0; i < this.posts.length ; i++) {
            if (this.posts[i].id == id) {
                return this.posts[i];
            }
        }
    }

    static createPost(board : Board, user : String, text : String) : void {
        this.posts.push(new Post(board, user, text));
    }

    static deletePost(id : Number) : void {
        for (let i :number = 0; i < this.posts.length ; i++) {
            if (this.posts[i].id == id) {
                this.posts.splice(i, 1);
                return;
            }
        }
    }

    static getPostsOfBoard( board : Board) : Post[] {
        let boardPosts : Post[] = [];

        for (let i :number = 0; i < this.posts.length ; i++) {
            if (this.posts[i].board == board) {
                boardPosts.push(this.posts[i]);
            }
        }
        return boardPosts;
    }

    static  getBoard(name : String) : Board {
        for (let i = 0; i < this.boards[i].name.length; i++ ) {
            if (this.boards[i].name == name) {
                return this.boards[i];
            }
        }
    }

    static getBoards() : Board[] {
        return this.boards;
    }
}

