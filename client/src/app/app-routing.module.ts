import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { AdminComponent } from './admin';
import { AdminGuard, GuestGuard, LoginGuard } from './guard';
import { NotFoundComponent } from './not-found';
import { ChangePasswordComponent } from './change-password';
import { ForbiddenComponent } from './forbidden';
import { SignupComponent } from './signup';
import { PacientsComponent, CalendarComponent, RecepieComponent, NurceProfileComponent } from './component/index';
import { DoctorComponent, ExaminationReportComponent } from './component/doctor-page';
import { RegistrationRequestComponent } from './admin/registration-request/registration-request.component';
import { DoctorCalendarComponent } from './component/doctor-page/doctor-calendar/doctor-calendar.component';
import { DiagnosesComponent } from './admin/codebook/diagnoses/diagnoses.component';
import { ThrapiesComponent } from './admin/codebook/thrapies/thrapies.component';
import { RoomsComponent } from './admin/codebook/rooms/rooms.component';
import { MedicationComponent } from './admin/codebook/medication/medication.component';
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
  }, {
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
    path: 'codes-diagnose',
    component: DiagnosesComponent
  },
  {
    path: 'codes-therapies',
    component: ThrapiesComponent
  },
  {
    path: 'codes-rooms',
    component: RoomsComponent
  },
  {
    path: 'codes-meds',
    component: MedicationComponent
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
