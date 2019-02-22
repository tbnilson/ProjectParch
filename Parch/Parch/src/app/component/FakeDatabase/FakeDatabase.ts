import { BrowserTransferStateModule } from '@angular/platform-browser';

export class User {
    static idGenerator : number = 0;
    
    id : number;
    name : string;
    email : string;

    constructor(name : string, email : string) {
        this.id = User.idGenerator++;
        this.name = name;
        this.email = email;
    }
}

export class Board {
    name : String;

    constructor(name : String) {
        this.name = name;
    }
}

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

export class FakeDatabase {

    static boards : Board[];
    static users : User[];
    static permissions : Permission[];
    static posts : Post[];

    static generateBoards() : void {
        this.boards = [new Board("Select Board"), new Board("Board0"), new Board("Board1"), new Board("Board2"),
        new Board("Board3")];
    }

    static generateUsers() : void {
        this.users = [new User("User0", "Austin.Tauer@gmail.com"), new User("User1", "Austin.Tauer@gmail.com"),
         new User("User2", "Austin.Tauer@gmail.com")];
    }

    static generatePermissions() : void {
        this.permissions = [new Permission(FakeDatabase.users[0], FakeDatabase.boards[1], "admin"),
            new Permission(FakeDatabase.users[0], FakeDatabase.boards[2], "user"), 
            new Permission(FakeDatabase.users[0], FakeDatabase.boards[3], "invite"),
            new Permission(FakeDatabase.users[0], FakeDatabase.boards[4], "invite")];
    }

    static generatePosts() : void {
        this.posts = [];
    }

    static generateDatabase() : void {
        this.generateBoards();
        this.generateUsers();
        this.generatePermissions();
        this.generatePosts();
    }

    static getUser(name : string) : User {
        for (let i :number = 0; i < this.users.length ; i++) {
            if (this.users[i].name == name) {
                return this.users[i];
            }
        }
    }

    static getPost(id : Number) : Post {
        for (let i :number = 0; i < this.posts.length ; i++) {
            if (this.posts[i].id == id) {
                return this.posts[i];
            }
        }
    }

    static createPost(board : Board, user : User, text : String) : void {
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
        for (let i = 0; i < this.boards.length; i++ ) {
            if (this.boards[i].name == name) {
                return this.boards[i];
            }
        }
    }

    static getBoards() : Board[] {
        return this.boards;
    }

    static getBoardsOfUser(user : User) : Board[] {
        let userBoards : Board[] = [this.boards[0]];
        for (let i = 0; i < this.boards.length; i++ ) {
            if (this.getPermissionOfUserBoard(user, this.boards[i]) != null &&
             this.getPermissionOfUserBoard(user, this.boards[i]).type != "invite") {
                userBoards.push(this.boards[i]);
            }
        }
        return userBoards;
    }

    static getPermissionsOfUser(user : User) : Permission[] {
        let userPermissions : Permission[] = [];

        for (let i = 0; i < this.permissions.length; i++ ) {
            if (this.permissions[i].user == user) {
                userPermissions.push(this.permissions[i]);
            }
        }
        return userPermissions;
    }

    static getInvitesOfUser(user : User) : Permission[] {
        let userPermissions : Permission[] = [];

        for (let i = 0; i < this.permissions.length; i++ ) {
            if (this.permissions[i].user == user) {
                if (this.permissions[i].type == "invite") {
                    userPermissions.push(this.permissions[i]);
                }
            }
        }
        return userPermissions;
    }

    static getPermissionOfUserBoard(user : User, board : Board) : Permission {

        for (let i = 0; i < this.permissions.length; i++ ) {
            if (this.permissions[i].user == user && this.permissions[i].board == board) {
                return this.permissions[i];
            }
        }
    }

    static deletePermission(permission : Permission) : void {
        this.permissions.splice(this.permissions.indexOf(permission), 1);
    }

}

