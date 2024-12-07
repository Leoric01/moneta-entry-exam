package leoric.monetaentrytrial.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskTwoInput {
    // i assume kinda valid values, based on restrictions we can use another annotations like: Positive, Min, Max, or even Pattern

    @NotNull(message = "Input integer cannot be null")
    private Integer inputInt;
}