package leoric.monetaentrytrial.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// normally we would use logs but for deployment purpose and simplification i just use this workaround for this task

@Entity
public class ActionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String msg;

}