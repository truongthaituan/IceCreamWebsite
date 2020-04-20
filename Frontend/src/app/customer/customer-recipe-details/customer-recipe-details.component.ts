import { Component, OnInit } from '@angular/core';
import { Icecream } from 'src/app/services/icecream-service/icecream.model';
import { User } from 'src/app/user-service/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/user-service/user.service';
import { IcecreamService } from 'src/app/services/icecream-service/icecream.service';
import { RecipeService } from 'src/app/services/recipe-service/recipe.service';
import { Recipe } from 'src/app/services/recipe-service/recipe.model';
import { FeedbackService } from 'src/app/services/feedback-service/feedback.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { OrderDetailsService } from 'src/app/services/orderdetails-service/order-details.service';
import { OrderDetails } from 'src/app/services/orderdetails-service/order-details.model';
import { Feedback } from 'src/app/services/feedback-service/feedback.model';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { Customer } from 'src/app/services/customer-service/customer.model';
declare var $:any;

@Component({
  selector: 'app-customer-recipe-details',
  templateUrl: './customer-recipe-details.component.html',
  styleUrls: ['./customer-recipe-details.component.css']
})
export class CustomerRecipeDetailsComponent implements OnInit {

  feedbackForm: FormGroup = new FormGroup({
    customer: new FormControl(null, [Validators.required]),
    order: new FormControl(null, Validators.required),
    details: new FormControl(null, Validators.required),
    createDate: new FormControl(null, Validators.required)
  })
  constructor(private _router: Router,private route: ActivatedRoute,
    private userService: UserService,private feedbackService: FeedbackService,private customerService: CustomerService,
    private iceCreamService: IcecreamService, private recipeService: RecipeService, private orderDetailsService: OrderDetailsService) {
  
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
  this.initialFeedback();
  }


  initialFeedback() {
    this.feedbackService.feedback = ({
      feedbackId: null,
      customer: null,
      order: null,
      details: '',
      createDate: ''
    });
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

  now: Date = new Date();
  submitFeedback(recipeId) {
    this.now = new Date();
    this.orderDetailsService.getOrderDetailsByRecipe(recipeId).subscribe(res => {
      this.orderDetailsService.orderDetails = res as OrderDetails[];
        if( this.orderDetailsService.orderDetails == null){
                    this.alertFalse = true;
                    this.alertMessage = "Your orders not exist this recipe so you can't send feedback!"
                    setTimeout(() => {this.alertSucess = false; this.alertMessage = '';}, 4000);
        }else{
          for(let i = 0; i < this.orderDetailsService.orderDetails.length;i++){
            this.customerService.getCustomerByUserName(localStorage.getItem("userName")).subscribe(res => {
              console.log(res)
              this.customerService.customer = res as Customer
              if(this.orderDetailsService.orderDetails[i].order.customer.customerId == this.customerService.customer.customerId
                && this.orderDetailsService.orderDetails[i].recipe.id == recipeId) {
                  console.log(this.feedbackForm.value)
                  this.feedbackForm.value.order = this.orderDetailsService.orderDetails[i].order
                  this.feedbackForm.value.customer = this.orderDetailsService.orderDetails[i].order.customer;
                  this.feedbackForm.value.createDate = this.now;
                  this.feedbackService.createFeedback(JSON.stringify(this.feedbackForm.value)).subscribe(res => {
                    this.alertSucess = true;
                    this.alertMessage = "Submit Feedback Successfully!"
                      this.feedbackForm.reset();
                      this.ngOnInit();
                      setTimeout(() => {this.alertSucess = false; this.alertMessage = '';}, 4000);

                  })
                console.log(this.orderDetailsService.orderDetails)
              }
            })
          }
        }
    })
    // if (this.feedbackForm.value.details == null) {
    //   alert("Details is empty!");
    //   return;
    // }
    // else if (this.registerForm.controls.password.value != this.registerForm.controls.repassword.value) {
    //     alert("Confirm password was wrong!");
    //     return false;
    //   }
    //   else {
    //     this.registerForm.value.roles = [{"id": parseInt(this.registerForm.value.roles)}];
    //     this.registerForm.value.avatar = $('input[type=file]').val().replace(/C:\\fakepath\\/i, 'images/');  
    //     // console.log(this.registerForm.value.avatar);
    //     // console.log(JSON.stringify(this.registerForm.value)); 
    //     this.userService.register(JSON.stringify(this.registerForm.value))
    //       .subscribe(
    //         data => {
    //           // console.log(data);
    //           this.statusRegister = true;
    //           this._router.navigate(['/manageUser']);
    //           console.log("Add User Successfully!");
    //         },
    //         error => {
    //           // console.log(error);
    //           this.errRegister = "Tài khoản có thể đã tồn tại!";
    //         })
    //   }
  }
}
