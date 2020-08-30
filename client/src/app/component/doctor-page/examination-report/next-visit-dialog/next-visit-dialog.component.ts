import { Component, OnInit } from '@angular/core';
import { ApiService, ConfigService, UserService } from 'app/service';
import { FormBuilder, FormControl } from '@angular/forms';
import * as moment from 'moment';

@Component({
  selector: 'app-next-visit-dialog',
  templateUrl: './next-visit-dialog.component.html',
  styleUrls: ['./next-visit-dialog.component.css']
})
export class NextVisitDialogComponent implements OnInit {
  groupControl: any;

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private formBuilder: FormBuilder,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.groupControl =this.formBuilder.group({      
      dateControl :  new FormControl(moment())
    });

    this.apiService
  
  }
  new_visit;

  schedule(){
    this.new_visit = {
      doctorid: this.userService.getMyId(),
      pacientid: {},
      visitDate: 0,
      visitTime:0
    }
    this.apiService.post(this.config.api_url + '/examination-report/add', this.new_visit).subscribe();
  }
  formatDate(input:any){  
    return moment(input).format("DDMMYYYY");    
  }
}
