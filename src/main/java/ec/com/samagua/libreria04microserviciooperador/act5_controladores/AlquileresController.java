package ec.com.samagua.libreria04microserviciooperador.act5_controladores;

import ec.com.samagua.libreria04microserviciooperador.act1_entidades.Alquiler;
import ec.com.samagua.libreria04microserviciooperador.act3_repositorios_integracion_ms_buscador.Libro;
import ec.com.samagua.libreria04microserviciooperador.act4_servicios.AlquilerService;
import ec.com.samagua.libreria04microserviciooperador.act5_controladores_dtos.AlquilerDto;
import ec.com.samagua.libreria04microserviciooperador.act5_controladores_dtos.Resultado;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AlquileresController {

    private final AlquilerService service;

    @PostMapping("/alquileres")
    public ResponseEntity<Alquiler> create(@RequestBody AlquilerDto dto) {
        Resultado<Alquiler> resultado = service.create(dto);
        if (!resultado.getErrores().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(resultado.getValor());
    }

    

    @GetMapping("/alquileres")
    public ResponseEntity<List<Alquiler>> findAll() {
        Resultado<List<Alquiler>> resultado = service.findAll();
        return ResponseEntity.ok(resultado.getValor());
    }

    @GetMapping("/alquileres/{id}")
    public ResponseEntity<Alquiler> getById(@PathVariable Long id) {
        Resultado<Alquiler> resultado = service.search(id);
        if(!resultado.getErrores().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.getValor());
    }

    @PatchMapping ("/alquileres/{id}")
    public ResponseEntity<Alquiler> updatePatch(@PathVariable Long id, @RequestBody String patch) {
        Resultado<Alquiler> entity = service.search(id);
        if (!entity.getErrores().isEmpty()) {
            entity.getErrores().forEach(error -> log.info(error));
            return ResponseEntity.badRequest().build();
        }

        Resultado<Alquiler> resultado = service.updatePatch(id, patch);
        if(!resultado.getErrores().isEmpty()) {
            resultado.getErrores().forEach(error -> log.info(error));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(resultado.getValor());
    }
}
