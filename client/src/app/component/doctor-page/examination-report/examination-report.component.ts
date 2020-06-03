import { Component, OnInit } from '@angular/core';
import { ApiService, ConfigService, UserService } from 'app/service';
import { DataRowOutlet } from '@angular/cdk/table';
import { Observable } from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { FormControl } from '@angular/forms';
import {formatDate} from '@angular/common';
import { Report } from './report';

@Component({
  selector: 'app-examination-report',
  templateUrl: './examination-report.component.html',
  styleUrls: ['./examination-report.component.css'],
  
  
})
export class ExaminationReportComponent implements OnInit {

  constructor( private apiService: ApiService,
              private config: ConfigService,
              private userService: UserService
              ) { }
    patiens:any;
    filteredOptions : Observable<any>;
    myControl = new FormControl();
    pacient_jmbg: String;
    currentPacient: any;
    allVisits: any;
    today: any;

  private _filter(value: string): string[] {
    const filterValue = value;

      return this.patiens.filter(option => option.toString().indexOf(filterValue) === 0);
  }
  getPacientByJMBG(){
    this.apiService.get(this.config.api_url + '/user/pacient/' + this.pacient_jmbg)
    .subscribe((data) => {
      this.currentPacient = data;
    });

  }
  getPacientExaminationReports(){
    this.apiService.get(this.config.api_url + '/examination-reports/pacient/' + this.pacient_jmbg)
    .subscribe((data) => {
      this.allVisits = data;
    });
  }
  getCodebooks(){ 
     this.apiService.get(this.config.api_url+'/codes/diagnoses/all')
  .subscribe((data) => {
    this.diagnoseList = data;
  });
  this.apiService.get(this.config.api_url+'/codes/medications/all')
  .subscribe((data) => {
    this.medList = data;
  });      
}
  onSearchClicked(){  
    this.getPacientByJMBG();
    this.getPacientExaminationReports();
   this.getCodebooks();
          
  }
  diagnoseList : any;
  medList : any;
  firstVisit(){
    if(this.allVisits!=null)
    return this.allVisits.length == 0 ?  true : false; 
  }
  ngOnInit() {
      this.apiService.get(this.config.api_url + '/user/pacient/jmbg')
          .subscribe((data) => {
            this.patiens = data;
            this.filteredOptions = this.myControl.valueChanges.pipe(
              startWith(''),
              map(value => this._filter(value))
            );
          });
           this.today = formatDate(new Date(), 'dd/MM/yyyy', 'en');
  }
  selected_med: any;
  selected_diagnose: any;
  description: string;
  new_report = new Report();

  saveReport(){
    this.new_report.diagnose_id = this.selected_diagnose;
    this.new_report.med_id = this.selected_diagnose;
    this.new_report.details = this.description;
    this.new_report.user_id = this.currentPacient.id;
    this.new_report.user_jmbg = this.currentPacient.jmbg;
    this.new_report.doctor_id = this.userService.currentUser.id;

    console.log(this.new_report);
    this.apiService.post(this.config.api_url + '/examination-reports/add', this.new_report)
                  .subscribe(() => this.getPacientExaminationReports());
  }

}
