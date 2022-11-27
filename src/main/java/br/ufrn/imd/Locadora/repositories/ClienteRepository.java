package br.ufrn.imd.Locadora.repositories;

import br.ufrn.imd.Locadora.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    public boolean existsByCpf(String cpf);
    
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.cpf) LIKE LOWER(CONCAT('%', :cpf,'%')) AND c.ativo=true")
    public List<Cliente> findByCpf(String cpf);

    @Query("SELECT max(c.id) FROM Cliente c")
    public Integer findMaxId();

    @Override
    @Query("SELECT c FROM Cliente c WHERE c.ativo = true")
    public List<Cliente> findAll();
}