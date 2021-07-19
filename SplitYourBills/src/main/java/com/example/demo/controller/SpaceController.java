package com.example.demo.controller;

import com.example.demo.dto.AddNewSpaceDTO;
import com.example.demo.model.Space;
import com.example.demo.payload.ApiResponse;
import com.example.demo.security.CurrentUser;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/spaces")
public class SpaceController {
    @Autowired
    private SpaceService spaceService;

    @Autowired
    private UserController userController;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addASpace(@RequestBody AddNewSpaceDTO space, @CurrentUser UserPrincipal currentUser){
        long userId = userController.getCurrentUserId(currentUser);
        spaceService.addSpace(space,userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Created a new space for person id : " + userId), HttpStatus.CREATED);

    }
//    @GetMapping("/")
//    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
//        authenticationService.authenticate(token);
//        int userId = authenticationService.getUser(token).getId();
//        CartDto cartDto = cartService.listCartItems(userId);
//        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
//    }
//    @PutMapping("/update/{cartItemId}")
//    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
//                                                      @RequestParam("token") String token) throws AuthenticationFailException,ProductNotExistException {
//        authenticationService.authenticate(token);
//        int userId = authenticationService.getUser(token).getId();
//
//        Product product = productService.getProductById(cartDto.getProductId());
//
//        cartService.updateCartItem(cartDto,userId,product);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{cartItemId}")
//    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,@RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
//        authenticationService.authenticate(token);
//
//        int userId = authenticationService.getUser(token).getId();
//
//        cartService.deleteCartItem(itemID,userId);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
//    }

}
