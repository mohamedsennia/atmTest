package atm.test.sennia.user;

import atm.test.sennia.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRpository userRpository;
    @Autowired
    public UserService(UserRpository userRpository){
        this.userRpository=userRpository;
    }
    public User findByEmail(String email){
        Optional<User> user=this.userRpository.findByEmail(email);
        if(user.isEmpty()){
            throw  new ResourceNotFoundException("user not found");
        }
        return user.get();
    }
}
