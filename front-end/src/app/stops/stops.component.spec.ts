import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StopsComponent } from './stops.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { MatGridListModule, MatIconModule, MatBadgeModule, MatTabsModule } from '@angular/material';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { Network } from '../model/Network';
import { User } from '../model/User';
import { LinesComponent } from '../lines/lines.component';
import { AccountComponent } from '../account/account.component';

describe('StopsComponent', () => {
  let component: StopsComponent;
  let fixture: ComponentFixture<StopsComponent>;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StopsComponent ],
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
    fixture = TestBed.createComponent(StopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
