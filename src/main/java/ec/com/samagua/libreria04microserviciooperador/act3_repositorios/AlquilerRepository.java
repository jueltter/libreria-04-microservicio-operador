package ec.com.samagua.libreria04microserviciooperador.act3_repositorios;

import ec.com.samagua.libreria04microserviciooperador.act1_entidades.Alquiler;
import ec.com.samagua.libreria04microserviciooperador.act2_jpa_repositorios.AlquilerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AlquilerRepository {

    private final AlquilerJpaRepository repository;

    public List<Alquiler> findAll() {
        return repository.findAll();

    }

    public Alquiler save(Alquiler alquiler) {
        return repository.save(alquiler);
    }

    public Optional<Alquiler> findById(Long id) {
        return repository.findById(id);

    }


}
