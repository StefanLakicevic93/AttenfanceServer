import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserStorageService } from '../auth/user-storage.service';

const baseUrl = "http://localhost:8080/api";

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  constructor(private http: HttpClient) { }

  getEmployees() {
    const projectId = UserStorageService.getUserProjectId();
    return this.http.get(baseUrl + `/managers/employees/${projectId}`);
  }

  markAttendance(attendanceDto: any) {
    return this.http.post(baseUrl + '/attendance/add', attendanceDto);
  }
}
