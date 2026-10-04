import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../auth/user-storage.service';

const baseUrl = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class EmployeeServiceService {

  constructor(private http: HttpClient) { }

  applyLeave(leaveDto: any): Observable<any> {
    return this.http.post(baseUrl + "/api/attendance/add/leave", leaveDto);
  }

  getMyLeaves(): Observable<any> {
    return this.http.get(baseUrl + "/api/attendance/leave/employee/" + UserStorageService.getUserId());
  }
}
