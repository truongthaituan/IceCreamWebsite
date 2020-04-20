import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './customer/home-page/home-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatBadgeModule} from '@angular/material/badge';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';
import {MatButtonModule} from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatCardModule} from '@angular/material/card';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatChipsModule} from '@angular/material/chips';
import {MatStepperModule} from '@angular/material/stepper';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSliderModule} from '@angular/material/slider';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';
import {MatTabsModule} from '@angular/material/tabs';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatTreeModule} from '@angular/material/tree';
import { LoginPageComponent } from './login-page/login-page.component';
import { AdminUserComponent } from './admin/admin-user/admin-user.component';
import { AdminCustomerComponent } from './admin/admin-customer/admin-customer.component';
import { AdminAddUserComponent } from './admin/admin-add-user/admin-add-user.component';
import { AdminUserProfileComponent } from './admin/admin-user-profile/admin-user-profile.component';
import { CustomerProfileEditComponent } from './customer/customer-profile-edit/customer-profile-edit.component';
import { OrderIcecreamComponent } from './customer/order-icecream/order-icecream.component';
import { CartPageComponent } from './customer/cart-page/cart-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { JwtInterceptor } from './services/jwt-service/Jwt.Interceptor';
import { NgxPaginationModule } from 'ngx-pagination';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
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
import { UserRegisterComponent } from './user/user-register/user-register.component';
import { CustomerRecipeDetailsComponent } from './customer/customer-recipe-details/customer-recipe-details.component';
import { CustomerPaymentComponent } from './customer/customer-payment/customer-payment.component';
import { OrderHistoryComponent } from './customer/order-history/order-history.component';
import { OrderHistoryDetailsComponent } from './customer/order-history-details/order-history-details.component';
import { CustomerChangePasswordComponent } from './customer/customer-change-password/customer-change-password.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { AddRecipeComponent } from './user/add-recipe/add-recipe.component';
import { UserChangePasswordComponent } from './user/user-change-password/user-change-password.component';
import { AdminUserEditComponent } from './admin/admin-user-edit/admin-user-edit.component';
import { AdminCustomerEditComponent } from './admin/admin-customer-edit/admin-customer-edit.component';
import { UserProfileEditComponent } from './user/user-profile-edit/user-profile-edit.component';
import { AdminRecipeEditComponent } from './admin/admin-recipe-edit/admin-recipe-edit.component';
import { ManageOrderDetailsComponent } from './user/manage-order-details/manage-order-details.component';
import { OrderDetailsEditComponent } from './user/order-details-edit/order-details-edit.component';
import { CustomerProfileComponent } from './customer/customer-profile/customer-profile.component';
import { CustomerConfirmAccountComponent } from './customer/customer-confirm-account/customer-confirm-account.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginPageComponent,
    AdminUserComponent,
    AdminCustomerComponent,
    AdminAddUserComponent,
    AdminUserProfileComponent,
    CustomerProfileEditComponent,
    OrderIcecreamComponent,
    CartPageComponent,
    RegisterPageComponent,
    AdminCustomerProfileComponent,
    AdminAddCustomerComponent,
    AdminRecipeComponent,
    AdminRecipeDetailComponent,
    AdminOrderComponent,
    AdminOrderDetailsComponent,
    AdminListOrderDetailsComponent,
    AdminFeedbackComponent,
    AdminFeedbackDetailsComponent,
    CustomerRegisterComponent,
    UserRegisterComponent,
    CustomerRecipeDetailsComponent,
    CustomerPaymentComponent,
    OrderHistoryComponent,
    OrderHistoryDetailsComponent,
    CustomerChangePasswordComponent,
    UserProfileComponent,
    AddRecipeComponent,
    UserChangePasswordComponent,
    AdminUserEditComponent,
    AdminCustomerEditComponent,
    UserProfileEditComponent,
    AdminRecipeEditComponent,
    ManageOrderDetailsComponent,
    OrderDetailsEditComponent,
    CustomerProfileComponent,
    CustomerConfirmAccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    Ng2SearchPipeModule 
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
