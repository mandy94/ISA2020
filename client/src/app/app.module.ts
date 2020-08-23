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
import { CodebookComponent } from './admin/codebook/codebook.component';
import { NurseComponent } from './nurse/nurse.component';
import { PacientsComponent } from './component/nurce-page/pacients/pacients.component';
import { CalendarComponent } from './component/nurce-page/calendar/calendar.component';
import { RecepieComponent } from './component/nurce-page/recepie/recepie.component';
import { NurceProfileComponent } from './component/nurce-page/nurce-profile/nurce-profile.component';
import { DoctorComponent , ExaminationReportComponent} from './component/doctor-page';
import {FullCalendarModule} from '@fullcalendar/angular';
import { RegistrationRequestComponent } from './admin/registration-request/registration-request.component';
import { NewExaminationDialogComponent } from './component/doctor-page/examination-report/new-examination-dialog/new-examination-dialog.component';
import { NewOperationroomDialogComponent } from './component/doctor-page/examination-report/new-operationroom-dialog/new-operationroom-dialog.component';
import { NextVisitDialogComponent } from './component/doctor-page/examination-report/next-visit-dialog/next-visit-dialog.component';
import { DoctorCalendarComponent } from './component/doctor-page/doctor-calendar/doctor-calendar.component';
import { RegisteredUsersComponent } from './admin/registered-users/registered-users.component';

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
    CodebookComponent,
    NurseComponent,
    PacientsComponent,
    CalendarComponent,
    RecepieComponent,
    NurceProfileComponent,
    DoctorComponent,
    ExaminationReportComponent,
    RegistrationRequestComponent,
    NewExaminationDialogComponent,
    NewOperationroomDialogComponent,
    NextVisitDialogComponent,
    DoctorCalendarComponent,
    RegisteredUsersComponent,
    
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    FullCalendarModule, // for FullCalendar!
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
  entryComponents:[NewExaminationDialogComponent,NextVisitDialogComponent,NewOperationroomDialogComponent]
})
export class AppModule {
}
