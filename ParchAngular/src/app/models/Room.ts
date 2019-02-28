export class Room {
    rid:number;
    roomname:string;
    posts:Array<string>;
    permissions:Array<string>;
    constructor(rid:number,rnme:string) {
      this.roomname=rnme;
      this.rid=rid;
    }
  }