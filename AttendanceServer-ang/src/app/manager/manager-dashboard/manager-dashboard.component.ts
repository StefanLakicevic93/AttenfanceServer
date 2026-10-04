import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { ManagerService } from '../manager.service';
import { UserStorageService } from '../../auth/user-storage.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-manager-dashboard',
  imports: [SharedModule],
  templateUrl: './manager-dashboard.component.html',
  styleUrl: './manager-dashboard.component.scss'
})
export class ManagerDashboardComponent {

  employeeDetails: any;

  constructor(private service: ManagerService,
              private msg: NzMessageService
  ) {}

  ngOnInit() {
    this.getEmployees();
  }

  getEmployees() {
    this.service.getEmployees().subscribe(res => {
      this.employeeDetails = res;
      console.log(this.employeeDetails);
    })
  }

  markAttendance(type: string, employeeId: number, projectId: number) {
    const data = {
      employeeId: employeeId,
      managerId: UserStorageService.getUserId(),
      projectId: projectId,
      attendanceStatus: type
    }
    this.service.markAttendance(data).subscribe(res => {
      this.msg.success('Attendace Marked Successfully', {nzDuration: 5000});
    }, error => {
      this.msg.error(`${error.error}`, {nzDuration: 5000});
    })
  }

}
