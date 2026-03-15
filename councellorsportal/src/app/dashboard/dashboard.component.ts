import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Counsellor, DashboardStats } from '../model/counsellor.model';
import { Enquiry } from '../model/enquiry.model';
import { CounsellorService } from '../services/counsellor.service';
import { EnquiryService } from '../services/enquiry.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, MatIconModule, RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent implements OnInit {
  currentCounsellor!: Counsellor | null;
    dashboardStats: DashboardStats | null = null;
    recentEnquiries: Enquiry[] = [];
    loading = true;
    errorMessage = '';

    constructor(
        private counsellorService: CounsellorService,
        private enquiryService: EnquiryService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.currentCounsellor = this.counsellorService.getCurrentCounsellor();

        if (this.currentCounsellor && this.currentCounsellor.counsellorId) {
            this.loadDashboard(this.currentCounsellor.counsellorId);
            this.loadRecentEnquiries(this.currentCounsellor.counsellorId);
        }
    }

    /**
     * Load dashboard statistics
     */
    loadDashboard(counsellorId: number): void {
        this.counsellorService.getDashboard(counsellorId).subscribe({
            next: (response) => {
                this.dashboardStats = response.data;
            },
            error: (error) => {
                this.errorMessage = error.message || 'Failed to load dashboard';
            }
        });
    }

    /**
     * Load recent enquiries (only 5 for dashboard preview)
     */
    loadRecentEnquiries(counsellorId: number): void {
        this.enquiryService.getEnquiriesByCounsellor(counsellorId).subscribe({
            next: (response) => {
                this.recentEnquiries = response.data.slice(0, 5);
                this.loading = false;
            },
            error: (error) => {
                this.errorMessage = error.message || 'Failed to load enquiries';
                this.loading = false;
            }
        });
    }

    /**
     * Navigate to Add Enquiry component
     */
    goToAddEnquiry(): void {
        this.router.navigate(['/add-enquiry']);
    }

    /**
     * Navigate to View Enquiries (List) component
     */
    goToViewEnquiries(): void {
        this.router.navigate(['/view-enquiries']);
    }

    /**
     * Logout
     */
    logout(): void {
        this.counsellorService.logout();
        this.router.navigate(['/login']);
    }

    /**
     * Get status badge class
     */
    getStatusClass(status: string): string {
        switch(status) {
            case 'Open': return 'badge bg-info';
            case 'Enrolled': return 'badge bg-success';
            case 'Lost': return 'badge bg-danger';
            default: return 'badge bg-secondary';
        }
    }

    /**
     * Get mode badge class
     */
    getModeClass(mode: string): string {
        switch(mode) {
            case 'Online': return 'badge bg-primary';
            case 'Offline': return 'badge bg-warning';
            case 'Hybrid': return 'badge bg-secondary';
            default: return 'badge bg-light';
        }
    }
}
