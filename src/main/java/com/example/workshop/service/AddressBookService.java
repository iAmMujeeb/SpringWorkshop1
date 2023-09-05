package com.example.workshop.service;

import com.example.workshop.dto.AddressBookDTO;
import com.example.workshop.model.AddressBookData;

import java.util.List;

public interface AddressBookService {
    String createNewContact(AddressBookDTO addressBookDTO);

    List<AddressBookData> getAllContacts();

    AddressBookData getContactById(int id);

    AddressBookData updateContactById(int id, AddressBookDTO addressBookDTO);

    String deleteContactById(int id);
}
