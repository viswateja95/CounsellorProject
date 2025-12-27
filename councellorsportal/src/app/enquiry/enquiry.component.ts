import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Enquiry } from '../model/enquiry.model';
import { EnquiryServiceService } from '../services/enquiry.service';
import { CounsellorService } from '../services/counsellor.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
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
    MatSelectModule],
  templateUrl: './enquiry.component.html',
  styleUrl: './enquiry.component.scss'
})
export class EnquiryComponent implements OnInit {
  enquiry: Enquiry = {
    studentName: '',
    studentPhno: '',
    courseName: '',
    classMode: '',
    enqStatus: 'Open',
    counsellorId: localStorage.getItem("counsellorId") ? Number(localStorage.getItem("counsellorId")) : undefined
  };

  courses = ['Java Full Stack', 'Python Full Stack', 'MERN Stack', 'Data Science', 'DevOps'];
  classModes = ['Online', 'Offline', 'Hybrid'];
  statuses = ['Open', 'Enrolled', 'Lost'];
  errorMessage = '';

  constructor(private enquiryService: EnquiryServiceService,
    private counsellorService: CounsellorService,
    private router: Router) { }


    ngOnInit(){
      const currentCounsellor = this.counsellorService.getCurrentCounsellor();
      if(currentCounsellor){
        this.enquiry.counsellorId = currentCounsellor.counsellorId;
      }
    }

    onSubmit(){
      this.enquiryService.addEnquiry(this.enquiry).subscribe({
        next: (response) =>{
          this.router.navigate(['/view-enquiries']);
        },error: (error) =>{
          this.errorMessage = 'Failed to add enquiry.';
          console.error('Error adding enquiry:', error);
        }
      })

    }

    goBack(){
      this.router.navigate(['/dashboard']);
    }
}
