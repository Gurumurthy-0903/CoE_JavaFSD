import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl:'./navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  status:boolean=false;
  loginmenu:string="Login"
     constructor(private router:Router){
       let username = localStorage.getItem("username")
        if(username){
          this.status=true;
          this.loginmenu=username+", Logout"
        }
     }

     loginhandler(){
        if(this.status){
          localStorage.removeItem("username")
          window.location.reload()
        }
        else{
          this.router.navigate(['/login'])
     }
}

}
