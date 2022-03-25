package com.rfelipe.auth.controller;

import com.rfelipe.auth.data.vo.UserVO;
import com.rfelipe.auth.jwt.JwtTokenProvider;
import com.rfelipe.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }


    @RequestMapping("/testeSecurity")
    public String teste(){
        return "testado";
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> login (@RequestBody UserVO userVO) throws AuthenticationException {

        var username = userVO.getUserName();
        var password = userVO.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        var user = userRepository.findByUserName(username);

        var token = "";
        if (user != null){
            token = jwtTokenProvider.createToken(username, user.getRoles());
        }else{
            throw new UsernameNotFoundException("Username not found");
        }

        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);

        return ok(model);

    }

}
