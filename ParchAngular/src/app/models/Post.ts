export class Post {
    username:string;
    message:string;
    timestamp:number;
    roomID:number;
    constructor(uname:string,mess:string,ts:number,rid:number){
        this.username = uname;
        this.timestamp = ts;
        this.message = mess;
        this.roomID = rid;
    }
}