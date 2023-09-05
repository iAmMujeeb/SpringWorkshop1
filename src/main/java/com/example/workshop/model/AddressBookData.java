package com.example.workshop.model;

import com.example.workshop.dto.AddressBookDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AddressBookData {

    @Id
    @GeneratedValue
    private int id;


    private String fullName;

    private String phoneNumber;

    private String emailId;

    private String address;

    private String state;

    private String city;

    private int zip;

    public AddressBookData(AddressBookDTO addressBookDTO) {
        this.fullName = addressBookDTO.fullName;
        this.phoneNumber = addressBookDTO.phoneNumber;
        this.emailId = addressBookDTO.emailId;
        this.address = addressBookDTO.address;
        this.state = addressBookDTO.state;
        this.city = addressBookDTO.city;
        this.zip = addressBookDTO.zip;
    }

}
