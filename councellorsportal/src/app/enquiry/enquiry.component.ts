import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Enquiry } from '../model/enquiry.model';
import { EnquiryService } from '../services/enquiry.service';
import { CounsellorService } from '../services/counsellor.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';

@Component({
  selector: 'app-enquiry',
  standalone: true,
  imports: [CommonModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        MatToolbarModule,
        FormsModule,
      MatIconModule,
    MatSelectModule,
    ReactiveFormsModule,],
  templateUrl: './enquiry.component.html',
  styleUrl: './enquiry.component.scss'
})
export class EnquiryComponent implements OnInit {
   enquiryForm!: FormGroup;
    loading = false;
    submitted = false;
    errorMessage = '';
    successMessage = '';
    classModes = ['Online', 'Offline', 'Hybrid'];
    statuses = ['Open', 'Enrolled', 'Lost'];

    constructor(
        private formBuilder: FormBuilder,
        private enquiryService: EnquiryService,
        private counsellorService: CounsellorService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.initializeForm();
    }

    initializeForm(): void {
        const counsellorId = this.counsellorService.getCurrentCounsellorId();

        this.enquiryForm = this.formBuilder.group({
            studentName: ['', [Validators.required, Validators.minLength(3)]],
            studentPhno: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
            courseName: ['', Validators.required],
            classMode: ['', Validators.required],
            enqStatus: ['Open', Validators.required],
            counsellorId: [counsellorId, Validators.required]
        });
    }

    get f() {
        return this.enquiryForm.controls;
    }

    onSubmit(): void {
        this.submitted = true;
        this.errorMessage = '';
        this.successMessage = '';

        if (this.enquiryForm.invalid) {
            return;
        }

        this.loading = true;
        const enquiry: Enquiry = this.enquiryForm.value;

        this.enquiryService.addEnquiry(enquiry).subscribe({
            next: (response) => {
                this.successMessage = response.message;
                setTimeout(() => {
                    this.router.navigate(['/dashboard']);
                }, 2000);
            },
            error: (error) => {
                this.errorMessage = error.message || 'Failed to add enquiry';
                this.loading = false;
            },
            complete: () => {
                this.loading = false;
            }
        });
    }
}
