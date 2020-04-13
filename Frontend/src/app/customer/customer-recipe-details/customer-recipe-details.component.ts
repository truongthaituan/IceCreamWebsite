import { Component, OnInit } from '@angular/core';
import { Icecream } from 'src/app/services/icecream-service/icecream.model';
import { User } from 'src/app/user-service/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/user-service/user.service';
import { IcecreamService } from 'src/app/services/icecream-service/icecream.service';
import { RecipeService } from 'src/app/services/recipe-service/recipe.service';
import { Recipe } from 'src/app/services/recipe-service/recipe.model';
declare var $:any;

@Component({
  selector: 'app-customer-recipe-details',
  templateUrl: './customer-recipe-details.component.html',
  styleUrls: ['./customer-recipe-details.component.css']
})
export class CustomerRecipeDetailsComponent implements OnInit {


  constructor(private _router: Router,private route: ActivatedRoute,private userService: UserService,
    private iceCreamService: IcecreamService, private recipeService: RecipeService) {
  
 }
 //chứa thông tin giỏ hàng
 CartRecipe = [];
 TongTien = 0;
 TongCount = 0;
 lengthCartRecipe = 0;
 //alert
 alertSucess = false;
ngOnInit() {
 let id = this.route.snapshot.paramMap.get('id');
 console.log(id);
 this.getRecipeById(Number(id));
    //set value giỏ hàng trên thanh head 
    this.getTotalCountAndPrice();

  }
getRecipeById(id: number){
  this.recipeService.getRecipeById(id).subscribe((res) => {
  this.recipeService.recipe = res as Recipe;
  console.log( this.recipeService.recipe)
  this.checkGetCountRecipeDetailEqual10(id);
  this.recipeService.recipe.user = Object.values(res)[1];
  this.userService.getUserById(this.recipeService.recipe.user).subscribe((res) => {
    this.userService.user = res as User;
    console.log(this.userService.user)
    })
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
    console.log(this.countRecipeDetailCur);
    //nếu nhập 0
    if (event.target.value == 0) {
      //show alert
      this.checkedAddRecipe = false;


      this.alertMessage = "Bạn không thể mua sách với số lượng bằng 0";
      this.alertFalse = true;
      setTimeout(() => { this.alertMessage = ""; this.alertFalse = false }, 4000);
    }
    else
      if (event.target.value > 10) {
        //show alert
        this.checkedAddRecipe = false;


        this.alertMessage = "Bạn chỉ được nhập tối đa 10 quốn sách";
        this.alertFalse = true;
        setTimeout(() => { this.alertMessage = ""; this.alertFalse = false }, 4000);
      } else {
        var CountMax10 = parseInt(event.target.value) + (this.countRecipeDetailCur);


        if (CountMax10 > 10) {
          //show alert
          this.checkedAddRecipe = false;
          //update lại số lượng 

          this.alertMessage = "số lượng tối đa chỉ được 10 mỗi quốn sách , tính luôn đã có trong giỏ hàng";
          this.alertFalse = true;
          setTimeout(() => { this.alertMessage = ""; this.alertFalse = false }, 4000);
        }
      }
    // if (!this.checkedAddRecipe) {
    //   $("#countRecipe").val(1);
    // }
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
   
    this.checkedAddRecipe = true;
    var CartRecipe = [];    //lưu trữ bộ nhớ tạm cho localStorage "CartRecipe"
    var dem = 0;            //Vị trí thêm sách mới vào localStorage "CartRecipe" (nếu sách chưa tồn tại)
    var temp = 0;           // đánh dấu nếu đã tồn tại sách trong localStorage "CartRecipe" --> count ++
    // nếu localStorage "CartRecipe" không rỗng
    if (!form.quantity )form.quantity = 1;
    if(form.quantity > 10) {
      this.checkedAddRecipe = false;
    }
    // nếu số lượng nhập vào <=10 thì oke 
    if (form.quantity <= 10) {
      if (localStorage.getItem('CartRecipe') != null) {
        //chạy vòng lặp để lưu vào bộ nhớ tạm ( tạo mảng cho Object)
        if (!form.quantity) form.quantity = 1;
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
               setTimeout(() => {this.alertMessage="";this.alertSucess=false}, 6000); 
            }
            else {
              if (  this.countRecipeDetailCur==10) {
                //show alert
                this.checkedAddRecipe = false;
                //update lại số lượng 
                this.alertMessage = "Đã tồn tại 10 quốn sách "+ CartRecipe[i].title +" trong giỏ hàng" ;
                this.alertFalse = true;
                setTimeout(() => { this.alertMessage = ""; this.alertFalse = false }, 4000);
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
      setTimeout(() => {this.alertMessage="";this.alertSucess=false}, 6000);
      }
      localStorage.setItem("CartRecipe", JSON.stringify(CartRecipe));
       // if (!this.checkedAddRecipe) {
    // }
    } 
   
    this.ngOnInit();
  }

}
