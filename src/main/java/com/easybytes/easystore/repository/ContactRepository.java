package com.easybytes.easystore.repository;

import com.easybytes.easystore.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
