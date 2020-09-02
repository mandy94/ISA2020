import { Component, OnInit } from '@angular/core';
import { UserService, ApiService, ConfigService } from 'app/service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registered-users',
  templateUrl: './registered-users.component.html',
  styleUrls: ['./registered-users.component.css']
})
export class RegisteredUsersComponent implements OnInit {

  constructor(private userservice: UserService,
    private apiservice: ApiService,
    private conf: ConfigService,
    private router: Router) { }

    
    activeUserList: any;
    deniedUserList: any;
    
  ngOnInit() {
    
    this.apiservice.get(this.conf.users_url)
    .subscribe(data => this.activeUserList = data);

  this.apiservice.get(this.conf.denied_users_url)
    .subscribe(data => this.deniedUserList = data);
  }
  back(){
    this.router.navigate(['/admin']);
  }

}
