import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { CodebookService } from 'app/service';
import { Room } from 'app/component/doctor-page/examination-report/report';
import { AddNewItemDialogComponent } from '../add-new-item-dialog/add-new-item-dialog.component';
import { MatDialog } from '@angular/material';
import { ApiService, ConfigService } from 'app/service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {

  constructor(private codebookservice : CodebookService,private apiService: ApiService,
    private conf: ConfigService,public dialog: MatDialog) { }

  ngOnInit() {
    this.getRooms();
  }
  rooms: any;
  newRoom = new FormControl(' ');
  displayedColumns = ['id','code', 'name', 'edit', 'delete'];
  getRooms(){
    this.codebookservice.getCodesForOperationRooms()
    .subscribe( data => {this.rooms = data});

  }
  delete(room){
    this.apiService.delete(this.conf.api_url + "/codes/room", room.id).subscribe(()=>    this.getRooms());  
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(AddNewItemDialogComponent, {
      width: '350px',
      data: { resourceName:"operacione sobe" }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getRooms();
    });
  }
 
}
