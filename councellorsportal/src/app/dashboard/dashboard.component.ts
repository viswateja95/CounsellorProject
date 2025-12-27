import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule,
    MatIconModule,],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {

  constructor(private router: Router) {

  }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  goToAddEnquiry(): void {
    this.router.navigate(['/add-enquiry']);
  }

  goToViewEnquiries(): void {
    this.router.navigate(['/view-enquiries']);
  }

}
