export class User{
    constructor(data: any){
        this.username = data.username,
        this.password = data.password
    }
    username: string;
    password: string;
}