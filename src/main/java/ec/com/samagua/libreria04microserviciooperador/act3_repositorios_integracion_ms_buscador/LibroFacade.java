package ec.com.samagua.libreria04microserviciooperador.act3_repositorios_integracion_ms_buscador;

import jakarta.annotation.PostConstruct;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class LibroFacade {

    @Value("${getLibro.url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    public Libro findById(Long id) {
        String url = String.format(baseUrl, id);
        log.info("Getting libro with ID {}. Request to {}", id, url);
        return restTemplate.getForObject(url, Libro.class);
    }

    public Libro establecerDisponibilidad(Long id, boolean disponibilidad) {
        String url = String.format(baseUrl, id);
        return restTemplate.patchForObject(url, Disponibilidad.builder().disponibilidad(disponibilidad).build(), Libro.class);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class Disponibilidad {
        private boolean disponibilidad;

    }
}
