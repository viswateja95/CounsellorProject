import { DashboardComponent } from './dashboard/dashboard.component';
import { HeaderComponent } from './header/header.component';
import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { EnquiryComponent } from './enquiry/enquiry.component';
import { ViewenquiryComponent } from './viewenquiry/viewenquiry.component';

export const routes: Routes = [
  { path: 'home', component: DashboardComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'add-enquiry', component: EnquiryComponent },
  { path: 'view-enquiries', component: ViewenquiryComponent },
  // { path: 'dashboard', component: DashboardComponent },

  { path: '', redirectTo: 'home', pathMatch: 'full' }
];
