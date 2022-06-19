import { Component, Input, OnInit } from '@angular/core';
import {
  Router,
  ActivatedRoute,
  ParamMap,
  NavigationEnd,
} from '@angular/router';
import { Subscription } from 'rxjs';
import { Blog } from '../blog/blog';
import { BlogService } from '../blog/blog.service';

@Component({
  selector: 'app-search-list',
  templateUrl: './search-list.component.html',
  styleUrls: ['./search-list.component.css'],
})
export class SearchListComponent implements OnInit {
  searchKey = '';
  blogs: Blog[] = [];
  navigationSub!: Subscription;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private blogservice: BlogService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((param) => {
      this.searchKey = param['searchKey'];
    });

    this.navigationSub = this.router.events.subscribe((e: any) => {
      if (e instanceof NavigationEnd) {
        this.resetSearch();
      }
    });
    //this.searchKey = this.route.snapshot.params['userInput'];
    this.getFilteredBlogs(this.searchKey);
  }

  resetSearch() {
    this.route.params.subscribe((param) => {
      console.log(param);
      this.searchKey = param['searchKey'];
    });
    this.getFilteredBlogs(this.searchKey);
  }

  getFilteredBlogs(searchKey: string) {
    console.log(this.searchKey);
    this.blogservice
      .getFilteredBlogs(searchKey)
      .subscribe((blogs) => (this.blogs = blogs));
  }
}
