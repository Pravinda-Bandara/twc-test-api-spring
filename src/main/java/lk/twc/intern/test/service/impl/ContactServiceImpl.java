package lk.twc.intern.test.service.impl;

import lk.twc.intern.test.entity.Contact;
import lk.twc.intern.test.entity.User;
import lk.twc.intern.test.repository.ContactRepository;
import lk.twc.intern.test.repository.UserRepository;
import lk.twc.intern.test.service.ContactService;
import lk.twc.intern.test.service.util.Transformer;
import lk.twc.intern.test.to.ContactTO;
import lk.twc.intern.test.type.ContactResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final Transformer transformer;

    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository, Transformer transformer) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.transformer = transformer;
    }

    @Override
    public ContactResponse saveContact(ContactTO contactTO) {
        Optional<User> existUser = userRepository.findById(contactTO.getUser());
        if (existUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Contact contact = transformer.fromContactTO(contactTO);
        contact.setUser(existUser.get());
        contactRepository.save(contact);
        ContactResponse contactResponse=transformer.toContactResponse(contact);
        return contactResponse;
    }

    @Override
    public void deleteContact(Long id) {
        Optional<Contact> existContact = contactRepository.findById(id);

        System.out.println(existContact);
        if (existContact.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        contactRepository.deleteById(id);
    }



    @Override
    public void updateContact(ContactTO contactTO, Long contactID) {
        Optional<User> existUser = userRepository.findById(contactTO.getUser());
        if (existUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Optional<Contact> existContact = contactRepository.findById(contactID);
        if (existContact.isEmpty()) {
            throw new IllegalArgumentException("Contact not found");
        }
        Contact contact=existContact.get();
        contact.setName(contactTO.getName());
        contact.setEmail(contactTO.getEmail());
        contact.setGender(contactTO.getGender());
        contact.setNumber(contactTO.getNumber());
    }
}
