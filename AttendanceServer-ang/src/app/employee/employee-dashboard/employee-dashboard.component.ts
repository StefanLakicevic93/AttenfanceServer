import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { EmployeeServiceService } from '../employee.service.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { UserStorageService } from '../../auth/user-storage.service';

@Component({
  selector: 'app-employee-dashboard',
  imports: [SharedModule],
  templateUrl: './employee-dashboard.component.html',
  styleUrl: './employee-dashboard.component.scss'
})
export class EmployeeDashboardComponent {

  leaves: any;

  constructor(private service: EmployeeServiceService,
              private msg: NzMessageService
  ) {}

  ngOnInit() {
    this.getMyLeaves();
  }

  applyLeave() {
    const data = {
      employeeId: UserStorageService.getUserId(),
      projectId: UserStorageService.getUserProjectId()
    }
    this.service.applyLeave(data).subscribe(res => {
      this.msg.success(`Leave Applied Successfully`, {nzDuration: 5000})
    }, error => {
      this.msg.error(`${error.error}`, {nzDuration: 5000})
    })
  }

  getMyLeaves() {
    this.service.getMyLeaves().subscribe(res => {
      this.leaves = res;
      console.log(this.leaves);
    })
  }

}
