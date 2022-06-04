import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { BlogListComponent } from './blog-list/blog-list.component';
import { BlogPreviewComponent } from './blog-preview/blog-preview.component';
import { BlogDetailComponent } from './blog-detail/blog-detail.component';
import { PostFormComponent } from './post-form/post-form.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    BlogListComponent,
    BlogPreviewComponent,
    BlogDetailComponent,
    PostFormComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      { path: '', component: BlogListComponent },
      { path: 'blogs/:id', component: BlogDetailComponent },
      { path: 'post', component: PostFormComponent },
    ]),
    FontAwesomeModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
