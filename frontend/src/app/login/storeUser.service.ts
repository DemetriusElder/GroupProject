import { Injectable } from '@angular/core';
import { Blog } from '../blog/blog';

@Injectable({
  // declares that this service should be created
  // by the root application injector.
  providedIn: 'root',
})
export class StoreUser {
    username: any;
    blog?: Blog;
  setUsername(username: string) {      
        this.username = username;  
      } 
  getUsername() { return this.username; }

  setBlog(blog: Blog){
    this.blog = blog;
  }
  getBlog() {return this.blog;}
}