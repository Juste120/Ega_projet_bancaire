import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service'; // Assurez-vous que le chemin d'importation est correct
import { Router } from '@angular/router';

@Component({
 selector: 'app-register',
 templateUrl: './register.component.html',
 styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
 registerForm: FormGroup;

 constructor(private formBuilder: FormBuilder, private authService: AuthService,private router: Router,) { 
 this.registerForm = this.formBuilder.group({});
 }

 ngOnInit(): void {
    this.registerForm = this.formBuilder.group({   
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
 }

 onSubmit(): void {
    if (this.registerForm.valid) {
        const user = this.registerForm.value;
        this.authService.register(user).subscribe({
         next: (response) => {
             console.log(response);
           alert("connexion reussie depuis wils banking")
         },
         error: (error) => {
             console.error(error);
             alert("erreur d'inscription wils banking")
             
         },complete:()=>{
            console.log('Registraction completed,attendez 5 secondes...');
            setTimeout(() => {
               this.router.navigateByUrl('/login');
           }, 5000);
         }
     });
    }
 }
}