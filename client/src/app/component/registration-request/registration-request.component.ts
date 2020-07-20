import { Component, OnInit } from '@angular/core';
import { UserService, ApiService, ConfigService, AuthService } from 'app/service';
import { subscribeOn } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration-request',
  templateUrl: './registration-request.component.html',
  styleUrls: ['./registration-request.component.css']
})
export class RegistrationRequestComponent implements OnInit {

  constructor(private userservice: UserService,
    private apiservice: ApiService,
    private conf: ConfigService,
    private auth: AuthService,
    private router: Router) { }
pendingUserList:[];
activeUserList: any;
deniedUserList : any;
status_message: string;
pendingUsers(){
  return this.pendingUserList === [] ? true : false;
}

ngOnInit() {
  if(!this.userservice.amIAdmin() || !this.auth.tokenIsPresent()){
    this.router.navigate([this.conf.login_url]);
  }else{
    this.apiservice.get(this.conf.pending_users_url)
    .subscribe(data => this.pendingUserList = data);
    
    this.apiservice.get(this.conf.users_url)
    .subscribe(data => this.activeUserList = data);
    
    this.apiservice.get(this.conf.denied_users_url)
    .subscribe(data => this.deniedUserList = data);
  }
}
allow(username: string){
    
  this.apiservice.get(this.conf.auth_url +"/allow/" + username)
  .subscribe((data) => {
    this.pendingUserList = data;
    this.status_message =" Korisnik "+  username + " uspesno registrovan";
  });
}
deny(username: string){
  
  this.apiservice.get(this.conf.auth_url +"/deny/" + username)
  .subscribe((data) => {
    this.pendingUserList = data;  
    this.apiservice.get(this.conf.denied_users_url)
    .subscribe(data => this.deniedUserList = data);    
  } 
  );
}
}