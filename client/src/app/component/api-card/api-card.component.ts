import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import { AuthService, UserService} from 'app/service/';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {User} from '../../shared/models/user'; 

@Component({
  selector: 'app-api-card',
  templateUrl: './api-card.component.html',
  styleUrls: ['./api-card.component.scss']
})
export class ApiCardComponent implements OnInit {

  @Input() title: string;
  @Input() subTitle: string;
  @Input() imgUrl: string;
  @Input() content: string;
  @Input() apiText: string;
  @Input() responseObj: any;
  @Input() username: string;
  @Input() password: string;
  expand = false;


  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) {
  }

  ngOnInit() {
  }

  form : FormGroup;
  user: User;
  onButtonClick() {
   
    this.user = new User({
      username : this.username,
      password : this.password,
    });
    
    this.authService.login(this.user)
      .subscribe(data => {
          this.userService.getMyInfo().subscribe();
          this.router.navigate(['/']);
        });      
  }
  responsePanelClass() {
    const rClass = ['response'];
    if (this.expand) {
      rClass.push('expand');
    }
    if (this.responseObj.status) {
      this.responseObj.status === 200 ?
        rClass.push('response-success') :
        rClass.push('response-error');
    }
    return rClass.join(' ');
  }

}
