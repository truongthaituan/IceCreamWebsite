import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/user-service/user.service';
import { Location } from '@angular/common';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/user-service/user.model';
import { RecipeService } from 'src/app/services/recipe-service/recipe.service';
import { Recipe } from 'src/app/services/recipe-service/recipe.model';
import { IcecreamService } from 'src/app/services/icecream-service/icecream.service';
import { Icecream } from 'src/app/services/icecream-service/icecream.model';
declare var $:any;
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  searchText;
  searchCategory;
  pageOfItems: Array<any>;
  recipes: Array<Recipe>;
  items: any;
  collection = [];
  selectedCategory: String = "";


  //alert
  alertMessage = "";
  alertSucess = false;
  alertFalse = false;
constructor(private _router: Router, private recipeService: RecipeService, private icecreamService : IcecreamService){}
//chứa thông tin giỏ hàng
CartRecipe = [];
TongTien = 0;
TongCount = 0;
le = 0;
  ngOnInit() {
    this.refreshRecipeList();
    this.getTotalCountAndPrice();
    this.refreshIceCreamList();
  }
  lengthCartRecipe = 0;
  // set độ dài của giỏ hàng
    cartRecipeLength(CartRecipe) {
    if (CartRecipe == null) {
      this.lengthCartRecipe = 0;
    } else {
      this.lengthCartRecipe = CartRecipe.length;
    }
  }
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

  refreshRecipeList(){
    this.recipeService.getRecipeList().subscribe((res) => {
      this.recipes = res as Recipe[];
      console.log(res)
    })
  }
  getRecipeListByIceCreamId(icecreamId){
    this.recipeService.getRecipeByicecreamId(icecreamId).subscribe((res) => {
      this.recipes = res as Recipe[];
      console.log(res)
    })
  }

  refreshIceCreamList(){
    this.icecreamService.getIceCreamList().subscribe((res) => {
      this.icecreamService.Icecreams = res as Icecream[];
    })
  }

  onChangePage(pageOfItems: Array<any>) {
    // update current page of items
    this.pageOfItems = pageOfItems;
  }





  checkCountMax10=true;
  checkCountCartBeforeAdd(selectedRecipe: Recipe) {
    this.checkCountMax10=true;
    for (var i = 0; i < this.lengthCartRecipe; i++) {
      if (this.CartRecipe[i].id == selectedRecipe.id) {
          //kiểm tra số lượng 
          if(this.CartRecipe[i].quantity==10)
          {
            this.checkCountMax10=false;
          }
          console.log(this.CartRecipe[i].quantity);
      }
    }
    //nếu sách không có trong CartRecipe ( chưa từng thêm vào giỏ)
    console.log("maxx 10 ??--->"+this.checkCountMax10);
  }

  // go to cart Recipe
  goToCartRecipe() {
    this._router.navigate(['/CartRecipe']);
  }

  checkedAddRecipe = false;
  //add to cart (RecipeDetail,CountSelect) 
  addToCart(selectedRecipe: Recipe) {
    // this.checkedAddRecipe = true;
    var CartRecipe = [];    //lưu trữ bộ nhớ tạm cho localStorage "CartRecipe"
    var dem = 0;            //Vị trí thêm sách mới vào localStorage "CartRecipe" (nếu sách chưa tồn tại)
    var temp = 0;           // đánh dấu nếu đã tồn tại sách trong localStorage "CartRecipe" --> count ++
    // nếu localStorage "CartRecipe" không rỗng
   
    // nếu số lượng nhập vào <=10 thì oke 
    //nếu cart khác rỗng
      if (localStorage.getItem('CartRecipe') != null) {
        //chạy vòng lặp để lưu vào bộ nhớ tạm ( tạo mảng cho Object)
        for (var i = 0; i < this.lengthCartRecipe; i++) {
          CartRecipe[i] = JSON.parse(localStorage.getItem("CartRecipe"))[i];
          // nếu id Recipe đã tồn tại trong  localStorage "CartRecipe" 
          if (CartRecipe[i].id == selectedRecipe.id) {
            temp = 1;  //đặt biến temp
            // nếu số lượng tối đa chỉ được 10 mỗi quốn sách , tính luôn đã có trong giỏ thì oke
            if (parseInt(CartRecipe[i].quantity) + 1 <= 10) {
              this.checkedAddRecipe = true;
              CartRecipe[i].quantity = parseInt(CartRecipe[i].quantity) + 1;  //tăng giá trị count
               //show alert
              this.alertMessage="Add recipe "+ selectedRecipe.title +" successfully into cart!";
              this.alertSucess=true;
              setTimeout(() => {this.alertMessage="";this.alertSucess=false}, 6000); 
   
            }
            else {
              //show alert
              this.checkedAddRecipe = false;
              //update lại số lượng 
              this.alertMessage = "Already exist 10 recipe " + CartRecipe[i].title + " in cart";
              this.alertFalse = true;
              setTimeout(() => { this.alertMessage = ""; this.alertFalse = false }, 6000);

            }
          }
          dem++;  // đẩy vị trí gán tiếp theo
        }
      }
      if (temp != 1) {      // nếu sách chưa có ( temp =0 ) thì thêm sách vào
      //show alert
      this.alertMessage="Add recipe "+ selectedRecipe.title +" successfully into cart!";
       this.alertSucess=true;
       setTimeout(() => {this.alertMessage="";this.alertSucess=false}, 6000); 
        selectedRecipe.quantity = 1;  // set count cho sách
        CartRecipe[dem] = selectedRecipe; // thêm sách vào vị trí "dem" ( vị trí cuối) 
      }
      localStorage.setItem("CartRecipe", JSON.stringify(CartRecipe));
  
    this.ngOnInit();
  }
  getCustomerRecipeById(recipeId)
  {
    return this._router.navigate(["/customerRecipeDetails" + `/${recipeId}`]);
  }
}

