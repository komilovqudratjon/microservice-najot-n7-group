package uz.najot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.najot.order.AuthApi;
import uz.najot.order.CheckTokenModel;

/**
 * @description: TODO
 * @date: 17 April 2024 $
 * @time: 6:03 PM 08 $
 * @author: Qudratjon Komilov
 */
@Service
@RequiredArgsConstructor
public class ComunicateAuthImpl implements ComunicateAuth{

    private final AuthApi authApi;

    @Override
    public boolean getAuthApi(String token) {
        return authApi.checkToken(new CheckTokenModel(token, "order-service"));
    }

}
