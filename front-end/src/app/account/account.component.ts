import { Component } from '@angular/core';
import { User } from '../model/User';
import { HttpClient } from '@angular/common/http';
import { Profile } from '../model/Profile';
import { Network } from '../model/Network';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent {
  private username: string;
  private pwd: string;
  private createAccount: boolean;

  constructor(private readonly user: User, private http: HttpClient, private network: Network) {
    this.createAccount = false;
  }

  private updateLineMsgs() {
    this.user.profile.prefLines.forEach(linename => this.network.updateMessageOf(linename));
  }

  private loginOrCreate() {
    if (this.createAccount) {
      this.http
        .post(`go/user/new/${this.username}/${this.pwd}`, {}, {withCredentials: true})
        .subscribe(() => this.logInExistingUser());
    } else {
      this.logInExistingUser();
    }
  }

  private logInExistingUser() {
    const postData = new FormData();
    postData.append('username' , this.username);
    postData.append('password' , this.pwd);

    this.http
      .post('go/login', postData, {withCredentials: true})
      .subscribe(() => this.getProfile());
  }

  private getProfile() {
    this.http
      .get('go/profile/get', {withCredentials: true})
      .subscribe((profile: Profile) => {
        this.user.profile = profile;
        this.updateLineMsgs();
      });
  }

  private logout() {
    this.http
      .get('go/logout', {withCredentials: true})
      .subscribe(() => this.user.profile = undefined);
  }
}
