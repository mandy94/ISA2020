import { Component, OnInit } from '@angular/core';
import { CodebookService } from 'app/service';
import { FormControl } from '@angular/forms';
import { Therapy } from '..';
import { AddNewItemDialogComponent } from '../add-new-item-dialog/add-new-item-dialog.component';
import { MatDialog } from '@angular/material';
import { ApiService, ConfigService } from 'app/service';

@Component({
  selector: 'app-thrapies',
  templateUrl: './thrapies.component.html',
  styleUrls: ['./thrapies.component.css']
})
export class ThrapiesComponent implements OnInit {


  constructor(private codebookservice : CodebookService,private apiService: ApiService,
    private conf: ConfigService,public dialog: MatDialog) { }



  therapies: any;
  newTherapy = new FormControl('');
  displayedColumns = ['id', 'name', 'edit', 'delete'];
 
  ngOnInit() {
    
    this.getTherapies();
  }
  delete(therapy){
    this.apiService.delete(this.conf.api_url + "/codes/therapy", therapy.id).subscribe(()=>this.getTherapies());  
  }
  getTherapies(){
    this.codebookservice.getCodesForTherapies()
    .subscribe( data => {this.therapies = data});

  }openDialog(): void {
    const dialogRef = this.dialog.open(AddNewItemDialogComponent, {
      width: '350px',
      data: { resourceName:"terapije" }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getTherapies();
    });
  }
}
