package ec.com.samagua.libreria04microserviciooperador.act3_repositorios_integracion_ms_buscador;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Libro {
    private Long id;
    private String titulo;
    private Boolean disponibilidad;
}
