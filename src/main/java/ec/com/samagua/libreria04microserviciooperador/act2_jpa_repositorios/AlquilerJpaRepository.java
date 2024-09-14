package ec.com.samagua.libreria04microserviciooperador.act2_jpa_repositorios;

import ec.com.samagua.libreria04microserviciooperador.act1_entidades.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlquilerJpaRepository  extends JpaRepository<Alquiler, Long>, JpaSpecificationExecutor<Alquiler>  {
}
