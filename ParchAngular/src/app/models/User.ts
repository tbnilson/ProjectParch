export class User {
    username:string;
    password:string;
    email:string;
    constructor(em:string,unme:string,pwd:string) {
      this.username=unme;
      this.password=pwd;
      this.email=em;
    }
  }