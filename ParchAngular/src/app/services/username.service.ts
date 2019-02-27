import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsernameService {

  private usernameSource = new BehaviorSubject<string>("");
  currentUsername = this.usernameSource.asObservable();

  constructor() { }

  changeUsername(user: string){
    this.usernameSource.next(user);
  }
}
