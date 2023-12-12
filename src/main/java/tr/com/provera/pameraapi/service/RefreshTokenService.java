package tr.com.provera.pameraapi.service;

import tr.com.provera.pameraapi.dao.model.RefreshToken;
import tr.com.provera.pameraapi.dto.UserDto;

public interface RefreshTokenService {
    public boolean isRefreshExpired(RefreshToken token);

    public RefreshToken createRefreshToken(UserDto userDto);

    public RefreshToken findByToken(String token);
}
