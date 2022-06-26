import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faAngleDown } from '@fortawesome/free-solid-svg-icons';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  isAuthenticated: boolean = false;
  user?: User;
  faAngleDown = faAngleDown;
  isShowingLogout: boolean = false;

  constructor(private authService: AuthService, private router: Router) {
    this.isAuthenticated = this.authService.isAuthenticated();
    if (this.isAuthenticated) {
      this.user = this.authService.getUser()!;
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/login');
  }

  toggleButton() {
    this.isShowingLogout = !this.isShowingLogout;
  }
}
