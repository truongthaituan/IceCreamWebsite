import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeService } from 'src/app/services/recipe-service/recipe.service';
import { Recipe } from 'src/app/services/recipe-service/recipe.model';
declare var $:any;
@Component({
  selector: 'app-order-icecream',
  templateUrl: './order-icecream.component.html',
  styleUrls: ['./order-icecream.component.css']
})
export class OrderIcecreamComponent implements OnInit {
   searchText;
  searchCategory;
  pageOfItems: Array<any>;
  recipes: Array<Recipe>;
  items: any;
  collection = [];
  selectedCategory: String = "";
  constructor(private _router: Router, private recipeService: RecipeService) {
    $(document).ready(function() {
      // grep the price only once...
    //   $("#countRecipe").on("keyup keydown change",function(event){
    //    if($("#countRecipe").val() < 0){
    //      alert(1)
    //    }
    // });
  });
  
   }
 //chứa thông tin giỏ hàng
 CartRecipe = [];
 TongTien = 0;
 TongCount = 0;
 lengthCartRecipe = 0;
 //alert
 alertSucess = false;
  ngOnInit() {
    this.refreshRecipeList();
    this.getTotalCountAndPrice();
  }

  refreshRecipeList(){
    this.recipeService.getRecipeList().subscribe((res) => {
      this.recipeService.recipes = res as Recipe[];
      console.log(res)
    })
  }



 // set độ dài của giỏ hàng
 cartRecipeLength(CartRecipe) {

  if (CartRecipe == null) {
    this.lengthCartRecipe = 0;
  } else {
    this.lengthCartRecipe = CartRecipe.length;
  }
}

onChangePage(pageOfItems: Array<any>) {
  // update current page of items
  this.pageOfItems = pageOfItems;
}


   //get total count and price 
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

  // số lượng add tối đa chỉ được 10 mỗi quốn sách , tính luôn đã có trong giỏ
  //##1 sự kiện change input
  alertFalse = false;
  alertMessage = "";
  checkedAddRecipe = true;
  countRecipeDetailCur = 0;

  getcountDetail(selectedRecipe: Recipe, event: any) {
    this.checkedAddRecipe = true;
 
    for( let i =0 ; i< this.recipeService.recipes.length;i++)
    {
      if(this.recipeService.recipes[i].id==selectedRecipe.id){
       this.checkGetCountRecipeDetailEqual10(selectedRecipe.id);
     
    //nếu nhập 0
    if (event.target.value == 0) {
      //show alert
      this.checkedAddRecipe = false;
      this.alertMessage = "You can't buy this recipe with quantity equal 0";
      this.alertFalse = true;
      setTimeout(() => { this.alertMessage = ""; this.alertFalse = false }, 3000);
    }
    else
      if (event.target.value > 10) {
        //show alert
        this.checkedAddRecipe = false;
        this.alertMessage = "You only can buy this recipe not more than 10";
        this.alertFalse = true;
        setTimeout(() => { this.alertMessage = ""; this.alertFalse = false },3000);
      } else {
        var CountMax10 = parseInt(event.target.value) + (this.countRecipeDetailCur);
        console.log("123");
        if (CountMax10 > 10) {
          //show alert
          console.log("hello123");
          this.checkedAddRecipe = false;
          //update lại số lượng 
          this.alertMessage = "The maximum number is only 10 per recipe, counting already in the cart";
          this.alertFalse = true;
          setTimeout(() => { this.alertMessage = ""; this.alertFalse = false }, 3000);
        }
      }
    // if (!this.checkedAddRecipe) {
    //   $("#countRecipe").val(1);
    // }
  }
}
    console.log(this.checkedAddRecipe);
  }

  // số lượng add tối đa chỉ được 10 mỗi quốn sách , tính luôn đã có trong giỏ
  //##2 khi số lượng đã 10 , ko nhấn change input , nhấn add to cart-->fail
  checkGetCountRecipeDetailEqual10(id) {
    this.checkedAddRecipe = true;  
    for (var i = 0; i < this.lengthCartRecipe; i++) {
      if (this.CartRecipe[i].id == id) {
        this.countRecipeDetailCur = this.CartRecipe[i].quantity;
        if (this.CartRecipe[i].quantity == 10) {
          //show alert
          this.checkedAddRecipe = false;
          //update lại số lượng 
        }
      }
    }
  }

