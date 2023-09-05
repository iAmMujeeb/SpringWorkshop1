package com.example.workshop.service;

import com.example.workshop.dto.AddressBookDTO;
import com.example.workshop.exception.AddressBookException;
import com.example.workshop.model.AddressBookData;
import com.example.workshop.repository.AddressBookRepository;
import com.example.workshop.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImplement implements AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private JwtToken jwtToken;

    @Override
    public String createNewContact(AddressBookDTO addressBookDTO) {
        List<AddressBookData> list = addressBookRepository.findByEmailId(addressBookDTO.emailId);
        AddressBookData addressBookData = new AddressBookData(addressBookDTO);
        if (list.isEmpty()) {
            addressBookRepository.save(addressBookData);
            return jwtToken.createToken(addressBookData.getId());
        } else {
            throw new AddressBookException("Email Id Already Present");
        }
    }

    @Override
    public List<AddressBookData> getAllContacts() {
        return addressBookRepository.findAll();
    }

    @Override
    public AddressBookData getContactById(int id) {
        return addressBookRepository.findById(id).orElseThrow(() -> new AddressBookException("Contact not found"));
    }

    @Override
    public AddressBookData updateContactById(int id, AddressBookDTO addressBookDTO) {
        AddressBookData addressBookData = getContactById(id);
        if (addressBookData != null) {
            addressBookData.setFullName(addressBookDTO.fullName);
            addressBookData.setPhoneNumber(addressBookDTO.phoneNumber);
            addressBookData.setAddress(addressBookDTO.address);
            addressBookData.setCity(addressBookDTO.city);
            addressBookData.setState(addressBookDTO.state);
            addressBookData.setZip(addressBookDTO.zip);
            addressBookData.setEmailId(addressBookDTO.emailId);
            return addressBookRepository.save(addressBookData);
        }
        return null;
    }

    @Override
    public String deleteContactById(int id) {
        AddressBookData addressBookData = getContactById(id);
        if (addressBookData != null) {
            addressBookRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Failed!";
    }

}
