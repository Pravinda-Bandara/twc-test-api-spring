package lk.twc.intern.test.api;

import lk.twc.intern.test.entity.Contact;
import lk.twc.intern.test.service.ContactService;
import lk.twc.intern.test.service.UserService;
import lk.twc.intern.test.to.ContactTO;
import lk.twc.intern.test.to.UserTO;
import lk.twc.intern.test.type.ContactResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactHttpController {
    private final ContactService contactService;

    public ContactHttpController(ContactService contactService) {
        this.contactService = contactService;
    }


    @PostMapping(value = "/",consumes = "application/json",produces = "application/json")
    public ResponseEntity<ContactResponse> saveContact(@RequestBody ContactTO contactTO) {
        try {
            ContactResponse saveContact = contactService.saveContact(contactTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveContact);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @DeleteMapping("/{contactId}")
    public void deleteContactById(@PathVariable Long contactId) {
        System.out.println(contactId);
        contactService.deleteContact(contactId);
    }
    @PatchMapping("/{contactId}")
    public void updateContact(@PathVariable Long contactId, @RequestBody ContactTO updatedContact) {
        contactService.updateContact(updatedContact, contactId);
    }

}
