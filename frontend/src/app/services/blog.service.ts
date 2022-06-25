import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Blog } from '../models/blog';

@Injectable({
  providedIn: 'root',
})
export class BlogService {
  private blogsUrl = 'http://localhost:8080/entries';

  constructor(private http: HttpClient) {}

  getBlogs(page: number, size: number): Observable<any> {
    return this.http.get<Blog[]>(`${this.blogsUrl}?page=${page}&size=${size}`);
  }
}
