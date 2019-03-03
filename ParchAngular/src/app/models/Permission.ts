export class Permission {

    permID:number;
    permissions:string;
    username:string;
    roomID:number;

    constructor(perm_id:number, permissions:string, parchuser_username:string, room_id:number) {
        this.permID = perm_id;
        this.permissions = permissions;
        this.username = parchuser_username;
        this.roomID = room_id;
        
    }


}