package br.ufrn.imd.Locadora.repositories;

import br.ufrn.imd.Locadora.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer>{

    public boolean existsByPlaca(String placa);
    
    @Query("SELECT c FROM Carro c WHERE LOWER(c.placa) LIKE LOWER(CONCAT('%', :placa,'%')) AND c.status != 'Vendido'")
    public List<Carro> findByPlaca(String placa);
    
    @Query("SELECT max(c.id) FROM Carro c")
    public Integer findMaxId();
    
    @Override
    @Query("SELECT c FROM Carro c WHERE c.status != 'Vendido' and c.ativo = true")
    public List<Carro> findAll();
}