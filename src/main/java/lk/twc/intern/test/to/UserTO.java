package lk.twc.intern.test.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTO implements Serializable {

    @NotBlank(message = "Username can't be empty")
    private String userName;

    @NotBlank(message = "Password can't be empty")
    private String userPassword;
}
