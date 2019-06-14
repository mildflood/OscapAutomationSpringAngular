import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppformComponent } from './appform.component';

describe('AppformComponent', () => {
  let component: AppformComponent;
  let fixture: ComponentFixture<AppformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
