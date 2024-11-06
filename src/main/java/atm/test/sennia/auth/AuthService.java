package atm.test.sennia.auth;

import atm.test.sennia.config.JwtService;
import atm.test.sennia.user.User;
import atm.test.sennia.user.UserRpository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRpository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public CostumeResponse logIn(@RequestBody LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserEmail(),loginRequest.getPassword())

        );
        User user=this.userRepository.findByEmail(loginRequest.getUserEmail()).orElseThrow(()->new UsernameNotFoundException("user not found"));
        return CostumeResponse.builder().token(jwtService.generateToken(user)).role(user.getRole()).build() ;
    }
    public CostumeResponse signUp(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return CostumeResponse.builder().token(jwtService.generateToken(user)).role(user.getRole()).build();
    }
    public CostumeResponse refreshToken(String token){
    String email=    jwtService.extractUsername(token);
        User user=this.userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user was not found"));
        return CostumeResponse.builder().token(jwtService.generateToken(user)).role(user.getRole()).build() ;

    }

}
