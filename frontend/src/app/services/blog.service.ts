import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Blog, BlogDto } from '../models/blog';
import { Page } from '../models/page';

@Injectable({
  providedIn: 'root',
})
export class BlogService {
  private readonly BLOG_URL = 'http://localhost:8080/entries';

  constructor(private http: HttpClient) {}

  getBlogs(page: number, size: number): Observable<Page> {
    return this.http.get<Page>(`${this.BLOG_URL}?page=${page}&size=${size}`);
  }

  searchBlogs(key: string, page: number, size: number): Observable<Page> {
    return this.http.get<Page>(
      `${this.BLOG_URL}/search?key=${key}&page=${page}&size=${size}`
    );
  }

  getBlogById(id: number): Observable<Blog> {
    return this.http.get<Blog>(`${this.BLOG_URL}/${id}`);
  }

  addBlog(blogDto: BlogDto, authToken: string): Observable<void> {
    return this.http.post<void>(
      `${this.BLOG_URL}`,
      {
        ...blogDto,
      },
      {
        headers: {
          authorization: authToken,
        },
      }
    );
  }

  deleteBlog(
    username: string,
    authToken: string,
    id: number
  ): Observable<void> {
    return this.http.delete<void>(`${this.BLOG_URL}/${id}`, {
      headers: {
        authorization: authToken,
      },
      body: {
        username,
      },
    });
  }

  updateBlog(blogDto: BlogDto, authToken: string, id: number) {
    return this.http.put<void>(
      `${this.BLOG_URL}/${id}`,
      {
        ...blogDto,
      },
      {
        headers: {
          authorization: authToken,
        },
      }
    );
  }
}
