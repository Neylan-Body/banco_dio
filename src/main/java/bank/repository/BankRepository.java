package bank.repository;

import bank.domain.model.Bank;
import bank.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
    Optional<Bank> findByName(String name);

    @Query("select b.clients from Bank b where b.name = ?1")
    Optional<List<Client>> findClientsByName(String name);

    void deleteByName(String name);

    boolean existsBankByName(String name);
}
