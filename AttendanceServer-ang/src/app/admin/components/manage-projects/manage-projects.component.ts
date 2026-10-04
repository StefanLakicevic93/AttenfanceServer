import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-manage-projects',
  imports: [SharedModule],
  templateUrl: './manage-projects.component.html',
  styleUrl: './manage-projects.component.scss'
})
export class ManageProjectsComponent {

  projectForm!: FormGroup;
  projects: any;

  constructor(private fb: FormBuilder,
              private service: AdminService,
              private msg: NzMessageService
  ) {}

  ngOnInit() {
    this.projectForm = this.fb.group({
      name: [null, [Validators.required]],
      duration: [null, [Validators.required]],
      startDate: [null, [Validators.required]],
    })
    this.getAllProjects();
  }

  addProject() {
    this.service.addProject(this.projectForm.value).subscribe((res) => {
      this.msg.success("Project posted successfully!", {nzDuration: 5000});
      this.projectForm.reset;
      this.getAllProjects();
    }, (error) => {
      this.msg.error("Error while posting a project!", {nzDuration: 5000});
    })
  }

  getAllProjects() {
    this.service.getProjects().subscribe(res => {
      this.projects = res;
      console.log(this.projects);
    })
  }

}
