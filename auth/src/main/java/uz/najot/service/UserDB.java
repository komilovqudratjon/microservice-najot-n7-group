package uz.najot.service;

import uz.najot.entity.Users;

/**
 * @description: TODO
 * @date: 26 March 2024 $
 * @time: 7:11 PM 34 $
 * @author: Qudratjon Komilov
 */

public interface UserDB {

    Users getUserById(Long id);
}
