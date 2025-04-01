import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Enquiry } from '../../model/enquires';
@Component({
  selector: 'app-enquires',
  templateUrl: './enquires.component.html',
  styleUrl: './enquires.component.css'
})
export class EnquiresComponent {
enquires: Enquiry[] = [];

constructor(private router: Router, private enquiryService: ApiService) { }

ngOnInit(): void {
  this.enquiryService.getEnquires().subscribe((data) => {
    this.enquires = data;
  });
}
}
