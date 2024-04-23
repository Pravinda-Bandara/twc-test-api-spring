package lk.twc.intern.test.to;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactTO implements Serializable {

    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotBlank(message = "Email can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email")
    private String email;

    @NotNull(message = "Gender can't be empty")
    private String gender;

    @NotBlank(message = "Number can't be empty")
    private String number;

    @Null(message = "User should be empty")
    private Long user;
}
