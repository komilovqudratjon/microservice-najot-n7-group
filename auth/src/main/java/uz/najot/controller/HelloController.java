package uz.najot.controller;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.najot.exceptions.UserNotFoundEx;
import uz.najot.order.UserIsActive;
import uz.najot.repository.UserRepository;
import uz.najot.service.AuthService;

/**
 * @description: TODO
 * @date: 25 March 2024 $
 * @time: 6:02 PM 40 $
 * @author: Qudratjon Komilov
 */
@RestController
@RequestMapping("/gratings")
@RequiredArgsConstructor
@Slf4j
public class HelloController {

    private final AuthService authService;

    private final UserRepository userRepository;

    @GetMapping("/get-user/{id}")
    public UserIsActive getUser(@PathVariable String id) {
        log.info("ORDER SERVICE");
        return new UserIsActive(userRepository.findById(Long.valueOf(id)).orElseThrow(() -> new UserNotFoundEx("User not found")).isActive());
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello World!";
    }

    @GetMapping("/user")
    @PermitAll
    public String user(@RequestParam String username) {
        userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundEx("User not found"));
        return "Hello World!";
    }


}
