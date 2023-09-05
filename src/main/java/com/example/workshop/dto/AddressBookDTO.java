package com.example.workshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {

    @NotEmpty
    @Pattern(regexp = "^[A-Z][A-Za-z]{2,}$", message = "Name invalid")
    public String fullName;

    @NotNull
    @Pattern(regexp = "^[0-9]{10}", message = "Phone Number invalid")
    public String phoneNumber;

    @NotNull
    @Email
    public String emailId;
    @NotNull
    public String address;

    @NotNull
    public String state;

    @NotNull
    public String city;

    @NotNull
    public int zip;

}
