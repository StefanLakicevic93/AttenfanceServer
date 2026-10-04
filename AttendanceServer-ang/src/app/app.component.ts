import { Component } from '@angular/core';
import { SharedModule } from './shared/shared.module';
import { UserStorageService } from './auth/user-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [SharedModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  
  isAdminLoggedIn = UserStorageService.isAdminLoggedIn();
  isManagerLoggedIn = UserStorageService.isManagerLoggedIn();
  isEmployeeLoggedIn = UserStorageService.isEmployeeLoggedIn();

  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe(events => {
      this.isAdminLoggedIn = UserStorageService.isAdminLoggedIn();
      this.isManagerLoggedIn = UserStorageService.isManagerLoggedIn();
      this.isEmployeeLoggedIn = UserStorageService.isEmployeeLoggedIn();
    })
  }

  logout() {
    UserStorageService.signout();
    this.router.navigateByUrl('/');
  }

}
