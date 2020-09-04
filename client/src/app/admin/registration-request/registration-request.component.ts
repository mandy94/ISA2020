import { Component, OnInit } from '@angular/core';
import { UserService, ApiService, ConfigService, AuthService } from 'app/service';
import { subscribeOn } from 'rxjs/operators';
import { Router } from '@angular/router';
import { RequestResponseDialogComponent } from './request-response-dialog/request-response-dialog.component';
import { MatDialog } from '@angular/material';

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
    private router: Router,
    public dialog: MatDialog) { }


  displayedColumns = ['fullName', 'email', 'actions']
  pendingUserList: [];
  status_message: string;

  pendingUsers() {
    return this.pendingUserList === [] ? true : false;
  }

  ngOnInit() {

    this.apiservice.get(this.conf.pending_users_url)
      .subscribe(data => this.pendingUserList = data);
  }

  allow(user) {

    this.apiservice.get(this.conf.auth_url + "/allow/" + user.username)
      .subscribe((data) => {
        this.pendingUserList = data;
        this.status_message = " Korisnik " + user.username + " uspesno registrovan";
      });
  }
  decline(user) {

    const dialogRef = this.dialog.open(RequestResponseDialogComponent, {
      width: '450px'

    });

    dialogRef.afterClosed().subscribe(result => {
      
      this.apiservice.put(this.conf.auth_url + "/deny/"+user.username ,{ descripton: result, email: user.email})
        .subscribe((data) => {
          this.pendingUserList = data;
          this.apiservice.get(this.conf.denied_users_url)
            .subscribe();
        }
        );
      });    
    }

    back(){
      this.router.navigate(['/admin']);
    }
    
}
