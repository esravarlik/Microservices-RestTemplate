package com.jojo.addressService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public ResponseEntity<Address> getAddressById(int addressId) {
        if (this.addressRepository.findAll().size() > 0) {
            return new ResponseEntity<>(addressRepository.findById(addressId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public ResponseEntity<Address> update(int id, Address address) {
        Optional<Address> toUpdate = addressRepository.findById(id);
        if (toUpdate.isPresent()) {
            toUpdate.get().setFullAddress(address.getFullAddress());
            toUpdate.get().setCountry(address.getCountry());
            toUpdate.get().setCity(address.getCity());
            toUpdate.get().setPostalCode(address.getPostalCode());
            return new ResponseEntity<>(addressRepository.save(toUpdate.get()),HttpStatus.CREATED);
        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public void delete(int id) {
        addressRepository.deleteById(id);
    }
}
