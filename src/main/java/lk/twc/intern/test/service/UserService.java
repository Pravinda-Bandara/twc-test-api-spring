package lk.twc.intern.test.service;


import lk.twc.intern.test.to.UserTO;

public interface UserService {
    Long saveUser(UserTO userTO);
    Long getUser(UserTO userTO);
}
