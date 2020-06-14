import {Component, OnInit} from '@angular/core';
import { UserService, CodebookService } from 'app/service';
import {ActivatedRoute} from '@angular/router';


@Component({

  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

constructor(private userService: UserService,
        private codebookService: CodebookService){
  
}
response:{};
public users:any;
  ngOnInit() {
    this.getUsers();
  }
  
  public getUsers():void{
    this.userService.getAll().subscribe( (data)=> this.users = data

    );
  }
 
}


