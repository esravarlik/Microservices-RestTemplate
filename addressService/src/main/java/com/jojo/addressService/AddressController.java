package com.jojo.addressService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAll(){
        List<Address> addresses = addressService.getAll();
        return ResponseEntity.ok(addresses);
    }


    @GetMapping("{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") int addressId) {
        ResponseEntity<Address> address = addressService.getAddressById(addressId);
        return address;
    }

    @PostMapping
    public ResponseEntity<Address> saveDepartment(@RequestBody Address address) {
        Address savedAddress = addressService.saveAddress(address);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable("id") int id,
                                          @RequestBody Address address) {
        return addressService.update(id, address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        addressService.delete(id);
    }
}
