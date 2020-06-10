import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {

  constructor( private apiService: ApiService) { }
  private _api_url = 'http://localhost:8080/api';

  public getPrescriptionByNurseId(id: number){ return  this.apiService.get(this._api_url +"/prescription/nurse/" + id);}
  public signPrescription(id){ return this.apiService.put(this._api_url + "/prescription/sign/" + id, null);}
}
