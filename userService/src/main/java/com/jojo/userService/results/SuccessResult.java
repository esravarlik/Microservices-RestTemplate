package com.jojo.userService.results;

public class SuccessResult extends Result {
    public SuccessResult(Result savedUser) {
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);
    }
}
