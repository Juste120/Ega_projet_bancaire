import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot(routes),FormsModule,ReactiveFormsModule, HttpClientModule,],
  exports: [RouterModule]
})
export class AppRoutingModule { }
