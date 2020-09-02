import { Component, OnInit } from '@angular/core';
import { UserService, CodebookService, ApiService, ConfigService } from 'app/service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-denied-users',
  templateUrl: './denied-users.component.html',
  styleUrls: ['./denied-users.component.css']
})
export class DeniedUsersComponent implements OnInit {

 
constructor(private userService: UserService,
  private apiService: ApiService,
  private conf:ConfigService,
  private router: Router ){

}
usersList:any;
  ngOnInit() {
    this.apiService.get(this.conf.denied_users_url).subscribe(data => {this.usersList = data});
  }
  back(){
    this.router.navigate(['/admin']);
  }

}
