import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class CodebookService {

  constructor( private apiService: ApiService) { }
  private _api_url = 'http://localhost:8180/api/';

  getCodesForDiagnoses(){ return  this.apiService.get(this._api_url +"codes/diagnoses/all");}
  getCodesForMedication(){return this.apiService.get(this._api_url +"codes/medications/all");}
  getCodesForExaminationReports(){return this.apiService.get(this._api_url +"codes/reports");}
  getCodesForOperationRooms(){return this.apiService.get(this._api_url +"codes/rooms");}
  getCodesForTherapies(){return this.apiService.get(this._api_url +"codes/therapies");}
  
  postDiagnose(diagnose){ return this.apiService.post(this._api_url + "codes/diagnose", diagnose);}
  postTherapy(therapy){return this.apiService.post(this._api_url + "codes/therapy" , therapy);}
  postRoom(room){return this.apiService.post(this._api_url + "codes/room", room);}
  postMeds(med){return this.apiService.post(this._api_url + "codes/med", med);}
}
