package com.mx.edu.tecdesoftware.StreamCore.api.web.controller;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Content;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.service.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contents")
@Tag(name = "Content", description = "Gestión de contenidos (películas y series)")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los contenidos", description = "Regresa la lista completa de contenidos")
    @ApiResponse(responseCode = "200", description = "Contenidos obtenidos correctamente")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<List<Content>> getAll() {
        return ResponseEntity.ok(contentService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener contenido por ID", description = "Regresa un contenido si existe")
    @ApiResponse(responseCode = "200", description = "Contenido encontrado")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Content> getContent(
            @Parameter(description = "ID del contenido", example = "1", required = true)
            @PathVariable("id") int contentId) {
        return contentService.getContent(contentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Obtener contenidos por categoría", description = "Regresa los contenidos de una categoría, ordenados por título")
    @ApiResponse(responseCode = "200", description = "Contenido(s) encontrados en la categoría")
    @ApiResponse(responseCode = "400", description = "ID de categoría inválido")
    @ApiResponse(responseCode = "404", description = "No hay contenidos en esa categoría")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<List<Content>> getByCategory(
            @Parameter(description = "ID de la categoría", example = "1", required = true)
            @PathVariable("categoryId") int categoryId) {
        return contentService.getByCategory(categoryId)
                .filter(contents -> !contents.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Obtener contenidos por tipo", description = "Regresa los contenidos activos de un tipo (P = Película, S = Serie)")
    @ApiResponse(responseCode = "200", description = "Contenido(s) encontrados de ese tipo")
    @ApiResponse(responseCode = "404", description = "No hay contenidos de ese tipo")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<List<Content>> getByType(
            @Parameter(description = "Tipo de contenido", example = "S", required = true)
            @PathVariable("type") String type) {
        return contentService.getByType(type)
                .filter(contents -> !contents.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/state")
    @Operation(
            summary = "Actualizar el estado de un contenido",
            description = "Activa o desactiva un contenido",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            examples = @ExampleObject(name = "Nuevo estado", value = "true")
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Estado actualizado correctamente")
    @ApiResponse(responseCode = "400", description = "Valor de estado inválido")
    @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Content> updateState(
            @Parameter(description = "ID del contenido", example = "1", required = true)
            @PathVariable("id") int contentId,
            @RequestBody Boolean state) {
        return contentService.updateState(contentId, state)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Crear un nuevo contenido",
            description = "Registra una película o serie y la regresa con su ID generado",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            examples = @ExampleObject(
                                    name = "Ejemplo de contenido",
                                    value = """
                                            {
                                                "title": "Stranger Things",
                                                "categoryId": 2,
                                                "type": "S",
                                                "releaseYear": 2016,
                                                "seasons": 4,
                                                "rating": "16+",
                                                "state": true
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Contenido creado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos de contenido inválidos")
    @ApiResponse(responseCode = "409", description = "La categoría indicada no existe")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Content> save(@RequestBody Content content) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contentService.save(content));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar contenido por ID", description = "Elimina un contenido y sus visualizaciones asociadas")
    @ApiResponse(responseCode = "200", description = "Contenido eliminado correctamente")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Boolean> delete(
            @Parameter(description = "ID del contenido a eliminar", example = "1", required = true)
            @PathVariable("id") int contentId) {
        if (contentService.delete(contentId)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}