package lk.twc.intern.test.api;

import lk.twc.intern.test.service.UserService;
import lk.twc.intern.test.to.UserTO;
import lk.twc.intern.test.type.ContactResponse;
import lk.twc.intern.test.type.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserHttpController {

    private final UserService userService;

    public UserHttpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserTO userTO) {
        try {
            UserResponse savedUserId = userService.saveUser(userTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserTO userTO) {
        try {
            UserResponse authenticatedUserID = userService.getUser(userTO);
            return ResponseEntity.ok(authenticatedUserID);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
