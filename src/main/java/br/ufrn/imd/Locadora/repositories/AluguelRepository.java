package br.ufrn.imd.Locadora.repositories;

import br.ufrn.imd.Locadora.entity.Aluguel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {

    @Query("SELECT a FROM Aluguel a WHERE a.veiculo_id = :veiculo")
    public List<Aluguel> findAllByVeiculoId(Integer veiculo);

    @Query("SELECT a FROM Aluguel a WHERE a.locatario_id = :locatario")
    public List<Aluguel> findALlByLocatarioId(Integer locatario);
    
    @Query("SELECT max(a.id) FROM Aluguel a")
    public Integer findMaxId();

}