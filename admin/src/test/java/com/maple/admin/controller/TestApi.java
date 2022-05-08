package com.maple.admin.controller;

import com.maple.common.exception.MapleBossException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.maple.common.exception.ErrorCode.TEST_HELLO_WORLD;

@RestController
@RequestMapping("/test")
public class TestApi {

    @GetMapping("/ex")
    public void handleException() throws Exception {
        throw new Exception();
    }

    @GetMapping("/runtime-ex")
    public void handleRuntimeException() {
        throw new RuntimeException();
    }

    @GetMapping("/maple-boss-ex")
    public void handleMapleBossException() {
        throw new MapleBossException(TEST_HELLO_WORLD);
    }
}
