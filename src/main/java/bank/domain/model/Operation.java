package bank.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "operation")
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Account account;

    private String operation;
}