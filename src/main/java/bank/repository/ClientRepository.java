package bank.repository;

import bank.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<List<Client>> findByName(String name);

    boolean existsClientByName(String name);

    void deleteByName(String name);


}
