import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { ApiService, ConfigService } from 'app/service';
import { config } from 'process';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-mandatory-doctor-dialog',
  templateUrl: './add-mandatory-doctor-dialog.component.html',
  styleUrls: ['./add-mandatory-doctor-dialog.component.css']
})
export class AddMandatoryDoctorDialogComponent implements OnInit {

  constructor(private apiService: ApiService,
    private config: ConfigService,
    public dialogRef: MatDialogRef<AddMandatoryDoctorDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  selDoctorControl = new FormControl('');
  dedicatedMandatoryDoctorList;
  otherDoctorList = [];
  ngOnInit() {
    this.apiService.get(this.config.api_url + '/operation-room/' + this.data.room.id + ' /mandatory-doctors')
      .subscribe(data => {
        let that = this;
        that.dedicatedMandatoryDoctorList = data;
        that.apiService.get(this.config.api_url + '/user/doctor')
          .subscribe(data => {
            let isDouble = false;
            data.forEach(temp => {
              that.dedicatedMandatoryDoctorList.forEach(element => {
                if (element.id == temp.id) {
                  isDouble = true;
                }
              });
              if (!isDouble)
                that.otherDoctorList.push(temp);
              isDouble = false;
            });

          });
      });
  }
  addDoctor() {
    console.log(this.selDoctorControl.value)
    this.selDoctorControl.value.forEach(id => {
      this.apiService.put(this.config.api_url + '/add/mandatory-doctor/' + id + '/room', this.data.room.id)
        .subscribe( () => this.dialogRef.close());
    });

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
