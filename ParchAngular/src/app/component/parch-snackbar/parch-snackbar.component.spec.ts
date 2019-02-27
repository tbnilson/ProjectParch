import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParchSnackbarComponent } from './parch-snackbar.component';

describe('ParchSnackbarComponent', () => {
  let component: ParchSnackbarComponent;
  let fixture: ComponentFixture<ParchSnackbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParchSnackbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParchSnackbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
