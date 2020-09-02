import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeniedUsersComponent } from './denied-users.component';

describe('DeniedUsersComponent', () => {
  let component: DeniedUsersComponent;
  let fixture: ComponentFixture<DeniedUsersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeniedUsersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeniedUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
