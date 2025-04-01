import { CanActivateFn } from '@angular/router';

export const enquiresGuard: CanActivateFn = (route, state) => {
  const username = localStorage.getItem('username');
  return username?true:false;
};
