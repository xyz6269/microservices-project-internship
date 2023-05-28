package org.example.authenticationservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.authenticationservice.DTO.AuthenticationRequest;
import org.example.authenticationservice.DTO.AuthenticationResponse;
import org.example.authenticationservice.DTO.RegisterRequest;
import org.example.authenticationservice.entity.User;
import org.example.authenticationservice.service.AuthenticationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registry/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        log.info("hi my name is jefffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    
    @GetMapping("/current")
    public  ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.getCurrentUser());
    }

    @GetMapping("/current-email")
    public  ResponseEntity<String> getCurrentUserEmail() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.getCurrentUser().getEmail());
    }


    //"firstName" : "admin",
    //   "lastName" : "admin",

}
