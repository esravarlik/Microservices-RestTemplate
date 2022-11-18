package com.jojo.userService;

import com.jojo.userService.results.Result;
import com.jojo.userService.results.SuccessResult;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId) {
        ResponseDto responseDto = userService.getUser(userId);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public Result saveUser(@RequestBody User user) throws Exception {
        Result savedUser = userService.saveUser(user);
        return new SuccessResult(savedUser);
    }
}
