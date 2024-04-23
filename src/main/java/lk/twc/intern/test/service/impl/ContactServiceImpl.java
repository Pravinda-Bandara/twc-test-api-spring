package lk.twc.intern.test.service.impl;

import lk.twc.intern.test.entity.Contact;
import lk.twc.intern.test.entity.User;
import lk.twc.intern.test.repository.ContactRepository;
import lk.twc.intern.test.repository.UserRepository;
import lk.twc.intern.test.service.ContactService;
import lk.twc.intern.test.to.ContactTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContactTO saveContact(ContactTO contactTO) {
        Optional<User> existUser = userRepository.findById(contactTO.getUser());
        if (existUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Contact contact = modelMapper.map(contactTO, Contact.class);
        contact.setUser(existUser.get());
        contactRepository.save(contact);
        return contactTO;
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
}
