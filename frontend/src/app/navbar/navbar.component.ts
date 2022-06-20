import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faPlus, faSearch } from '@fortawesome/free-solid-svg-icons';
import { AuthenticationService } from '../login/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  faPlus = faPlus;
  isLoggedIn = false;
  faSearch = faSearch;
  public userInput!: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    console.log('menu ->' + this.isLoggedIn);
  }

  handleLogout() {
    this.authenticationService.logout();
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
  }
  onSearch() {
    console.log(this.userInput);

    this.router.navigate(['search/', this.userInput]);
    this.userInput = '';
  }
}
