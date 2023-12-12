package tr.com.provera.pameraapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tr.com.provera.pameraapi.dao.RefreshTokenRepository;
import tr.com.provera.pameraapi.dao.model.RefreshToken;
import tr.com.provera.pameraapi.dao.model.User;
import tr.com.provera.pameraapi.dto.UserDto;
import tr.com.provera.pameraapi.service.RefreshTokenService;

import java.util.Date;
import java.util.UUID;

import static tr.com.provera.pameraapi.util.Constant.*;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final ModelMapper modelMapper;


    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, ModelMapper modelMapper) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isRefreshExpired(RefreshToken token) {
        return token.getExpiryDate().before(new Date());
    }

    @Override
    public RefreshToken createRefreshToken(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_SECONDS * 1000));
        refreshTokenRepository.save(token);
        return token;
    }

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}
