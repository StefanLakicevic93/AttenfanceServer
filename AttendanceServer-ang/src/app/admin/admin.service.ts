import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  addProject(projectDto: any) : Observable<any> {
    return this.http.post(baseUrl + "/api/projects/add", projectDto);
  }

  getProjects(): Observable<any> {
    return this.http.get(baseUrl + "/api/projects/all");
  }

  addUser(dto: any): Observable<any> {
    return this.http.post(baseUrl + '/api/admin/create-user', dto);
  }

  getManagers(): Observable<any> {
    return this.http.get(baseUrl + "/api/admin/managers");
  }

  getEmployees(): Observable<any> {
    return this.http.get(baseUrl + "/api/admin/employees");
  }
}