  //add to cart (RecipeDetail,CountSelect) 
  addToCart(selectedRecipe: Recipe, form: Recipe) {
    this.getTotalCountAndPrice();
    this.checkGetCountRecipeDetailEqual10(selectedRecipe.id);
    console.log("curent===>"+ this.countRecipeDetailCur);
    this.checkedAddRecipe = true;
    var CartRecipe = [];    //lưu trữ bộ nhớ tạm cho localStorage "CartRecipe"
    var dem = 0;            //Vị trí thêm sách mới vào localStorage "CartRecipe" (nếu sách chưa tồn tại)
    var temp = 0;           // đánh dấu nếu đã tồn tại sách trong localStorage "CartRecipe" --> count ++
    // nếu localStorage "CartRecipe" không rỗng
    if (!form.quantity) 
    {
      form.quantity = 1;
    }
      if (form.quantity > 10) 
    {
      console.log( this.countRecipeDetailCur);
       //show alert
                this.checkedAddRecipe = false;
                //update lại số lượng 
                
                this.alertMessage = "No more than 10 " + CartRecipe[i].title +" at a time" ;
                this.alertFalse = true;
                setTimeout(() => { this.alertMessage = ""; this.alertFalse = false }, 3000);
      return false;
    }
      // nếu số lượng nhập vào <=10 thì oke 
    if (form.quantity <= 10) {
      if (localStorage.getItem('CartRecipe') != null) {
        //chạy vòng lặp để lưu vào bộ nhớ tạm ( tạo mảng cho Object)
        if (!form.quantity){
           form.quantity = 1;
        }
           for (var i = 0; i < this.lengthCartRecipe; i++) {
          CartRecipe[i] = JSON.parse(localStorage.getItem("CartRecipe"))[i];
          // nếu id Recipe đã tồn tại trong  localStorage "CartRecipe" 
          if (CartRecipe[i].id == selectedRecipe.id) {
            temp = 1;  //đặt biến temp
            // nếu số lượng tối đa chỉ được 10 mỗi quốn sách , tính luôn đã có trong giỏ thì oke
            if (parseInt(CartRecipe[i].quantity) + form.quantity <= 10) {
              CartRecipe[i].quantity = parseInt(CartRecipe[i].quantity) + form.quantity;  //tăng giá trị count
                //show alert
                this.alertMessage="Add recipe "+ selectedRecipe.title +" successfully into cart!";
                this.alertSucess=true;
                setTimeout(() => {this.alertMessage="";this.alertSucess=false}, 3000); 
            }
            else { 
      
              if (this.countRecipeDetailCur == 10) {
                //show alert
                this.checkedAddRecipe = false;
                //update lại số lượng 
                this.alertMessage = "Already exist 10 recipe " + CartRecipe[i].title + " in cart";
                this.alertFalse = true;
                setTimeout(() => { this.alertMessage = ""; this.alertFalse = false }, 3000);
              }

            }
          }
          dem++;  // đẩy vị trí gán tiếp theo
        }
      }
      if (temp != 1) {      // nếu sách chưa có ( temp =0 ) thì thêm sách vào
        selectedRecipe.quantity = form.quantity;  // set count cho sách
        CartRecipe[dem] = selectedRecipe; // thêm sách vào vị trí "dem" ( vị trí cuối) 
              //show alert
      this.alertMessage="Add recipe "+ selectedRecipe.title +" successfully into cart!";
      this.alertSucess=true;
      setTimeout(() => {this.alertMessage="";this.alertSucess=false}, 3000);
      }
      localStorage.setItem("CartRecipe", JSON.stringify(CartRecipe));
    } else{
    }
   
    this.ngOnInit();
  }

}
