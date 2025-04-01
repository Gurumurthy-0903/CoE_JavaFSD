import { Component } from '@angular/core';
import { aboutUs } from '../../model/aboutUs'; // Fixed typo in import

import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css'] // Fixed typo in styleUrls
})
export class AboutUsComponent {
  aboutUs: aboutUs[] = []; // Renamed property to 'aboutUs'

  constructor(private aboutusservice: ApiService) { }

  ngOnInit(): void {
    this.aboutusservice.getAboutUs().subscribe((data) => {
      this.aboutUs = data; // Updated assignment to 'aboutUs'
    });
  }
}
