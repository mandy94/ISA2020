import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser;

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) {
  }

  initUser() {
    const promise = this.apiService.get(this.config.refresh_token_url).toPromise()
      .then(res => {
        if (res.access_token !== null) {
          return this.getMyInfo().toPromise()
            .then(user => {
              this.currentUser = user;
            });
        }
      })
      .catch(() => null);
    return promise;
  }

  amIAdmin(){
    if(this.currentUser != null){
      var auths = this.currentUser.authorities;
      for(var auth of auths)
        if(auth.authority === "ROLE_ADMIN")
          return true;
      return false;
    }
  }
  amIDoctor(){
    
    if(this.currentUser != null){
      var auths = this.currentUser.authorities;
      for(var auth of auths)
        if(auth.authority === "ROLE_DOCTOR")
          return true;
      return false;
    }
  }
  amIPacient(){
    if(this.currentUser != null){
      var auths = this.currentUser.authorities;
      for(var auth of auths)
        if(auth.authority === "ROLE_PACIENT")
          return true;
      return false;
    }
  }
  amINurce(){
    if(this.currentUser != null){
      var auths = this.currentUser.authorities;
      for(var auth of auths)
        if(auth.authority === "ROLE_NURCE")
          return true;
      return false;
    }
  }

  getMyInfo() {
    return this.apiService.get(this.config.whoami_url)
      .pipe(map(user => {
        this.currentUser = user;        
        return user;
      }));
  }

  getAll() {
    return this.apiService.get(this.config.users_url);
  }

}
