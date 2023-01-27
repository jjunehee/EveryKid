package com.aaop.everykid.entity;

import com.aaop.everykid.dto.ChildFormDto;
import com.aaop.everykid.dto.RegisterCFormDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name="Child")
@Getter
@Setter
@ToString
public class Child {

    @Id
    @Column(name="C_KID")
    private Long CKID;

    @Column(name ="C_AGE")
    private String cAGE;

    @Column(name ="C_NAME")
    private String cNAME;

    @Column(name ="P_KID")
    private Long PKID;

<<<<<<< HEAD
=======
    @Column(name ="C_IMG")
    private String cIMG;

>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e

    public static Child createChild(RegisterCFormDto registerCFormDto){
        Child child = new Child();
        child.setCNAME(registerCFormDto.getCNAME());
        child.setCAGE(registerCFormDto.getCAGE());
        child.setPKID(registerCFormDto.getPKID());
<<<<<<< HEAD
=======
        child.setCIMG(registerCFormDto.getCIMG());
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
        child.setCKID(registerCFormDto.getPKID());
        return child;
    }


/*    @Transient
    private MultipartFile picture;

    @Column(name = "C_IMG")
    private String pictureUrl;*/
<<<<<<< HEAD
}
=======
}
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
