import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class CodebookService {

  constructor( private apiService: ApiService) { }
  private _api_url = 'http://localhost:8080';

  getCodesForDiagnoses(){ return  this.apiService.get(this._api_url +"/api/codes/diagnoses");}
  getCodesForMedication(){return this.apiService.get(this._api_url +"/api/codes/medication");}
  getCodesForExaminationReports(){return this.apiService.get(this._api_url +"/api/codes/reports");}
  getCodesForOperationRooms(){return this.apiService.get(this._api_url +"/api/codes/rooms");}
  getCodesForTherapies(){return this.apiService.get(this._api_url +"/api/codes/therapies");}
}
