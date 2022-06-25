import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Blog } from '../models/blog';
import { Page } from '../models/page';

@Injectable({
  providedIn: 'root',
})
export class BlogService {
  private blogsUrl = 'http://localhost:8080/entries';

  constructor(private http: HttpClient) {}

  getBlogs(page: number, size: number): Observable<Page> {
    return this.http.get<Page>(`${this.blogsUrl}?page=${page}&size=${size}`);
  }

  searchBlogs(key: string, page: number, size: number): Observable<Page> {
    return this.http.get<Page>(
      `${this.blogsUrl}/search?key=${key}&page=${page}&size=${size}`
    );
  }

  getBlogById(id: number): Observable<Blog> {
    return this.http.get<Blog>(`${this.blogsUrl}/${id}`);
  }
}
