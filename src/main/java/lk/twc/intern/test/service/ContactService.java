package lk.twc.intern.test.service;

import lk.twc.intern.test.entity.Contact;
import lk.twc.intern.test.to.ContactTO;
import lk.twc.intern.test.type.ContactResponse;

import java.util.List;

public interface ContactService {
    ContactResponse saveContact(ContactTO contactTO);
void deleteContact(Long id);

ContactResponse updateContact(ContactTO contactTO,Long contactID);
List<ContactResponse> getContacts(Long userID);

}
