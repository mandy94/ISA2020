import {BrowserModule} from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FlexLayoutModule} from '@angular/flex-layout';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {HomeComponent} from './home';
import {LoginComponent} from './login';
import {AdminGuard, GuestGuard, LoginGuard} from './guard';
import {NotFoundComponent} from './not-found';
import {AccountMenuComponent} from './component/header/account-menu/account-menu.component';
import {ApiCardComponent, HeaderComponent} from './component';

import {ApiService, AuthService, ConfigService,  UserService} from './service';
import {ChangePasswordComponent} from './change-password/change-password.component';
import {ForbiddenComponent} from './forbidden/forbidden.component';
import {AdminComponent} from './admin/admin.component';
import {SignupComponent} from './signup/signup.component';
import {AngularMaterialModule} from './angular-material/angular-material.module';
import {MatIconRegistry} from '@angular/material';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { NurseComponent } from './nurse/nurse.component';
import { DoctorComponent , ExaminationReportComponent} from './component/doctor-page';

import { RegistrationRequestComponent } from './admin/registration-request/registration-request.component';
import { NewExaminationDialogComponent } from './component/doctor-page/examination-report/new-examination-dialog/new-examination-dialog.component';
import { NewOperationroomDialogComponent } from './component/doctor-page/examination-report/new-operationroom-dialog/new-operationroom-dialog.component';
import { NextVisitDialogComponent } from './component/doctor-page/examination-report/next-visit-dialog/next-visit-dialog.component';
import { DoctorCalendarComponent } from './component/doctor-page/doctor-calendar/doctor-calendar.component';
import { RegisteredUsersComponent } from './admin/registered-users/registered-users.component';
import { DiagnosesComponent } from './admin/codebook/diagnoses/diagnoses.component';
import { ThrapiesComponent } from './admin/codebook/thrapies/thrapies.component';
import { RoomsComponent } from './admin/codebook/rooms/rooms.component';
import { MedicationComponent } from './admin/codebook/medication/medication.component';
import { RequestResponseDialogComponent } from './admin/registration-request/request-response-dialog/request-response-dialog.component';
import { DeniedUsersComponent } from './admin/denied-users/denied-users.component';
import { MandatoryDoctorsComponent } from './admin/mandatory-doctors/mandatory-doctors.component';
import { AddMandatoryDoctorDialogComponent } from './admin/mandatory-doctors/add-mandatory-doctor-dialog/add-mandatory-doctor-dialog.component';
import { AddNewItemDialogComponent } from './admin/codebook/add-new-item-dialog/add-new-item-dialog.component';
import { ReservationsRequestComponent } from './admin/reservations-request/reservations-request.component';
import { EditExaminationDialogComponent } from './component/doctor-page/examination-report/edit-examination-dialog/edit-examination-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ApiCardComponent,
    HomeComponent,
    LoginComponent,
    NotFoundComponent,
    AccountMenuComponent,
    ChangePasswordComponent,
    ForbiddenComponent,
    AdminComponent,
    SignupComponent,    
    NurseComponent,
    DoctorComponent,
    ExaminationReportComponent,
    RegistrationRequestComponent,
    NewExaminationDialogComponent,
    NewOperationroomDialogComponent,
    NextVisitDialogComponent,
    DoctorCalendarComponent,
    RegisteredUsersComponent,
    DiagnosesComponent,
    ThrapiesComponent,
    RoomsComponent,
    MedicationComponent,
    RequestResponseDialogComponent,
    DeniedUsersComponent,
    MandatoryDoctorsComponent,
    AddMandatoryDoctorDialogComponent,
    AddNewItemDialogComponent,
    ReservationsRequestComponent,
    EditExaminationDialogComponent,
    
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,    
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    AngularMaterialModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    LoginGuard,
    GuestGuard,
    AdminGuard,
    
    AuthService,
    ApiService,
    UserService,
    ConfigService,
    MatIconRegistry,
  ],
  bootstrap: [AppComponent],
  entryComponents:[EditExaminationDialogComponent,AddNewItemDialogComponent,AddMandatoryDoctorDialogComponent,RequestResponseDialogComponent,NewExaminationDialogComponent,NextVisitDialogComponent,NewOperationroomDialogComponent]
})
export class AppModule {
}
