export interface Counsellor {
  counsellorId?: number;
  name: string;
  email: string;
  pwd: string;
  phno: string;
}

export interface LoginRequest {
  email: string;
  pwd: string;
}
