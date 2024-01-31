
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
   providedIn: 'root'
})
export class AuthService {
   private apiUrl = 'https://your-api-url.com'; // Replace with your API URL

   constructor(private http: HttpClient, private router: Router) { }

   login(credentials: { email: string, password: string }): Observable<any> {
      return this.http.post(`${this.apiUrl} /login`, credentials).pipe(
         tap((response: any) => {
            if (response && response.token) {
               localStorage.setItem('token', response.token);
               this.router.navigate(['/menu-user']); // Navigate to user menu after successful login
            }
         })
      );
   }

   register(user: { lastname: string, email: string, password: string }): Observable<any> {
      return this.http.post(`${this.apiUrl}/register`, user).pipe(
         tap((response: any) => {
            if (response && response.success) {
               this.router.navigate(['/login']); // Navigate to login after successful registration
            }
         })
      );
   }

   logout(): Observable<any> {
      localStorage.removeItem('token');
      this.router.navigate(['/login']); // Navigate to login after logout
      return of({ success: true });
   }
}


