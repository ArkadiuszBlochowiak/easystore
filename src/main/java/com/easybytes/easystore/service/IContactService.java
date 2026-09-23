package com.easybytes.easystore.service;

import com.easybytes.easystore.dto.ContactRequestDto;
import com.easybytes.easystore.dto.ContactResponseDto;

import java.util.List;

public interface IContactService {
    boolean saveContact(ContactRequestDto contactRequestDto);

    List<ContactResponseDto> getAllOpenMessages();

    void updateMessageStatus(Long contactId, String status);
}
