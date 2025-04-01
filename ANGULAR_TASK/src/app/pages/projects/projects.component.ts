import { Component } from '@angular/core';
import { Project } from '../../model/project';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.css'
})
export class ProjectsComponent {

  projects: Project[] = [];

  constructor(private router:Router,private projectservice:ApiService) { }

  ngOnInit(): void {
    this.projectservice.getProjects().subscribe((data)=>{
      this.projects = data;
    });
  }
}
