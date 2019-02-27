import { Board } from './Board';
import { User } from './User';

export class Post {
    static idGenerator : number = 0;

    id : number;
    board : Board;
    user : User;
    text : String;

    constructor(board : Board, user : User, text : String) {
        this.id = Post.idGenerator++;
        this.board = board;
        this.user = user;
        this.text = text;
    }

}