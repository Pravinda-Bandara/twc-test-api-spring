package lk.twc.intern.test.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lk.twc.intern.test.entity.Contact;
import lk.twc.intern.test.entity.User;
import lk.twc.intern.test.repository.ContactRepository;
import lk.twc.intern.test.repository.UserRepository;
import lk.twc.intern.test.service.ContactService;
import lk.twc.intern.test.service.util.Transformer;
import lk.twc.intern.test.to.ContactTO;
import lk.twc.intern.test.type.ContactResponse;


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
        System.out.println(contactTO);
        Optional<User> existUser = userRepository.findById(contactTO.getUser());
        System.out.println(existUser.get());
        if (existUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Contact contact = transformer.fromContactTO(contactTO);
        System.out.println(contact);
        contact.setUser(existUser.get());
        System.out.println(contact);
        contactRepository.save(contact);
        ContactResponse contactResponse=transformer.toContactResponse(contact);
        System.out.println(contactResponse);
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
    public ContactResponse updateContact(ContactTO contactTO, Long contactID) {

        Optional<Contact> existContact = contactRepository.findById(contactID);
        if (existContact.isEmpty()) {
            throw new IllegalArgumentException("Contact not found");
        }
        Contact contact=existContact.get();
        contact.setName(contactTO.getName());
        contact.setEmail(contactTO.getEmail());
        contact.setGender(contactTO.getGender());
        contact.setNumber(contactTO.getNumber());
        ContactResponse contactResponse = transformer.toContactResponse(contact);
        return contactResponse;
    }


    @Override
    public List<ContactResponse> getContacts(Long userID) {
        Optional<User> existUser = userRepository.findById(userID);
        if (existUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        List<Contact> contacts = contactRepository.findByUserId(userID);
        return contacts.stream()
                .map(transformer::toContactResponse)
                .collect(Collectors.toList());
    }
}
