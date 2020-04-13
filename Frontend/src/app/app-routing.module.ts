import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './customer/home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { AdminUserComponent } from './admin/admin-user/admin-user.component';
import { AdminCustomerComponent } from './admin/admin-customer/admin-customer.component';
import { AdminAddUserComponent } from './admin/admin-add-user/admin-add-user.component';
import { AdminUserProfileComponent } from './admin/admin-user-profile/admin-user-profile.component';
import { CustomerProfileEditComponent } from './customer/customer-profile-edit/customer-profile-edit.component';
import { OrderIcecreamComponent } from './customer/order-icecream/order-icecream.component';
import { CartPageComponent } from './customer/cart-page/cart-page.component';
import { AdminCustomerProfileComponent } from './admin/admin-customer-profile/admin-customer-profile.component';
import { AdminAddCustomerComponent } from './admin/admin-add-customer/admin-add-customer.component';
import { AdminRecipeComponent } from './admin/admin-recipe/admin-recipe.component';
import { AdminRecipeDetailComponent } from './admin/admin-recipe-detail/admin-recipe-detail.component';
import { AdminOrderComponent } from './admin/admin-order/admin-order.component';
import { AdminOrderDetailsComponent } from './admin/admin-order-details/admin-order-details.component';
import { AdminListOrderDetailsComponent } from './admin/admin-list-order-details/admin-list-order-details.component';
import { AdminFeedbackComponent } from './admin/admin-feedback/admin-feedback.component';
import { AdminFeedbackDetailsComponent } from './admin/admin-feedback-details/admin-feedback-details.component';
import { CustomerRegisterComponent } from './customer/customer-register/customer-register.component';
import { CustomerRecipeDetailsComponent } from './customer/customer-recipe-details/customer-recipe-details.component';
import { CustomerPaymentComponent } from './customer/customer-payment/customer-payment.component';
import { OrderHistoryComponent } from './customer/order-history/order-history.component';
import { OrderHistoryDetailsComponent } from './customer/order-history-details/order-history-details.component';
import { CustomerChangePasswordComponent } from './customer/customer-change-password/customer-change-password.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { AddRecipeComponent } from './user/add-recipe/add-recipe.component';
import { UserChangePasswordComponent } from './user/user-change-password/user-change-password.component';

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'customerRegister', component: CustomerRegisterComponent},
  {path: 'customerChangePassword', component: CustomerChangePasswordComponent},
  {path: 'userChangePassword', component: UserChangePasswordComponent},
  {path: 'manageUserProfile/:id', component: AdminUserProfileComponent},
  {path: 'userProfile/:userName', component: UserProfileComponent},
  {path: 'manageUser', component: AdminUserComponent},
  {path: 'manageCustomer', component: AdminCustomerComponent},
  {path: 'manageRecipe',component: AdminRecipeComponent},
  {path: 'insertUser', component: AdminAddUserComponent},
  {path: 'insertRecipe', component: AddRecipeComponent},
  {path: 'customerProfile/:userName',component: CustomerProfileEditComponent},
  {path: 'customerRecipeDetails/:id',component: CustomerRecipeDetailsComponent},
  {path: 'customerPayment', component: CustomerPaymentComponent},
  {path: 'orderIcecream',component: OrderIcecreamComponent},
  {path: 'orderHistory',component: OrderHistoryComponent},
  {path: 'orderHistoryDetails/:id',component: OrderHistoryDetailsComponent},
  {path: 'cartRecipe',component:CartPageComponent},
  {path: 'manageCustomerProfile/:id',component: AdminCustomerProfileComponent},
  {path: 'manageRecipeDetail/:id',component: AdminRecipeDetailComponent},
  {path: 'manageAddCustomer',component: AdminAddCustomerComponent},
  {path: 'manageOrder',component: AdminOrderComponent},
  {path: 'manageOrderDetails/:id',component: AdminOrderDetailsComponent},
  {path: 'manageListOrderDetails',component: AdminListOrderDetailsComponent},
  {path: 'manageFeedback',component: AdminFeedbackComponent},
  {path: 'manageFeedbackDetails/:id',component: AdminFeedbackDetailsComponent}

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
