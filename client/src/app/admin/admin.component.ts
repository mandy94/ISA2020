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
    // this.userService.getMyInfo().subscribe(me =>{
      // if(me.doIHaveToChangePassword){
      //   this.router.navigate(['/change-password']);
      // }
    // })
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
  gotoReservationRequestPage(){
    this.router.navigate(['/reservation-requests']);
  
  }
  gotomandatoryDoctorsPage(){
    this.router.navigate(['/mandatory-doctors']);
  
  }

 
}


