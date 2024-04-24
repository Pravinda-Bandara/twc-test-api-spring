package lk.twc.intern.test.service.util;

import lk.twc.intern.test.entity.Contact;
import lk.twc.intern.test.entity.User;
import lk.twc.intern.test.to.ContactTO;
import lk.twc.intern.test.to.UserTO;
import lk.twc.intern.test.type.ContactResponse;
import lk.twc.intern.test.type.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {

    private final ModelMapper mapper;

    public Transformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public User fromUserTO(UserTO userTO){
        return mapper.map(userTO, User.class);
    }

    public Contact fromContactTO(ContactTO contactTO){
        return mapper.map(contactTO, Contact.class);
    }

    public UserTO toUserTO(User user){
        return mapper.map(user, UserTO.class);
    }

    public ContactTO toContactTO(Contact contact){
        return mapper.map(contact, ContactTO.class);
    }
    public ContactResponse toContactResponse(Contact contact){
        ContactResponse contactResponse = mapper.map(contact, ContactResponse.class);
        contactResponse.set_id(contact.getId());
        return contactResponse;
    }
    public UserResponse toUserResponse(Long id){
        return mapper.map(id, UserResponse.class);
    }

}
