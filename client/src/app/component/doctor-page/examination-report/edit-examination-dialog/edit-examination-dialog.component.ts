import { Component, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService, ConfigService, UserService } from 'app/service';
import { ReportDTO } from 'app/shared/models/other';
import * as moment from 'moment';
import { ShowOnDirtyErrorStateMatcher } from '@angular/material';

@Component({
  selector: 'app-edit-examination-dialog',
  templateUrl: './edit-examination-dialog.component.html',
  styleUrls: ['./edit-examination-dialog.component.css']
})
export class EditExaminationDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<EditExaminationDialogComponent>,
    private apiService: ApiService,
    private config: ConfigService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.today = moment().format('DD MMM YYYY');
  }
  ngOnInit() {
    this.getData();
    this.description = this.data.details;
    this.selected_diagnose = this.data.diagnose;

    this.selected_med = this.data.medication;
    this.selected_therapy = this.data.therapies;
  }
  today;
  diagnoseList;
  selected_diagnose;
  medList;
  selected_med;
  therapyList;
  selected_therapy = [];
  nurceList = [];
  edits;
  description;
  selected_nurce;


  getData() {
    this.apiService.get(this.config.api_url + '/user/nurces')
      .subscribe((data) => {
        this.nurceList = data;
        console.log(data);
      });
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
      });

  }
  editReport() {
  this.edits = {
    id: this.data.id,
    diagnose: this.selected_diagnose,
    details: this.description,
    therapies: this.selected_therapy
  }

    this.apiService.put(this.config.api_url + '/examination-report/edit', this.edits)
      .subscribe(() => this.dialogRef.close());
  }
}

