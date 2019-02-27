import { User } from './User';
import { Board } from './Board';

export class Permission {
    static idGenerator : number = 0;

    id : number;
    user : User;
    board : Board;
    type : string;

    constructor(user : User, board : Board, type : string) {
        this.id = Permission.idGenerator++;
        this.user = user;
        this.board = board;
        this.type = type;
    }


}