package com.aaop.everykid.service;


import com.aaop.everykid.Jwt.TokenUtils;
import com.aaop.everykid.config.StatusCode;
import com.aaop.everykid.dto.LoginPFormDto;
import com.aaop.everykid.dto.RegisterPFormDto;
import com.aaop.everykid.entity.*;
import com.aaop.everykid.dto.TokenResponseDto;
import com.aaop.everykid.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.aaop.everykid.config.StatusCode.*;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository parentRepository;
    private final KindergartenRepository kindergartenRepository;
    private final TokenUtils tokenUtils;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final ChildRepository childRepository;

    public Optional<Parent> findBypID(String PID) {

        return parentRepository.findBypID(PID);
    }


    @Transactional
    public TokenResponseDto signUp(RegisterPFormDto registerPFormDto) {
        Parent parent =
                parentRepository.save(
                        Parent.builder()
                                .pPWD(passwordEncoder.encode(registerPFormDto.getPPWD()))
                                .pID(registerPFormDto.getPID())
                                .pNAME(registerPFormDto.getPNAME())
                                .pEMAIL(registerPFormDto.getPEMAIL())
                                .pPHONE(registerPFormDto.getPPHONE())
                                .kKID(registerPFormDto.getKKID())
                                .tNAME(registerPFormDto.getTNAME())
                                .build());

        String accessToken = tokenUtils.generateJwtToken(parent);
        String refreshToken = tokenUtils.saveRefreshToken(parent);

        authRepository.save(
                Auth.builder().parent(parent).refreshToken(refreshToken).build());

        return TokenResponseDto.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
    }

    @Transactional
    public TokenResponseDto signIn(LoginPFormDto loginFormDto) throws Exception {

        Parent parent =
                parentRepository
                        .findBypID(loginFormDto.getPID())
                        .orElseGet(Parent::new);
        Kindergarten kindergarten =
                kindergartenRepository
                        .findByKKID(parent.getKKID())
                        .orElseGet(Kindergarten::new);
        Auth auth = authRepository
<<<<<<< HEAD
                        .findByParentPKID(parent.getPKID())
                        .orElseGet(Auth::new);
=======
                .findByParentPKID(parent.getPKID())
                .orElseGet(Auth::new);
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e

        Child child = childRepository
                .findByPKID(parent.getPKID()).orElseGet(Child::new);

        if (parent.getPID() == null){
            return TokenResponseDto.builder()
                    .status(NOT_FOUND)
                    .build();
        }
        if (!passwordEncoder.matches(loginFormDto.getPPWD(), parent.getPPWD())) {
            return TokenResponseDto.builder()
                    .status(BAD_REQUEST)
                    .build();
        }
        String accessToken = "";
        String refreshToken = auth.getRefreshToken();

        if (tokenUtils.isValidRefreshToken(refreshToken)) {
            accessToken = tokenUtils.generateJwtToken(auth.getParent());
            return TokenResponseDto.builder()
                    .status(OK)
                    .ACCESS_TOKEN(accessToken)
                    .REFRESH_TOKEN(auth.getRefreshToken())
                    .pNAME(parent.getPNAME())
                    .pPHONE(parent.getPPHONE())
                    .pID(parent.getPID())
                    .pEMAIL(parent.getPEMAIL())
                    .PKID(parent.getPKID())
                    .pEMAIL(parent.getPEMAIL())
                    .KKID(parent.getKKID())
                    .kNAME(kindergarten.getKNAME())
                    .kADDRESS(kindergarten.getKADDRESS())
                    .kPHONE(kindergarten.getKPHONE())
                    .tNAME(parent.getTNAME())
                    .cAGE(child.getCAGE())
                    .cNAME(child.getCNAME())
<<<<<<< HEAD
=======
                    .cIMG(child.getCIMG())
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
                    .build();

        } else {
            accessToken = tokenUtils.generateJwtToken(auth.getParent());
            refreshToken = tokenUtils.saveRefreshToken(parent);
            auth.refreshUpdate(refreshToken);
        }

        return TokenResponseDto.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
    }


    public List<Parent> findUsers() {
        return parentRepository.findAll();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
