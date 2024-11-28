package leoric.monetaentrytrial.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskOneInput {
    @NotBlank(message = "input field is mandatory ( String/text )")
    private String input;
}