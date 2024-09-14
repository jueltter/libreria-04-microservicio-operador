package ec.com.samagua.libreria04microserviciooperador.act5_controladores_dtos;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlquilerDto {
    private Long id;
    private String cedula;
    private String nombre;
    private Date fecha;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Long> libros;
    private String estado;

}
