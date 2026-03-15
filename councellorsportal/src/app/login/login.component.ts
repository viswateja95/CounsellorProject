import { CounsellorService } from './../services/counsellor.service';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Counsellor, LoginRequest } from '../model/counsellor.model';
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
      FormsModule,
      ReactiveFormsModule
    ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
    loginForm!: FormGroup;
    loading = false;
    submitted = false;
    errorMessage = '';

    constructor(
        private formBuilder: FormBuilder,
        private counsellorService: CounsellorService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.initializeForm();
    }

    initializeForm(): void {
        this.loginForm = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            pwd: ['', [Validators.required]]
        });
    }

    get f() {
        return this.loginForm.controls;
    }

    onSubmit(): void {
        this.submitted = true;
        this.errorMessage = '';

        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;
        const loginRequest: LoginRequest = this.loginForm.value;

        this.counsellorService.login(loginRequest).subscribe({
            next: (response) => {
                this.counsellorService.setCurrentCounsellor(response.data);
                this.router.navigate(['/home']);
            },
            error: (error) => {
                this.errorMessage = error.message || 'Login failed';
                this.loading = false;
            },
            complete: () => {
                this.loading = false;
            }
        });
    }
}
