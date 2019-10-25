import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LinesComponent } from './lines.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { MatGridListModule, MatIconModule, MatBadgeModule, MatTabsModule } from '@angular/material';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { Network } from '../model/Network';
import { User } from '../model/User';

describe('LinesComponent', () => {
  let component: LinesComponent;
  let fixture: ComponentFixture<LinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LinesComponent ],
      imports: [
        BrowserModule,
        HttpClientModule,
        MatGridListModule,
        MatIconModule,
        MatBadgeModule,
        MatTabsModule,
        NoopAnimationsModule,
        AngularFontAwesomeModule
      ],
      providers: [Network, User]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
