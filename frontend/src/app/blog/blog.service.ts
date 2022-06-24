import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ComponentFactoryResolver, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Blog } from './blog';
import { AuthUser } from './blog';

@Injectable({
  providedIn: 'root',
})
export class BlogService {
  private blogsUrl = 'http://localhost:8080/entries';
  private authusersUrl = 'http://localhost:8080/authusers';

  constructor(private http: HttpClient) {}

  getBlogs(): Observable<Blog[]> {
    return this.http.get<Blog[]>(this.blogsUrl);
  }

  getCount():Observable<number>{
    return this.http.get<number>(`${this.blogsUrl}/count`)
  }

  getPageBlogs(pagenumber: number): Observable<Blog[]> {
    console.log(pagenumber);
    return this.http.get<Blog[]>(`${this.blogsUrl}/pagelist/${pagenumber}`);
  }

  postBlog(blog: Blog): Observable<void> {
    return this.http.post<void>(this.blogsUrl, blog);
  }
  
  postAuthUsers(authuser: AuthUser): Observable<void>{
    return this.http.post<void>(this.authusersUrl, authuser);
  }

  updateEntries(blog : Blog): Observable<Blog>{
    return this.http.put<Blog>(this.blogsUrl, blog);

  }

  getBlogById(id: number): Observable<Blog> {
    return this.http.get<Blog>(`${this.blogsUrl}/${id}`);
  }

  deleteBlogById(id: number): Observable<void>{
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: id,
    };
    return this.http.delete<void>(`${this.blogsUrl}/delete/${id}`);
  }

  getFilteredBlogs(searchKey: String): Observable<Blog[]> {
    return this.http.get<Blog[]>(`${this.blogsUrl}/search/${searchKey}`);
  }

}
