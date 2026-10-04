import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { UserStorageService } from '../user-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [SharedModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private service: AuthService,
              private msg: NzMessageService,
              private router: Router
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]]
    })
  }

  login() {
    this.service.login(this.loginForm.value).subscribe((res) => {
      UserStorageService.saveUser(res);

      if(UserStorageService.isAdminLoggedIn()) {
        this.router.navigateByUrl("/admin/dashboard");
      } else if(UserStorageService.isEmployeeLoggedIn()) {
        this.router.navigateByUrl('/employee/dashboard');
      } else if(UserStorageService.isManagerLoggedIn()) {
        this.router.navigateByUrl('/manager/dashboard');
      }
      console.log(res);
      this.msg.success("Login successful!", {nzDuration: 5000});
    }, (error) => {
      this.msg.error("Bad credentials!", {nzDuration: 5000});
    })
  }

}
