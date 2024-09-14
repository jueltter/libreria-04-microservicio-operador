package ec.com.samagua.libreria04microserviciooperador.act4_servicios;

import ec.com.samagua.libreria04microserviciooperador.act1_entidades.Alquiler;
import ec.com.samagua.libreria04microserviciooperador.act3_repositorios_integracion_ms_buscador.Libro;
import ec.com.samagua.libreria04microserviciooperador.act5_controladores_dtos.AlquilerDto;
import ec.com.samagua.libreria04microserviciooperador.act5_controladores_dtos.Resultado;

import java.util.List;

public interface AlquilerService {

    Resultado<Alquiler> create(AlquilerDto dto);

    Resultado<Alquiler> updatePatch(Long id, String patch);

    Resultado<List<Alquiler>> findAll();

    Resultado<Alquiler> search(Long id);
}
