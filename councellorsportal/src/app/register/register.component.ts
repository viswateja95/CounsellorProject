import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
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
    ReactiveFormsModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
    loading = false;
    submitted = false;
    errorMessage = '';
    successMessage = '';

    constructor(
        private formBuilder: FormBuilder,
        private counsellorService: CounsellorService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.initializeForm();
    }

    initializeForm(): void {
        this.registerForm = this.formBuilder.group({
            name: ['', [Validators.required, Validators.minLength(3)]],
            email: ['', [Validators.required, Validators.email]],
            phno: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
            pwd: ['', [Validators.required, Validators.minLength(6)]]
        });
    }

    get f() {
        return this.registerForm.controls;
    }

    onSubmit(): void {
        this.submitted = true;
        this.errorMessage = '';
        this.successMessage = '';

        if (this.registerForm.invalid) {
            return;
        }

        this.loading = true;
        const counsellor: Counsellor = this.registerForm.value;

        this.counsellorService.register(counsellor).subscribe({
            next: (response) => {
                this.successMessage = response.message;
                this.counsellorService.setCurrentCounsellor(response.data);
                setTimeout(() => {
                    this.router.navigate(['/dashboard']);
                }, 2000);
            },
            error: (error) => {
                this.errorMessage = error.message || 'Registration failed';
                this.loading = false;
            },
            complete: () => {
                this.loading = false;
            }
        });
    }
}
