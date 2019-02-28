export class Permission {

    perm_id:number;
    permissions:string;
    parchuser_username:string;
    room_id:number;

    constructor(perm_id:number, permissions:string, parchuser_username:string, room_id:number) {
        this.perm_id = perm_id;
        this.permissions = permissions;
        this.parchuser_username = parchuser_username;
        this.room_id = room_id;
        
    }


}