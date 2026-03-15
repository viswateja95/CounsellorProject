import { DashboardComponent } from './dashboard/dashboard.component';
import { HeaderComponent } from './header/header.component';
import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { EnquiryComponent } from './enquiry/enquiry.component';
import { ViewenquiryComponent } from './viewenquiry/viewenquiry.component';
import { authGuard } from './guard/auth.guard';

export const routes: Routes = [
  { path: 'home', component: DashboardComponent, canActivate: [authGuard] },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'add-enquiry', component: EnquiryComponent, canActivate: [authGuard] },
  { path: 'view-enquiries', component: ViewenquiryComponent, canActivate: [authGuard] },

  { path: '', redirectTo: 'login', pathMatch: 'full' }
];
