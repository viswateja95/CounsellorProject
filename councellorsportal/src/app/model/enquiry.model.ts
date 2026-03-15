export interface Enquiry {
    enqId?: number;
    studentName: string;
    studentPhno: string;
    courseName: string;
    courseId?: number;
    classMode: string;
    enqStatus: string;
    counsellorId: number;
}

export interface EnquiryFilter {
    courseId?: number;
    classMode?: string;
    enqStatus?: string;
    studentName?: string;
}

export interface DashboardData {
    totalEnquiries: number;
    enrolledEnquiries: number;
    lostEnquiries: number;
    openEnquiries: number;
}
