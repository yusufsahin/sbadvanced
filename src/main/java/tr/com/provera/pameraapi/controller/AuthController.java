package tr.com.provera.pameraapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import tr.com.provera.pameraapi.dao.model.RefreshToken;
import tr.com.provera.pameraapi.dao.model.User;
import tr.com.provera.pameraapi.dto.*;
import tr.com.provera.pameraapi.security.TokenProvider;
import tr.com.provera.pameraapi.service.RefreshTokenService;
import tr.com.provera.pameraapi.service.UserService;
import tr.com.provera.pameraapi.util.ApiPaths;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiPaths.AuthCtrl.CTRL)
@Tag(name = ApiPaths.AuthCtrl.CTRL, description = "Auth APIs")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    /*
    @Autowired
    private EmailSenderService emailSenderService;
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Operation(summary = "Login Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(schema = @Schema(implementation = ResponseEntity.class))
            )})
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUser) throws AuthenticationException, UnsupportedEncodingException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);


        AuthToken authToken = new AuthToken();

        UserDto userDetail= userService.findByUsername(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(authentication);
        final RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetail);
        UserDto authUser= new UserDto();

        authUser.setId(userDetail.getId());
        authUser.setUsername(userDetail.getUsername());
        authUser.setEmail(userDetail.getEmail());
        authUser.setFirstname(userDetail.getFirstname());
        authUser.setLastname(userDetail.getLastname());
        authUser.setPhonenum(userDetail.getPhonenum());
        authUser.setPicture(userDetail.getPicture());
        authToken.setToken(token);
        authToken.setRefreshToken(refreshToken.getToken());
        authToken.setUser(authUser);


        return ResponseEntity.ok(authToken);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Operation(summary = "Register Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succesful Operation",content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "409", description = "an error occured")
    }
    )
    public ResponseEntity<Boolean> register(@RequestBody UserRegisterDto userRegisterDto) throws AuthenticationException {

        try {
            Boolean response = userService.register(userRegisterDto);
            if(true)
            {
                //SimpleMailMessage simpleMsg = new SimpleMailMessage();
                //simpleMsg.setSubject("Welcome to PAMERA");
                //simpleMsg.setTo(userRegisterDto.getEmail());
                //simpleMsg.setText("Welcome & Validate");

                //emailSenderService.sendEmail(simpleMsg);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Operation(summary = "Forgot password Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succesfull Operation",content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "409", description = "An error occured")
    })
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public ResponseEntity<Boolean> forgotpassword(@RequestBody UserForgotPasswordDto userForgotPasswordDto) throws AuthenticationException {

        try {
            //Boolean response = userService.forgotPassword(userForgotPasswordDto);
            return ResponseEntity.ok(true); //parantez içinde response olucak
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @RequestMapping(value = "/resetpassword/{id}", method = RequestMethod.POST)
    @Operation(summary = "Reset password Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succesfull Operation",content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "409", description = "An error occured")
    })
    public ResponseEntity<Boolean> resetpassword(@PathVariable(value = "id", required = true) UUID id, @RequestBody UserResetPasswordDto userResetPasswordDto) throws AuthenticationException {

        try {
            Boolean response = userService.resetPassword(userResetPasswordDto);
            return ResponseEntity.ok(response);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    @Operation(summary = "Refresh Token Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(schema = @Schema(implementation = ResponseEntity.class))
            )})
    public ResponseEntity<AuthToken> refresh(@RequestParam(name = "refreshToken") String refreshToken){

        RefreshToken dbRefreshToken = refreshTokenService.findByToken(refreshToken);
        if(dbRefreshToken != null){
            if(dbRefreshToken.getUser() != null && !refreshTokenService.isRefreshExpired(dbRefreshToken)){
                User user = dbRefreshToken.getUser();
                UserDto userDto = userService.findByUsername(user.getUsername());
                String token = jwtTokenUtil.generateTokenByUser(user);
                RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(userDto);
                AuthToken authToken = new AuthToken(token,newRefreshToken.getToken(),userDto);
                return ResponseEntity.ok(authToken);
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

//String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
//
//Pattern pattern = Pattern.compile(regex);