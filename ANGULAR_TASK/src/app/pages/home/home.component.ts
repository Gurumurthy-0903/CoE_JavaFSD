import { Component } from '@angular/core';
import { Home } from '../../model/home';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  home: Home[] = [];
  constructor(private router:Router,private homeservice:ApiService) { }
  
    ngOnInit(): void {
      this.homeservice.getProjects().subscribe((data)=>{
        this.home = data;
      });
    }
}
