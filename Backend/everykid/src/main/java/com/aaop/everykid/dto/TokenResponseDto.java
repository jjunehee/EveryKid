package com.aaop.everykid.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponseDto {
    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;
    private Long PKID;
    private Long KKID;
    private String pID;
    private String pNAME;
    private String pPHONE;
    private String pEMAIL;
    private int status;
    private String kNAME;
    private String kPHONE;
    private String kADDRESS;
    private String tNAME;
    private String cNAME;
    private String cAGE;
<<<<<<< HEAD
}
=======
    private String cIMG;
}
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
