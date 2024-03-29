import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {DisplayMessage} from '../shared/models/display-message';
import {AuthService, UserService} from '../service';
import {Subject} from 'rxjs/Subject';
import {takeUntil} from 'rxjs/operators';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit, OnDestroy {
  title = 'Sign up';
  form: FormGroup;

  /**
   * Boolean used in telling the UI
   * that the form has been submitted
   * and is awaiting a response
   */
  submitted = false;

  /**
   * Notification message from received
   * form request or router
   */
  notification: DisplayMessage;

  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {

  }
  authList = ['Korsnik', 'Doktor', 'Medicinska sestra', 'Administrator'];
  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      firstname: [''],
      lastname: [''],
      email: [''],
      role:['']
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }
  
  onSubmit() {
    /**
     * Innocent until proven guilty
     */
    this.notification = undefined;
    this.submitted = true;
    
    this.authService.signup(this.form.value)
      .subscribe(data => {
          console.log(data);
          this.authService.login(this.form.value).subscribe(() => {
            this.userService.getMyInfo().subscribe();
          });
            this.router.navigate([this.returnUrl]);
        },
        error => {
          this.submitted = false;
          console.log('Sign up error');
          this.notification = {msgType: 'error', msgBody: error['error'].message};
        });

  }


}
