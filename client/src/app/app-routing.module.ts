import {NgModule, Component} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home';
import {LoginComponent} from './login';
import {AdminComponent} from './admin';
import {AdminGuard, GuestGuard, LoginGuard} from './guard';
import {NotFoundComponent} from './not-found';
import {ChangePasswordComponent} from './change-password';
import {ForbiddenComponent} from './forbidden';
import {SignupComponent} from './signup';
import {PacientsComponent,  CalendarComponent, RecepieComponent, NurceProfileComponent} from './component/index';
import {DoctorComponent, ExaminationReportComponent} from './component/doctor-page';
import { RegistrationRequestComponent } from './component/registration-request/registration-request.component';
import { DoctorCalendarComponent } from './component/doctor-page/doctor-calendar/doctor-calendar.component';
export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'doctor',
    component: DoctorComponent
  },
    {
    path: 'search-pacient',
    component: ExaminationReportComponent
  },
  {
    path: 'pacient-examination/:jmbg',
    component: ExaminationReportComponent
  },
  {
    path: 'pacients',
    component: PacientsComponent
  },
  {
    path: 'doctor-calendar',
    component: DoctorCalendarComponent
  },
  {
    path: 'calendar',
    component: CalendarComponent
  },
  {
    path: 'recepie',
    component: RecepieComponent
  },
  {
    path: 'nurce-profile',
    component: NurceProfileComponent
  },
  {
    path: 'signup',
    component: SignupComponent,
    canActivate: [GuestGuard],
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [GuestGuard]
  },{
    path: 'registration-request',
    component: RegistrationRequestComponent
  },
  {
    path: 'change-password',
    component: ChangePasswordComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AdminGuard]
  },
  {
    path: '404',
    component: NotFoundComponent
  },
  {
    path: '403',
    component: ForbiddenComponent
  },
  {
    path: '**',
    redirectTo: '/404'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule {
}
