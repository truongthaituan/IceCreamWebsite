import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
declare var $:any;
@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css']
})
export class CartPageComponent implements OnInit {
  constructor(private _router: Router) {

  }
  //chứa thông tin giỏ hàng
  CartRecipe = [];
  // Lưu tổng tiền và tổng số lượng chung
  TongTien = 0;
  TongCount = 0;
  // kiểm tra giỏ hàng rỗng
  checkViewCart = false;
  lengthCartRecipe = 0;
  //alert
  alertMessage = "";
  alertSucess = false;
  alertFalse = false;
  ngOnInit() {
   //get giỏ hàng
   this.CartRecipe = JSON.parse(localStorage.getItem("CartRecipe"));
   //set độ dài cartRecipe
   this.cartRecipeLength(this.CartRecipe);
   //set value giỏ hàng trên thanh head 
   this.getTotalCountAndPrice();
   // Hiện ra label khi giỏ hàng rỗng
   this.CheckViewCart();
  }

  // set độ dài của giỏ hàng
  cartRecipeLength(CartRecipe) {
    if (CartRecipe == null) {
      this.lengthCartRecipe = 0;
    } else {
      this.lengthCartRecipe = CartRecipe.length;
    }
  }
  //get total count and price trên header
  getTotalCountAndPrice() {
    this.TongTien = 0;
    this.TongCount = 0;
    this.CartRecipe = JSON.parse(localStorage.getItem("CartRecipe"));
    this.cartRecipeLength(this.CartRecipe);
    if (this.CartRecipe != null) {
      for (var i = 0; i < this.lengthCartRecipe; i++) {
        this.TongTien += parseInt(this.CartRecipe[i].price) * parseInt(this.CartRecipe[i].quantity);
        this.TongCount += parseInt(this.CartRecipe[i].quantity);
      }
    }
      $('.item-total').html("&nbsp;" + this.TongTien.toString());
      $('.item-count').html(this.TongCount.toString());
      localStorage.setItem("TongTien", this.TongTien.toString());
      localStorage.setItem("TongCount", this.TongCount.toString());
  }
  //check header giỏ hàng
  CheckViewCart() {
    if (this.CartRecipe == null || this.lengthCartRecipe == 0) {
      this.checkViewCart = true;
    }
    else {
      this.checkViewCart = false;
    }
  }
    
  // cờ hiệu của alert
  checkedAddRecipe = true;
  //get count onChange --> updateCartRecipe
  getCountUpdate(event: any, id) {
    console.log(event.target.value);
    this.checkedAddRecipe = true;
    // kiểm tra xem có lớn hơn 10 ko
    if (event.target.value <= 10) {
      console.log("update");
      this.updateCartRecipe(id,event.target.value);
    }else
     {
      //show alert
      this.checkedAddRecipe = false;
      //update lại số lượng 
      localStorage.setItem("CartRecipe", JSON.stringify(this.CartRecipe));
      this.ngOnInit();
      this.alertMessage = "You can only enter a maximum of 10 books";
      this.alertFalse = true;
      setTimeout(() => { this.alertMessage = ""; this.alertFalse = false }, 3000);
    }
  }
//Update Cart Recipe
updateCartRecipe(id,quantity) {
  //kiểm tra Recipe[id].count có bằng 0 không ,... nếu =0 thì ==> gửi qua hàm xóa
  if (this.checkedAddRecipe) {
    for (var i = 0; i < this.lengthCartRecipe; i++) {
      //tìm id được chọn để edit 
      if (this.CartRecipe[i].id == id) {
        if (quantity == 0) {
          this.deleteCartRecipe(id);
        }
        else{
          this.CartRecipe[i].quantity = quantity;
          this.alertMessage = "Update quantity successfully!";
          this.alertSucess = true;
          setTimeout(() => { this.alertMessage = ""; this.alertSucess = false }, 3000);
        }
      }
    }
  }
  localStorage.setItem("CartRecipe", JSON.stringify(this.CartRecipe));
  this.ngOnInit();
}
      // Delete Cart Recipe
  deleteCartRecipe(id) {
    var setconfirm = confirm('Do you want to delete this recipe?')
    if (setconfirm == true) {
      for (var i = 0; i < this.lengthCartRecipe; i++) {
        if (this.CartRecipe[i].id == id) {
          this.CartRecipe.splice(i, 1);
          break;
        }
      }
      localStorage.setItem("CartRecipe", JSON.stringify(this.CartRecipe));
      this.ngOnInit();
    }
  }

    moveToOrderIcecream() {
      return this._router.navigate(['/orderIcecream']);
    }
    checkoutWhenNull() {
      var setconfirm = confirm('Your cart is empty. Would you like to buy some recipes ?')
      if (setconfirm == true) {
        this.moveToOrderIcecream();
      }
    }
    moveToPayment(){
      return this._router.navigate(['/customerPayment']);
    }
}
