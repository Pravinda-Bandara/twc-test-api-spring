package lk.twc.intern.test.service;


import lk.twc.intern.test.to.UserTO;
import lk.twc.intern.test.type.UserResponse;

public interface UserService {
    UserResponse saveUser(UserTO userTO);
    UserResponse getUser(UserTO userTO);
}
