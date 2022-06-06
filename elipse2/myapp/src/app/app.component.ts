import { Component } from '@angular/core';
@Component({
selector: 'app-root',
template:`<h1>This is the header</h1>
<nav>
<a routerLink="/page-a" routerLinkActive="active">A</a> |
<a routerLink="/page-b/5/bob" routerLinkActive="active">B</a>
</nav>
<!-- Where router should display a view -->
<router-outlet></router-outlet>`
})
export class AppComponent { }