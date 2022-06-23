import { Injectable } from '@angular/core';

@Injectable({
  // declares that this service should be created
  // by the root application injector.
  providedIn: 'root',
})
export class StoreUser {
    username: any;
setUsername(username: string) {      
        this.username = username;  
      } 
  getUsername() { return this.username; }
}