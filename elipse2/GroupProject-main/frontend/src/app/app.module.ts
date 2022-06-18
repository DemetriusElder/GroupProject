import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { BlogListComponent } from './blog-list/blog-list.component';
import { BlogPreviewComponent } from './blog-preview/blog-preview.component';
import { BlogDetailComponent } from './blog-detail/blog-detail.component';
import { PostFormComponent } from './post-form/post-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DateAgoPipe } from './pipes/date-ago.pipe';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { BasicAuthInterceptorService } from './basic-auth-interceptor.service';
import { LogoutComponent } from './logout/logout.component';
import {NgxPaginationModule} from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    BlogListComponent,
    BlogPreviewComponent,
    BlogDetailComponent,
    PostFormComponent,
    DateAgoPipe,
    LoginComponent,
    LogoutComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      { path: 'home', component: BlogListComponent },
      { path: 'post', component: PostFormComponent },
      { path: 'blogs/:id', component: BlogDetailComponent },
      { path: 'login', component: LoginComponent },
    ]),
    FontAwesomeModule,
    HttpClientModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
