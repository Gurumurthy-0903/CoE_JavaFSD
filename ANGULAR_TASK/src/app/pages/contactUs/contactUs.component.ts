import { Component } from '@angular/core';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-contactUs',
  templateUrl:'./contactUs.component.html',
  styleUrls: ['./contactUs.component.css']
})
export class ContactUsComponent 
{
  name: string = '';
    email: string = '';
    phone: string = '';
    message: string = '';
    errors:string[]=[]
   
   
    constructor(private api: ApiService) {}
   
    submitForm() {
      this.errors=[]
      let emailPattern = /^[a-zA-Z0-9\._]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$/;
      if(!this.name || this.name.length<3){
        this.errors.push("Name should be atleast three characters long")
      }
 
      if(this.errors.length==0){
        this.api.addEnquires({
          name: this.name,
          email: this.email,
          phone: this.phone,
          message: this.message
        }).subscribe({
            next: (response) => {
              console.log("Enquiry Form Response:", response);
              this.name = '';
              this.email = '';
              this.phone = '';
              this.message = '';
              alert("Enquiry Submitted Successfully")
            },
            error: (error) => {
              alert("Something went wrong. Please try again")
           }});
      }
   
    }

}
