import { Component, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService, ConfigService, UserService } from 'app/service';
import { ReportDTO } from 'app/shared/models/other';
import * as moment from 'moment';
import { ShowOnDirtyErrorStateMatcher } from '@angular/material';
@Component({
  selector: 'app-new-examination-dialog',
  templateUrl: './new-examination-dialog.component.html',
  styleUrls: ['./new-examination-dialog.component.css']
})

export class NewExaminationDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<NewExaminationDialogComponent>,
    private apiService: ApiService,
    private config: ConfigService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.getCodebooks();            
        this.today = moment().format('D MMM YYYY');

    }

  hasOperation = false;
  setOperation() { this.hasOperation = true; }
  onNoClick(): void {
    this.dialogRef.close();
  }
  today;
  diagnoseList;
  selected_diagnose
  medList;
  selected_med;
  therapyList;
  selected_therapy;
  new_report;
  description;

  getCodebooks() {
    this.apiService.get(this.config.api_url + '/codes/diagnoses/all')
      .subscribe((data) => {
        this.diagnoseList = data;
      });
    this.apiService.get(this.config.api_url + '/codes/medications/all')
      .subscribe((data) => {
        this.medList = data;
      });
    this.apiService.get(this.config.api_url + '/codes/therapies')
      .subscribe((data) => {
        this.therapyList = data
      })
  }
finished(){
  return this.description != undefined ? true: false;  
}

  saveReport() {
    this.new_report = new ReportDTO();
    this.new_report.details = this.description;
    this.new_report.diagnose  = this.selected_diagnose;
    this.new_report.therapies = this.selected_therapy;
    this.new_report.medication = this.selected_med;
    this.new_report.pecient = this.data.pacient;
    this.new_report.doctor = this.data.doctor;
    
    this.new_report.visitDate = moment().format('DD.MM.YYYY');
    this.new_report.visitTime = moment().format('HH.mm') ;

  console.log(this.new_report);
    this.apiService.post(this.config.api_url + '/examination-report/add', this.new_report)
      .subscribe();
  }
}
