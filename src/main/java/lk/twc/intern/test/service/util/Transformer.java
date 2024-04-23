package lk.twc.intern.test.service.util;

import lk.twc.intern.test.entity.Contact;
import lk.twc.intern.test.entity.User;
import lk.twc.intern.test.to.ContactTO;
import lk.twc.intern.test.to.UserTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class Transformer {

    private final ModelMapper mapper;

    public Transformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public User fromUserTO(UserTO userTO){
        User user = mapper.map(userTO, User.class);
        return user;
    }

    public Contact fromContactTO(ContactTO lecturerTO){
        Contact contact = mapper.map(lecturerTO, Contact.class);
        return contact;
    }

    public UserTO toLecturerTO(User lecturer){
        return mapper.map(lecturer, UserTO.class);
    }
    public ContactTO toLecturerTO(Contact lecturer){
        return mapper.map(lecturer, ContactTO.class);
    }
}
