import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormsModule } from '@angular/forms';
import { Counsellor } from '../model/counsellor.model';
import { CounsellorService } from '../services/counsellor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatToolbarModule,
    FormsModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {
  counsellor: Counsellor = {
    name: '',
    email: '',
    pwd: '',
    phno: '',
  };
  errorMessage = '';
  successMessage = '';

  constructor(
    private counsellorService: CounsellorService,
    private router: Router
  ) {}

  onRegister() {
    this.counsellorService.register(this.counsellor).subscribe({
      next: (response) => {
        this.successMessage =
          'Registration successful! Redirecting to login...';
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error) => {
        this.errorMessage = 'Registration failed. Please try again.';
      },
    });
  }
  goToLogin() {
    this.router.navigate(['/login']);
  }
}
