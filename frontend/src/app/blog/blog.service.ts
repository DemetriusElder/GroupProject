import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Blog } from './blog';
import { AuthUser } from './blog';

@Injectable({
  providedIn: 'root',
})
export class BlogService {
  private blogsUrl = 'http://localhost:8080/entries';
  private authusersUrl = 'http://localhost:8080/authusers'

  constructor(private http: HttpClient) {}

  getBlogs(): Observable<Blog[]> {
    return this.http.get<Blog[]>(this.blogsUrl);
  }

  postBlog(blog: Blog): Observable<void> {
    return this.http.post<void>(this.blogsUrl, blog);
  }
  
  postAuthUsers(authuser: AuthUser): Observable<void>{
    return this.http.post<void>(this.authusersUrl, authuser);
  }

  getBlogById(id: string): Observable<Blog> {
    return this.http.get<Blog>(`${this.blogsUrl}/${id}`);
  }

  getFilteredBlogs(searchKey: String): Observable<Blog[]> {
    return this.http.get<Blog[]>(`${this.blogsUrl}/search/${searchKey}`);
  }

}
