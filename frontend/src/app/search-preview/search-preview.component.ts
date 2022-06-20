import { Component, Input, OnInit } from '@angular/core';
import { Blog } from '../blog/blog';

@Component({
  selector: 'app-search-preview',
  templateUrl: './search-preview.component.html',
  styleUrls: ['./search-preview.component.css'],
})
export class SearchPreviewComponent implements OnInit {
  @Input() blog: Blog = {};

  constructor() {}

  ngOnInit(): void {}
}
