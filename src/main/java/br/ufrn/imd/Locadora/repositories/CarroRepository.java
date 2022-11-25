package br.ufrn.imd.Locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.imd.Locadora.entity.Carro;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{
    public boolean existsByPlaca(String placa);
    
    public List<Carro> findByPlaca(String placa);

    @Query("SELECT max(c.id) FROM Carro c")
    public Integer findMaxId();
}