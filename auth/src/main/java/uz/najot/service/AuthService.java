package uz.najot.service;

import uz.najot.confing.PrincipleUser;
import uz.najot.entity.Users;
import uz.najot.model.*;
import uz.najot.order.CheckTokenModel;

import java.util.List;

/**
 * @description: TODO
 * @date: 26 March 2024 $
 * @time: 7:11 PM 34 $
 * @author: Qudratjon Komilov
 */

public interface AuthService {

    JwtTokenDTO login(LoginRequestDTO request);

    String register(RegisterRequestDTO request);

    RegisterResponseDTO updateUser(UpdateUserDTO request, PrincipleUser principleUser) throws Exception;

    JwtTokenDTO verify(String code, String token);

    List<FileDTO> getProfilePhoto(PrincipleUser principleUser);

    Users me(PrincipleUser principleUser);

    JwtTokenDTO refresh(String token);

    boolean checkToken(CheckTokenModel checkTokenModel);
}
