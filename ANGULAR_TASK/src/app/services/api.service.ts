import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http:HttpClient) { }

  getProjects():Observable<any>{
    return this.http.get('http://localhost:4500/projects');
  }
  getServices():Observable<any>{
    return this.http.get('http://localhost:4500/services');
  }
  getHome():Observable<any>{
    return this.http.get('http://localhost:4500/home');
  }
getAboutUs():Observable<any>{
  return this.http.get('http://localhost:4500/aboutUs');
}

getLogin():Observable<any>{
  return this.http.get('http://localhost:4500/login');
}

addEnquires(data:any):Observable<any>{
  return this.http.post('http://localhost:4500/enquires',data);
}

getEnquires():Observable<any>{
  return this.http.get('http://localhost:4500/enquires');
}


}
