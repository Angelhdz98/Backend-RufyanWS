package com.example.PaginaWebRufyan.domain.model;




import com.example.PaginaWebRufyan.Security.Roles.RoleEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserDomain {

    public static Integer MIN_USERNAME_LENGTH = 3;

    private final Long  id;
    @Embedded
    private final FullName fullname;
    private final BirthDate birthDate;
    private final String username;
    private final String email;
    private final String hashedPassword;
    private final RoleEnum role;


    //Normal constructor for controllers
    public UserDomain(Long id, FullName fullname,
                      BirthDate birthDate,
                      String username,
                      String email,
                      String password) {
        this.id = id;
        this.fullname = fullname;
        this.hashedPassword = password;
        if(!birthDate.isAdult())  throw new IllegalArgumentException("El usuario debe ser mayor de edad");
        this.birthDate = birthDate;
        checkValidUsername(username);
        this.username = username;
        checkValidEmail(email);
        this.email = email;
        this.role = RoleEnum.ROLE_CLIENT;

    }
    //Just for user seed to create the unique admin
    public UserDomain(Long id, FullName fullname, BirthDate birthDate, String username, String email, String hashedPassword, RoleEnum role) {
        this.id = id;
        this.fullname = fullname;
        if(!birthDate.isAdult())  throw new IllegalArgumentException("El usuario debe ser mayor de edad");
        this.birthDate = birthDate;
        checkValidUsername(username);
        this.username = username;
        checkValidEmail(email);
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    private static void checkValidUsername(String username) {
        if(username.length()<MIN_USERNAME_LENGTH|| !username.matches("^[a-zA-Z0-9._]+$")) throw new IllegalArgumentException("El nombre de usuario debe tener un minimo de " + MIN_USERNAME_LENGTH + " caracteres y solo puede contener letras, numeros, puntos y guiones bajos el username: " + username + " no cumple las caracteristicas");
    }

    private static void checkValidEmail(String email) {
        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) throw new IllegalArgumentException("El email no es valido");
    }

//private Long profilePictureId;
//private UserValidators userValidators;

/* // estas rela
private Set<Long> likedProductsId;
private Set<Long> purchasesId;
private Set<Long> originalOwnedPaintingId; // no tan precindible
*/


}
