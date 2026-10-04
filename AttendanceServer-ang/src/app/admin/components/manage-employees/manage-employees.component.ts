import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-manage-employees',
  imports: [SharedModule],
  templateUrl: './manage-employees.component.html',
  styleUrl: './manage-employees.component.scss'
})
export class ManageEmployeesComponent {

  employeeForm!: FormGroup;
  projects: any;
  employees: any;

  constructor(private fb: FormBuilder,
              private service: AdminService,
              private msg: NzMessageService
  ) {}

  ngOnInit() {
    this.employeeForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required]],
      projectId: [null, [Validators.required]],
      password: [null, [Validators.required]]
    });
    this.getAllProjects();
    this.getEmployees();
  }

  getAllProjects() {
    this.service.getProjects().subscribe(res => {
      this.projects = res;
      console.log(this.projects);
    })
  }

  submitForm() {
    const data = this.employeeForm.value;
    data.userRole = 'EMPLOYEE';

    this.service.addUser(data).subscribe(res => {
      this.msg.success('Employee created successfully', {nzDuration: 5000});
      this.employeeForm.reset;
      this.getEmployees();
    }, error => {
      this.msg.error(error.error, {nzDuration: 5000});
    })
  }

  getEmployees() {
    this.service.getEmployees().subscribe(res => {
      this.employees = res;
    })
  }

  

}
