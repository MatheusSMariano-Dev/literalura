package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    List<Autor> findByNascimentoLessThanEqualAndFalecimentoGreaterThanEqual(Integer ano1, Integer ano2);

}