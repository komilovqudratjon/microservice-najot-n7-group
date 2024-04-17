package uz.najot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.najot.confing.PrincipleUser;
import uz.najot.entity.Users;
import uz.najot.model.*;
import uz.najot.order.CheckTokenModel;
import uz.najot.service.AuthService;

import java.util.List;

/**
 * @description: TODO
 * @date: 25 March 2024 $
 * @time: 6:02 PM 40 $
 * @author: Qudratjon Komilov
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public JwtTokenDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid  RegisterRequestDTO request) {
        return authService.register(request);
    }

    @PostMapping("/verify/{token}/{code}")
    public JwtTokenDTO verify(@PathVariable String code, @PathVariable String token) {
        return authService.verify(code, token);
    }

    @PostMapping("/refresh-token/{token}")
    public JwtTokenDTO refresh(@PathVariable String token) {
        return authService.refresh(token);
    }

    @GetMapping("/profile-photo")
    public List<FileDTO> getProfilePhoto(@AuthenticationPrincipal PrincipleUser principleUser) {
        return authService.getProfilePhoto(principleUser);
    }

    @PutMapping("/update-user")
    public RegisterResponseDTO updateUser(@RequestBody UpdateUserDTO request, @AuthenticationPrincipal PrincipleUser principleUser) throws Exception {
        return authService.updateUser(request, principleUser);
    }

    @GetMapping("/me")
    public Users me(@AuthenticationPrincipal PrincipleUser principleUser) {
        return authService.me(principleUser);
    }

    @PostMapping("/check-token")
    public boolean checkToken(@RequestBody CheckTokenModel checkTokenModel) {
        return authService.checkToken(checkTokenModel);
    }

}
