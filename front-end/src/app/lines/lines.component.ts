import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Network } from '../model/Network';
import { User } from '../model/User';
import { Profile } from '../model/Profile';
import { Line } from '../model/Line';

@Component({
  selector: 'app-lines',
  templateUrl: './lines.component.html',
  styleUrls: ['./lines.component.css']
})
export class LinesComponent implements OnInit {
  // To show whether the preferred lines or all the lines
  @Input()
  preferred: boolean;

  private nbColumns: number;

  constructor(private readonly http: HttpClient, private readonly network: Network, private readonly user: User) {
  }

  ngOnInit() {
    this.preferred = this.preferred !== undefined;
    this.nbColumns = this.computeNbColumns();
  }

  onResizeGrid() {
    this.nbColumns = this.computeNbColumns();
  }

  private computeNbColumns(): number {
    return (window.innerWidth / 110);
  }

  private isFav(line: string): boolean {
    return this.user.profile.prefLines.includes(line);
  }

  private getFavIconClasses(line: string): string {
    // changes the star character depending on whether it is a fav line
    return 'fa ' + (this.isFav(line) ? 'fa-star' : 'fa-star-o');
  }

  private fav(line: string) {
    if (this.isFav(line)) {
        // Un-fav the line
        this.http
          .put(`go/profile/lines/del/${line}`, {withCredentials: true})
          .subscribe((profile: Profile) => this.user.profile = profile);
      } else {
        // adds he line in the fav ones
        this.http
          .put(`go/profile/lines/new/${line}`, {withCredentials: true})
          .subscribe((profile: Profile) => {
            this.user.profile = profile;
            // Need to update the potential message that concern the line
            this.network.updateMessageOf(line);
          });
      }
  }

  private getLines(): Line[] {
    let ls;
    if (this.preferred) {
      if (this.user.profile === undefined) {
        return [];
      }

      ls = this.network.getLines().filter(l => this.user.profile.prefLines.includes(l.nomcourt));
    } else {
      ls = this.network.getLines();
    }

    return ls.sort((l1, l2) => l1.nomcourt < l2.nomcourt ? -1 : 1);
  }
}
