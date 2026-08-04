package com.mx.edu.tecdesoftware.StreamCore.api.web.controller;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Category;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category", description = "Gestión de categorías de contenido")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    @Operation(summary = "Obtener todas las categorías", description = "Regresa la lista completa de categorías")
    @ApiResponse(responseCode = "200", description = "Categorías obtenidas correctamente")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID", description = "Regresa una categoría si existe")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Category> getCategory(
            @Parameter(description = "ID de la categoría", example = "1", required = true)
            @PathVariable("id") int categoryId) {
        return categoryService.getCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Crear una nueva categoría",
            description = "Registra una categoría y la regresa con su ID generado",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            examples = @ExampleObject(
                                    name = "Ejemplo de categoría",
                                    value = """
                                            {
                                                "description": "Acción",
                                                "state": true
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Categoría creada correctamente")
    @ApiResponse(responseCode = "400", description = "Datos de categoría inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Category> save(@Valid @RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría por ID", description = "Elimina una categoría si existe")
    @ApiResponse(responseCode = "200", description = "Categoría eliminada correctamente")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @ApiResponse(responseCode = "409", description = "No se puede eliminar: tiene contenidos asociados")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Boolean> delete(
            @Parameter(description = "ID de la categoría a eliminar", example = "1", required = true)
            @PathVariable("id") int categoryId) {
        if (categoryService.delete(categoryId)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}