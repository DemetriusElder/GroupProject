import { Component, Input, OnInit } from '@angular/core';
import { Blog } from '../blog/blog';

@Component({
  selector: 'app-blog-preview',
  templateUrl: './blog-preview.component.html',
  styleUrls: ['./blog-preview.component.css'],
})
export class BlogPreviewComponent implements OnInit {
  @Input() blog: Blog = {};

  constructor() {}

  ngOnInit(): void {}
}
