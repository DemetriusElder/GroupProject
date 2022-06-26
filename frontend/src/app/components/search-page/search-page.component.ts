import { Component, OnChanges, SimpleChanges } from '@angular/core';
import { BlogService } from 'src/app/services/blog.service';
import { Blog } from 'src/app/models/blog';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css'],
})
export class SearchPageComponent {
  faSearch = faSearch;
  blogs: Blog[] = [];
  page: number = 0;
  size: number = 9;
  last: boolean = false;
  key: string = '';

  constructor(private blogService: BlogService) {}

  onInputChange() {
    this.page = 0;
    if (!this.key) {
      this.blogs = [];
      return;
    }
    this.blogService
      .searchBlogs(this.key, this.page, this.size)
      .subscribe(({ content, last }) => {
        this.blogs = content;
        this.last = last;
      });
  }

  onFetchMore() {
    this.page++;
    this.blogService
      .searchBlogs(this.key, this.page, this.size)
      .subscribe(({ content, last }) => {
        this.blogs = [...this.blogs, ...content];
        this.last = last;
      });
  }
}
