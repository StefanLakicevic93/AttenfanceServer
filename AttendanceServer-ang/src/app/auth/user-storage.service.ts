import { Injectable } from '@angular/core';

const  USER = "user";

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

  static saveUser(user: any) {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }

  static getUserId(): string {
    const user = this.getUser();

    if(user == null) {
      return "";
    }
    return user.id;
  }

  static getUserProjectId(): string {
    const user = this.getUser();

    if(user == null) {
      return "";
    }
    return user.projectId;
  }

  static getUserRole(): string {
    const user = this.getUser();

    if(user == null) {
      return "";
    }
    return user.userRole;
  }

  static isAdminLoggedIn(): boolean {

    if(this.getUserRole() == "") {
      return false;
    }
    const role: string = this.getUserRole();
    return role == "ADMIN";
  }

  static isManagerLoggedIn(): boolean {

    if(this.getUserRole() == "") {
      return false;
    }
    const role: string = this.getUserRole();
    return role == "MANAGER";
  }

  static isEmployeeLoggedIn(): boolean {

    if(this.getUserRole() == "") {
      return false;
    }
    const role: string = this.getUserRole();
    return role == "EMPLOYEE";
  }

  static signout(): void {
    window.localStorage.removeItem(USER);
  }

}
