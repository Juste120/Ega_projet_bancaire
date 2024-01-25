import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';

@Injectable({
 providedIn: 'root'
})
export class AuthService {
 private apiUrl = 'https://your-api-url.com'; // Replace with your API URL

 constructor(private http: HttpClient) { }

 login(credentials: { email: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials).pipe(
       tap((response: any) => {
          if (response && response.token) {//si y'a reponse et qu'il contient  un token alors
             localStorage.setItem('token', response.token);
             //tu le stoke dans le local storage du navigateur avec fonction setItem comme clé 'token' et valeur reponse.token
          }
       })
    );
 }
   //pipe methode of observable permet d'appliquer une serie  d'operation sur un observable,map,filter,
   /*
    tap()execute une fonction pour each valor emise par l'observable
    mais sans modifier la valeur elle meme
    'est utile lorsque vous voulez effectuer une action basée sur la valeur, mais que vous voulez toujours que l'Observable émette la même valeur. Dans votre cas, tap est utilisé pour exécuter une fonction qui stocke le jeton dans le localStorage chaque fois que la requête HTTP émet une réponse.
   */
 register(user: { lastname:string,email: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
 }

 logout(): Observable<any> {
     localStorage.removeItem('token');
     return of({ success: true });
 }
 }

