package com.example.workshop.controller;

import com.example.workshop.dto.AddressBookDTO;
import com.example.workshop.dto.ResponseDTO;
import com.example.workshop.model.AddressBookData;
import com.example.workshop.repository.AddressBookRepository;
import com.example.workshop.service.AddressBookService;
import com.example.workshop.token.JwtToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AddressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private JwtToken jwtToken;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createNewContact(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        String token = null;
        token = addressBookService.createNewContact(addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Created new contact successfully", token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllContacts() {
        List<AddressBookData> addressBookDataList = null;
        addressBookDataList = addressBookService.getAllContacts();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successfull", addressBookDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{token}")
    public ResponseEntity<ResponseDTO> getContactByToken(@PathVariable("token") String token) {
        int id = jwtToken.decodeToken(token);
        AddressBookData addressBookData = null;
        addressBookData = addressBookService.getContactById(id);
        ResponseDTO responseDTO = new ResponseDTO("Get Call for Token Successfull", addressBookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDTO> updateContactByToken(@PathVariable("token") String token, @RequestBody AddressBookDTO addressBookDTO) {
        int id = jwtToken.decodeToken(token);
        AddressBookData addressBookData = null;
        addressBookData = addressBookService.updateContactById(id, addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Contact Successfully", addressBookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{token}")
    public ResponseEntity<ResponseDTO> deleteContactByToken(@PathVariable("token") String token) {
        int id = jwtToken.decodeToken(token);
        addressBookService.deleteContactById(id);
        ResponseDTO responseDTO = new ResponseDTO("Contact Deleted Successfully", "Delete id: " + id);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

}
