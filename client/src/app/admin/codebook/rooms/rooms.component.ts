import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { CodebookService } from 'app/service';
import { Room } from 'app/component/doctor-page/examination-report/report';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {

  constructor(private codebookservice : CodebookService) { }

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
  postRoom(){
    let room = new Room();
    room.name = this.newRoom.value;
      this.codebookservice.postRoom(room).subscribe(data => this.rooms = data);
  }
}
