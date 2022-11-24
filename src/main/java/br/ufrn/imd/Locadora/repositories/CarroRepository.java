package br.ufrn.imd.Locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.imd.Locadora.controllers.Carro;;

interface CarroRepository extends JpaRepository<Carro, Long>{
    
}
