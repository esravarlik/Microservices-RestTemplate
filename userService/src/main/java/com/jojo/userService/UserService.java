package com.jojo.userService;

import com.jojo.userService.results.ErrorResult;
import com.jojo.userService.results.Result;
import com.jojo.userService.results.SuccessDataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class UserService {

    static String baseUrl = "http://localhost:8080/api/addresses";
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public Result saveUser(User user) throws Exception {
        ResponseEntity<AddressDto> saveResponse = restTemplate
                .getForEntity(baseUrl + "/" + user.getAddressId(),
                        AddressDto.class);

        Long addressId = saveResponse.getBody().getId();
        if (!addressId.equals(user.getAddressId())) {
            return new ErrorResult("Address id not found.");
        }

        User toSave = new User();
        toSave.setEmail(toSave.getEmail());
        toSave.setFirstName(toSave.getFirstName());
        toSave.setLastName(user.getLastName());
        toSave.setAddressId(user.getAddressId());

        return new SuccessDataResult<>(userRepository.save(user));
    }

    public ResponseDto getUser(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);

        ResponseEntity<AddressDto> response = restTemplate
                .getForEntity(baseUrl + "/" + user.getAddressId(),
                        AddressDto.class);

        AddressDto addressDto = response.getBody();

        System.out.println(response.getStatusCode());

        responseDto.setUser(userDto);
        responseDto.setAddress(addressDto);
        return responseDto;
    }

    private UserDto mapToUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
