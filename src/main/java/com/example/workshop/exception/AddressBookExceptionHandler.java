package com.example.workshop.exception;

import com.example.workshop.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AddressBookExceptionHandler {

    private static final String message = "Exception While Processing REST Requests";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessages = errorList.stream().map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO(message, errorMessages);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressBookException.class)
    public ResponseEntity<ResponseDTO> handleAddressBookException(AddressBookException exception) {
        ResponseDTO responseDTO = new ResponseDTO(message, exception.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
