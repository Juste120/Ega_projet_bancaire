import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
 providedIn: 'root'
})
export class AuthService {
 private apiUrl = 'https://your-api-url.com'; // Replace with your API URL

 constructor(private http: HttpClient) { }

 login(credentials: { email: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials);
 }

 register(user: { email: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
 }

 logout(): Observable<any> {
    
    return of({ success: true });
 }
}
