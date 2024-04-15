package uz.najot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import uz.najot.order.AuthApi;

/**
 * @description: TODO
 * @date: 25 March 2024 $
 * @time: 6:02 PM 40 $
 * @author: Qudratjon Komilov
 */
@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {


    private final AuthApi authApi;

    @GetMapping("/do")
        public String admin(@RequestParam String name, @RequestParam Long id,@RequestHeader("X-Request-Default-Red") String string) {
//        Boolean forObject = restTemplate.getForObject("http://AUTH-SERVICE/gratings/get-user/" + id, Boolean.class);
        log.info("Header: {}", string);
        Boolean forObject = authApi.getUser(String.valueOf(id)).isActive();
        if (forObject==null) {
            return "User not found";
        }
        if (forObject) {
            return "Order is made "+name;
        }
        return "Hello World!";
    }

}
