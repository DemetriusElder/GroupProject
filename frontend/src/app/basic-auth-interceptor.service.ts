// import {
//   HttpInterceptor,
//   HttpRequest,
//   HttpHandler,
//   HttpEvent,
//   HttpHeaders,
// } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root',
// })
// export class BasicAuthInterceptorService implements HttpInterceptor {
//   constructor(private authService: AuthService) {}

//   intercept(
//     req: HttpRequest<any>,
//     next: HttpHandler
//   ): Observable<HttpEvent<any>> {
//     if (
//       this.authenticationService.isUserLoggedIn() &&
//       req.url.indexOf('basicauth') === -1
//     ) {
//       const authReq = req.clone({
//         headers: new HttpHeaders({
//           'Content-Type': 'application/json',
//           Authorization: `Basic ${window.btoa(
//             this.authenticationService.username +
//               ':' +
//               this.authenticationService.password
//           )}`,
//         }),
//       });
//       return next.handle(authReq);
//     } else {
//       return next.handle(req);
//     }
//   }
// }
