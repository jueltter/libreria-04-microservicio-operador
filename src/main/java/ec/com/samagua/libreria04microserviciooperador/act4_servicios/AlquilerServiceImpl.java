package ec.com.samagua.libreria04microserviciooperador.act4_servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import ec.com.samagua.libreria04microserviciooperador.act1_entidades.Alquiler;
import ec.com.samagua.libreria04microserviciooperador.act3_repositorios.AlquilerRepository;
import ec.com.samagua.libreria04microserviciooperador.act3_repositorios_integracion_ms_buscador.Libro;
import ec.com.samagua.libreria04microserviciooperador.act3_repositorios_integracion_ms_buscador.LibroFacade;
import ec.com.samagua.libreria04microserviciooperador.act5_controladores_dtos.AlquilerDto;
import ec.com.samagua.libreria04microserviciooperador.act5_controladores_dtos.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    @Autowired
    private AlquilerRepository repository;

    @Autowired
    private LibroFacade libroFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Resultado<Alquiler> create(AlquilerDto dto) {
        List<Libro> libros = dto.getLibros().stream().map(libroFacade::findById).toList();
        boolean librosDisponibles = libros.stream().allMatch(Libro::getDisponibilidad);

        if (!librosDisponibles) {
            return new Resultado<>(null, Collections.singletonList("libros no disponibles"));
        }

        Alquiler entity = repository.save(Alquiler.builder()
                .cedula(dto.getCedula())
                .nombre(dto.getNombre())
                .fecha(dto.getFecha())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .libros(libros.stream().map(Libro::getId).toList())
                .estado(dto.getEstado())
                .build());

        libros.forEach(obj -> libroFacade.establecerDisponibilidad(obj.getId(),false));

        return new Resultado<>(entity, Collections.emptyList());
    }

    @Override
    public Resultado<Alquiler> updatePatch(Long id, String patch) {
        try {
            Alquiler entity = repository.findById(id).get();

            JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(patch));
            JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(entity)));
            Alquiler patched = objectMapper.treeToValue(target, Alquiler.class);
            repository.save(patched);
            return new Resultado<>(patched, Collections.emptyList());
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Resultado<List<Alquiler>> findAll() {
        List<Alquiler> entities = repository.findAll();
        return new Resultado<>(entities, Collections.emptyList());
    }

    @Override
    public Resultado<Alquiler> search(Long id) {
        if (id == null) {
            return new Resultado<>(null, Collections.singletonList("id es obligatorio"));
        }

        Alquiler entity = repository.findById(id).orElse(null);

        if (entity == null) {
            return new Resultado<>(null, Collections.singletonList("id es incorrecto"));
        }

        return new Resultado<>(entity, Collections.emptyList());
    }
}
