import { Profile } from './Profile';
import { Injectable } from '@angular/core';

@Injectable()
export class User {
    profile: Profile | undefined;
}
