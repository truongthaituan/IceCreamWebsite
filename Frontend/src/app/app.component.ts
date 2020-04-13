import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth-service/auth.service';
import { Recipe } from './services/recipe-service/recipe.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private _router: Router, private authService: AuthService) { }
  title = 'frontend';
  customOptions: any = {
    loop: true,
    mouseDrag: false,
    touchDrag: false,
    pullDrag: false,
    dots: false,
    navSpeed: 700,
    navText: ['', ''],
    responsive: {
      0: {
        items: 1
      },
      400: {
        items: 2
      },
      740: {
        items: 3
      },
      940: {
        items: 4
      }
    },
    nav: true
  }
  isLoggedIn: Boolean = false
  roles: string[] = []
  userName = localStorage.getItem('userName');
  statusLogin = localStorage.getItem('statusLogin');
  CartRecipe: Array<Recipe>

  ngOnInit() {
    var cartInfo = document.getElementById('cart-info');
    var cart = document.getElementById('cart');
    cartInfo.addEventListener("click", function () {
      cart.classList.toggle("show-cart");
    });
    this.authService.authInfo.subscribe(val => {
      this.isLoggedIn = val.loggedIn;
      this.roles = val.roles;
    });
    // s
  }

  moveToLogin() {
    return this._router.navigate(['/login']);
  }
  moveToAdminCustomer() {
    return this._router.navigate(['/manageCustomer']);
  }
  moveToAdminUser() {
    return this._router.navigate(['/manageUser']);
  }
  moveToAdminRecipe() {
    return this._router.navigate(['/manageRecipe']);
  }
  moveToAdminOrder() {
    return this._router.navigate(['/manageOrder']);
  }

  moveToOrderIcecream() {
    return this._router.navigate(['/orderIcecream']);
  }
  moveToCartRecipe() {
    return this._router.navigate(['/cartRecipe']);
  }
  
  moveToProfile(userName) {
    return this._router.navigate(['/customerProfile' + `/${userName}`]);
  }
  moveToUserProfile(userName) {
    return this._router.navigate(['/userProfile' + `/${userName}`]);
  }
  moveToOrderHistory() {
    return this._router.navigate(['/orderHistory']);
  }
  moveToIcecream() {
    return this._router.navigate(['/orderIcecream']);
  }

  moveToAdminFeedback() {
    return this._router.navigate(['/manageFeedback']);
  }
  moveToHomePage() {
    return this._router.navigate(['/']);
  }
  getCustomerByUserName(userName)
  {
    return this._router.navigate(["/customerProfile" + `/${userName}`]);
  }
  logout() {
    localStorage.clear();
    window.location.href = "/";
  }
}
