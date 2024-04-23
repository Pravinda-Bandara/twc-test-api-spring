package lk.twc.intern.test.service;


import lk.twc.intern.test.to.UserTO;

public interface UserService {
    UserTO saveUser(UserTO userTO);
    UserTO getUser(UserTO userTO);
}
