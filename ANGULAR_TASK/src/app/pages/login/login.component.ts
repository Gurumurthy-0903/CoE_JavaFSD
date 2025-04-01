import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent
{
   username: string = '';
   password: string = '';
    login(){
       
          if(this.username=="admin" && this.password
          =="admin"){
        
            localStorage.setItem("username",this.username)
            window.location.reload()
        }
        else alert("Invalid Credentials")
      }
}