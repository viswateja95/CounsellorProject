import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { CounsellorService } from '../services/counsellor.service';

export const authGuard: CanActivateFn = (route, state) => {
  const counsellorService = inject(CounsellorService);
  const router = inject(Router);

  if (counsellorService.isLoggedIn()) {
    return true;
  }

  router.navigate(['/login']);
  return false;
};
