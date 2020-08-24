import { Component, OnInit } from '@angular/core';
import { CodebookService } from 'app/service';
import { FormControl } from '@angular/forms';
import { Therapy } from '..';

@Component({
  selector: 'app-thrapies',
  templateUrl: './thrapies.component.html',
  styleUrls: ['./thrapies.component.css']
})
export class ThrapiesComponent implements OnInit {


  constructor( private codebookservice : CodebookService) { }


  therapies: any;
  newTherapy = new FormControl('');
  displayedColumns = ['id', 'name', 'edit', 'delete'];
 
  ngOnInit() {
    
    this.getTherapies();
  }

  getTherapies(){
    this.codebookservice.getCodesForTherapies()
    .subscribe( data => {this.therapies = data});

  }
  postTherapy(){
    let therapy = new Therapy();
    therapy.name = this.newTherapy.value;
    
    this.codebookservice.postTherapy(therapy).subscribe((data) => this.therapies = data);
  }
}
