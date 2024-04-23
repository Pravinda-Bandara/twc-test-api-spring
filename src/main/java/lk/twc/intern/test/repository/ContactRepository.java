package lk.twc.intern.test.repository;

import lk.twc.intern.test.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findByUserId(Long userId);
}
