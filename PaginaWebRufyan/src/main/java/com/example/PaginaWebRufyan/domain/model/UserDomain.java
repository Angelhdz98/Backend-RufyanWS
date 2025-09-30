package com.example.PaginaWebRufyan.domain.model;




import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import lombok.Getter;

@Getter
public class UserDomain {

    public static Integer MIN_USERNAME_LENGTH = 3;

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
        if(!birthDate.isAdult())  throw new IllegalArgumentException("El usuario debe ser mayor de edad");
        this.birthDate = birthDate;
        if(username.length()<MIN_USERNAME_LENGTH|| !username.matches("^[a-zA-Z0-9._]+$")) throw new IllegalArgumentException("El nombre de usuario debe tener un minimo de " + MIN_USERNAME_LENGTH + " caracteres y solo puede contener letras, numeros, puntos y guiones bajos el username: " + username+ " no cumple las caracteristicas");
        this.username = username;
        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) throw new IllegalArgumentException("El email no es valido");
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
