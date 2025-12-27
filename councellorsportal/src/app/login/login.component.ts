import { CounsellorService } from './../services/counsellor.service';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormsModule } from '@angular/forms';
import { LoginRequest } from '../model/counsellor.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ CommonModule,
      MatCardModule,
      MatFormFieldModule,
      MatInputModule,
      MatButtonModule,
      MatToolbarModule,
      FormsModule,],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginRequest: LoginRequest = {
    email: '',
    pwd: ''
  };
  errorMessage = '';

  constructor(private counsellorService: CounsellorService,
    private router: Router
  ) { }
  onLogin() {
    this.counsellorService.onLogin(this.loginRequest.email, this.loginRequest.pwd).subscribe({
      next: (response) => {
        console.log("Login successful", response);
        this.router.navigate(['/home']);
      },
      error: (error) => {
        this.errorMessage = 'Login failed. Please check your credentials.';
      }
    });
  }
  goToRegister(){
    this.router.navigate(['/register']);
  }
}
