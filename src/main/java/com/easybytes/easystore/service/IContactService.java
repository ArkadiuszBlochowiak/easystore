package com.easybytes.easystore.service;

import com.easybytes.easystore.dto.ContactRequestDto;

public interface IContactService {
    boolean saveContact(ContactRequestDto contactRequestDto);
}
