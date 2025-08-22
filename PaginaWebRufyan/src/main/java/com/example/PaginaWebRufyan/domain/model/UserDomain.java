package com.example.PaginaWebRufyan.domain.model;



import com.example.PaginaWebRufyan.User.Entity.UserProfilePicture;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserDomain {
    private final Long  id;
    private final FullName fullname;
    private final BirthDate birthDate;
    private final String username;
    private final String email;



    public UserDomain(Long id, FullName fullname,
                      BirthDate birthDate,
                      String username,
                      String email) {
        this.id = id;
        this.fullname = fullname;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;


    }
//private Long profilePictureId;
//private UserValidators userValidators;

/* // estas rela
private Set<Long> likedProductsId;
private Set<Long> purchasesId;
private Set<Long> originalOwnedPaintingId; // no tan precindible
*/


}
