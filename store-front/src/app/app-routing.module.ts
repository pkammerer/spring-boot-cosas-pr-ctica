import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from 'app/login/login.component';


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: AppComponent}
];


export const routing = RouterModule.forRoot(routes)
export class AppRoutingModule { }
