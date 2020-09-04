import { Component, OnInit } from '@angular/core';
import { ApiService, ConfigService } from 'app/service';
@Component({
  selector: 'app-reservations-request',
  templateUrl: './reservations-request.component.html',
  styleUrls: ['./reservations-request.component.css']
})
export class ReservationsRequestComponent implements OnInit {

  constructor(private apiService :ApiService,    
    private conf: ConfigService){

}
reservationList;
  ngOnInit() {
    this.apiService.get(this.conf.api_url + '/appointment/room/next-free').subscribe( data => this.reservationList = data);
  }
  makeReservation(item){
    
  }
}
