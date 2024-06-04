package com.ecommerce.price_service.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class OpenApiController {

    @GetMapping("/openapi.yaml")
    public ResponseEntity<String> getOpenApiYaml() throws IOException {
        Path path = Paths.get(new ClassPathResource("openapi.yaml").getURI());
        String yaml = new String(Files.readAllBytes(path));
        return ResponseEntity.ok(yaml);
    }
}
