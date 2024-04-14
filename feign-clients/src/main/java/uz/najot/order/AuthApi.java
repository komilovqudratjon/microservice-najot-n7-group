package uz.najot.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: TODO
 * @date: 07 April 2024 $
 * @time: 8:31 PM 12 $
 * @author: Qudratjon Komilov
 */
@FeignClient("AUTH-SERVICE")
public interface AuthApi {
    @GetMapping("/gratings/get-user/{id}")
    UserIsActive getUser(@PathVariable(value = "id") String id);
}
