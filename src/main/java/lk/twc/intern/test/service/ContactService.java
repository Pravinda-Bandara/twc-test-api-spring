package lk.twc.intern.test.service;

import lk.twc.intern.test.entity.Contact;
import lk.twc.intern.test.to.ContactTO;

import java.util.List;

public interface ContactService {
ContactTO saveContact(ContactTO contactTO);
void deleteContact(Long id);

}
