import { Component, OnInit } from '@angular/core';
import { ApiService, ConfigService } from 'app/service';
import { AddMandatoryDoctorDialogComponent } from './add-mandatory-doctor-dialog/add-mandatory-doctor-dialog.component';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-mandatory-doctors',
  templateUrl: './mandatory-doctors.component.html',
  styleUrls: ['./mandatory-doctors.component.css']
})
export class MandatoryDoctorsComponent implements OnInit {

  constructor(private apiService: ApiService,
    public dialog: MatDialog,
    private config: ConfigService) {

  }

  roomList;
  ngOnInit() {
    this.apiService.get(this.config.api_url + '/operation-rooms/all')
      .subscribe(data => this.roomList = data);

  }
  deleteDoctor(doctor, room){
    this.apiService.delete(this.config.api_url + '/delete/doctor/' + doctor.id + '/from-room', room.id)
    .subscribe(data => this.roomList = data);

  }
  showAddDialog(room){
    const dialogRef = this.dialog.open(AddMandatoryDoctorDialogComponent, {
      width: '650px',
      data: {room: room}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.apiService.get(this.config.api_url + '/operation-rooms/all')
      .subscribe(data => this.roomList = data);

        });
  }

}
