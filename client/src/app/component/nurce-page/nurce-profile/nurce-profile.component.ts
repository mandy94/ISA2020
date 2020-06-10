import { Component, OnInit } from '@angular/core';
import { UserService } from 'app/service';

@Component({
  selector: 'app-nurce-profile',
  templateUrl: './nurce-profile.component.html',
  styleUrls: ['./nurce-profile.component.css']
})
export class NurceProfileComponent implements OnInit {

  constructor(private userService : UserService) { }

  ngOnInit() {
    this.userService.getMyInfo();
  }
 
}
