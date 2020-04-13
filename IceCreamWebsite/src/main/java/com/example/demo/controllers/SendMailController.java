package com.example.demo.controllers;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.Response;
import com.example.demo.dto.SendMail;
import com.example.demo.dto.StatusCRUD;
import com.example.demo.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/sendmail")
public class SendMailController {
    @Autowired
    MailService mailService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatusCRUD> login(@RequestBody SendMail sendMail) {
        String emailCustomer = sendMail.getEmail();
        String output = " <head>\n" +
                "  <style>\n" +
                "      table {\n" +
                "       font-family: arial, sans-serif;\n" +
                "        border-collapse: collapse;\n" +
                "       width: 100%;\n" +
                "      }\n" +
                "\n" +
                "        td, th {\n" +
                "         border: 1px solid #dddddd;\n" +
                "          text-align: left;\n" +
                "                 padding: 8px;\n" +
                "            }\n" +
                "\n" +
                "      tr:nth-child(even) {\n" +
                "           background-color: #dddddd;\n" +
                "                  }\n" +
                "          </style>\n" +
                "          </head>\n" +
                "    <body>\n" +
                "    <p>Notify about your cart</p>\n" +
                "    <h3>Cart Details</h3>\n" +
                "    <ul>  \n" +
                "      <li>Username: " + sendMail.getUserName() + "</li>\n" +
                "      <li>Address: " +sendMail.getAddress()+ "</li>\n" +
                "      <li>Phone: " +sendMail.getPhone()+ "</li>\n" +
                "      <li>Total: $" +sendMail.getTotal()+ "</li>\n" +
                "      <li>Order Date : " +sendMail.getCreateDate()+ "</li>\n" +
                "    </ul>\n" +
                "    <h3></h3>\n" +
                "    <table>\n" +
                "    <thead>\n" +
                "                               <tr>\n" +
                "                               \n" +
                "                                 <th>Recipe</th>\n" +
                "                                 <th>Price</th>\n" +
                "                                 <th>Quantity</th>\n" +
                "                                 <th>Sub total</th>\n" +
                "                               </tr>\n" +
                "                             </thead>\n" +
                "                             <tbody>\n";

                int dem_i = 0;
                int dem_j = 0;
                int dem_k = 0;
                int dem_l = 0;
                for(String i : (sendMail.getTitle().split("next"))){
                    dem_j = 0;
                for(String j :  (sendMail.getPrice().split("next"))){
                    dem_k = 0;
                 for(String k : (sendMail.getQuantity().split("next"))){
                     dem_l = 0;
                 for(String l :  (sendMail.getSubTotal().split("next"))){
                           if(dem_i == dem_j && dem_i == dem_k && dem_i == dem_l){
                                   if( i != "") {
                                       output = output +
                                               "                                         <tr>\n" +
                                               "                                         <td>" + i + "</td>\n" +
                                               "                                         <td>$" + j + "</td>\n" +
                                               "                                         <td>" + k + "</td>\n" +
                                               "                                         <td>$" + l + "</td>\n" +
                                               "                                 </tr>\n" +
                                               "                                     \n";
                                                    }
                                             }
                                        dem_l = dem_l + 1;
                                    }
                                dem_k = dem_k + 1;
                            }
                       dem_j=dem_j + 1;
                     }
                    dem_i=dem_i + 1;
                }
                output = output + "</tbody>\n" +
                "</table>\n" +
                "</body> ";
        try {
            mailService.sendHTML(emailCustomer,"truongthaituan98@gmail.com",
                    "Your Order", output);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(new StatusCRUD("Send Mail Successfully!"),HttpStatus.OK);
    }
}
