import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { MatGridListModule, MatIconModule, MatBadgeModule, MatTabsModule } from '@angular/material';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { Network } from './model/Network';
import { User } from './model/User';
import { AccountComponent } from './account/account.component';
import { LinesComponent } from './lines/lines.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { path: '', component: AppComponent }
        ]),
        BrowserModule,
        HttpClientModule,
        MatGridListModule,
        MatIconModule,
        MatBadgeModule,
        MatTabsModule,
        NoopAnimationsModule,
        AngularFontAwesomeModule
      ],
      declarations: [
        AppComponent, LinesComponent, AccountComponent
      ],
      providers: [Network, User]
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'rennesgo'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('rennesgo');
  });
});
