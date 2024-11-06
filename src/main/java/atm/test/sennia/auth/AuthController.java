package atm.test.sennia.auth;

import atm.test.sennia.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping("/logIn")
    public ResponseEntity<CostumeResponse> logIn(@RequestBody LoginRequest logInRequest){

        try {
            return   new ResponseEntity<>(this.authService.logIn(logInRequest), HttpStatus.OK);
        }catch (UsernameNotFoundException exception){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
    @PostMapping("/signUp")
    public ResponseEntity<CostumeResponse> signUp(@RequestBody User user){
        return new ResponseEntity<>(this.authService.signUp(user), HttpStatus.OK);
    }
    @PostMapping("/refresh")
        public ResponseEntity<CostumeResponse> refreshToken(@RequestBody TokenDTO token){
        return new ResponseEntity<>(this.authService.refreshToken( token.getToken()), HttpStatus.OK);
    }
}
