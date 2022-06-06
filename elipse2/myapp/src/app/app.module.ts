import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NewDirective } from './newDirective';
import { ThirdDirective } from './thirdDirective';
import { AppComponent } from './app.component';
import { FahrenheitPipe } from './app.fahrenheitPipe';
import { PageDefault } from './app.pagedefault';
import { PageAComponent } from './app.page-a';
import { PageBComponent } from './app.page-b';
import { routing } from './app.routing';

@NgModule({
  declarations: [
    AppComponent, NewDirective, ThirdDirective, FahrenheitPipe,
    PageDefault, PageAComponent, PageBComponent
  ],
  imports: [
    BrowserModule, routing, FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
