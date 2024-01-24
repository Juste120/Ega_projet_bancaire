import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { AuthService } from '../auth.service'; // Assurez-vous que le chemin vers votre service Auth est correct

@Component({
 selector: 'app-login',
 templateUrl: './login.component.html',
 styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 loginForm: FormGroup;

 constructor(private fb: FormBuilder, private authService: AuthService) {
    this.loginForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
 }

 ngOnInit(): void {}

 onSubmit() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
       next: (res) => {
          console.log(res);
          // Handle successful login here
          alert('Connexion reussie');
       },
       error: (err) => {
          console.error(err);
          alert('Une erreur s\'est produite lors de la connexion. Veuillez réessayer.');
       }
      });
    }
 }
}