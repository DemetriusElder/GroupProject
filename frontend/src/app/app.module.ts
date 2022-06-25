import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes, ExtraOptions } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { BlogListComponent } from './components/blog-list/blog-list.component';
import { BlogPreviewComponent } from './blog-preview/blog-preview.component';
import { BlogDetailComponent } from './blog-detail/blog-detail.component';
import { PostFormComponent } from './post-form/post-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DateAgoPipe } from './pipes/date-ago.pipe';
import { FormsModule } from '@angular/forms';
import { BasicAuthInterceptorService } from './basic-auth-interceptor.service';
import { LogoutComponent } from './logout/logout.component';
import { SearchListComponent } from './search-list/search-list.component';
import { RegisterFormComponent } from './register/register-form.component';
import { UpdateBlogComponent } from './update-blog/update-blog.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { LogoComponent } from './components/logo/logo.component';
import { FooterComponent } from './components/footer/footer.component';
import { LayoutComponent } from './components/layout/layout.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { SearchPageComponent } from './components/search-page/search-page.component';
import { ArticlePageComponent } from './components/article-page/article-page.component';
import { MyPageComponent } from './components/my-page/my-page.component';
import { AddPageComponent } from './components/add-page/add-page.component';

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
    SearchListComponent,
    RegisterFormComponent,
    UpdateBlogComponent,
    SignupComponent,
    LogoComponent,
    FooterComponent,
    LayoutComponent,
    HomePageComponent,
    SearchPageComponent,
    ArticlePageComponent,
    MyPageComponent,
    AddPageComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(
      [
        { path: 'login', component: LoginComponent },
        { path: 'signup', component: SignupComponent },
        {
          path: '',
          component: LayoutComponent,
          children: [
            { path: '', component: HomePageComponent },
            { path: 'search', component: SearchPageComponent },
            { path: 'add', component: AddPageComponent },
            { path: 'me', component: MyPageComponent },
            { path: 'blogs/:id', component: ArticlePageComponent },
          ],
        },
        { path: 'post', component: PostFormComponent },
        // { path: 'blogs/:id', component: BlogDetailComponent },
        { path: 'update', component: UpdateBlogComponent },
        {
          path: 'search/:searchKey',
          component: SearchListComponent,
          //canActivate: [AuthenticationGuard],
          runGuardsAndResolvers: 'paramsOrQueryParamsChange',
        },
      ],
      { onSameUrlNavigation: 'reload' }
    ),
    FontAwesomeModule,
    HttpClientModule,
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
