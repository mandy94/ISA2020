import {Component, OnInit} from '@angular/core';
import { UserService, CodebookService } from 'app/service';
import {ActivatedRoute, RouteReuseStrategy, Router} from '@angular/router';


@Component({

  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

constructor(private userService: UserService,
        private codebookService: CodebookService,
        private router: Router){
  
}
response:{};
public users:any;
  ngOnInit() {
    
  }
  gotoRegistrationPage(){
    this.router.navigate(['/registration-requests']);
  }
  gotoDeniedUsersPage(){
    this.router.navigate(['/denied-users']);  
  }
  gotoActiveUsrsPage(){
    this.router.navigate(['/active-users']);
  
  }
  gotomandatoryDoctorsPage(){
    this.router.navigate(['/mandatory-doctors']);
  
  }

 
}


