package lk.twc.intern.test.to;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTO implements Serializable {

    @NotBlank(message = "Username can't be empty")
    private String userName;

    @NotBlank(message = "Password can't be empty")
    private String userPassword;
}
