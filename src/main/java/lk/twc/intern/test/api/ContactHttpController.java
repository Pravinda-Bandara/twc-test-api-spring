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

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@CrossOrigin
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
    @DeleteMapping("/{userId}")
    public void deleteContactById(@PathVariable Long userId) {
        System.out.println("------------------------");
        System.out.println(userId);
        contactService.deleteContact(userId);
    }
    @PatchMapping(value = "/{_id}",consumes ="application/json")
    public ContactResponse updateContact(@PathVariable Long _id, @RequestBody ContactTO updatedContact) {
        System.out.println(_id);
        System.out.println(updatedContact);
       return contactService.updateContact(updatedContact, _id);
    }
    @GetMapping("/{userID}")
    public List<ContactResponse> getContacts(@PathVariable Long userID) {
        return contactService.getContacts(userID);
    }

}
