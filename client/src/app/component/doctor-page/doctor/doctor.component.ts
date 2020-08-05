import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }
  gotoDoctorExaminations(){
    
    this.router.navigate(['/examination']);
  }
  gotoDoctorCalendar(){
    this.router.navigate(['/doctor-calendar']);
  }
}
