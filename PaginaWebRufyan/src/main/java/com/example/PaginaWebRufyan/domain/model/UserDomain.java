package com.example.PaginaWebRufyan.domain.model;



import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDomain {
    private  Long id;
    private final FullName fullname;
    private final BirthDate birthDate;
    private final String email;
    private final String username;

    public UserDomain( FullName fullname, BirthDate birthDate, String email, String username) {

        this.fullname = fullname;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
    }

//private Long profilePictureId;
//private UserValidators userValidators;

/* // estas rela
private Set<Long> likedProductsId;
private Set<Long> purchasesId;
private Set<Long> originalOwnedPaintingId; // no tan precindible
*/


}
