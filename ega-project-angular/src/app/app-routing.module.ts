import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MenuUserComponent } from './menu-user/menu-user.component';
import { TransactionComponent } from './transaction/transaction.component';

const routes: Routes = [
 { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'menu-user', component: MenuUserComponent },
  { path: 'transaction', component: TransactionComponent },
  {path: 'koffi',component:TransactionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes),FormsModule,ReactiveFormsModule, HttpClientModule,],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  
}
