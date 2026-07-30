package com.mx.edu.tecdesoftware.StreamCore.api.web.controller;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Plan;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.service.PlanService;
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
@RequestMapping("/plans")
@Tag(name = "Plan", description = "Gestión de planes de suscripción")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los planes", description = "Regresa la lista completa de planes")
    @ApiResponse(responseCode = "200", description = "Planes obtenidos correctamente")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<List<Plan>> getAll() {
        return ResponseEntity.ok(planService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener plan por ID", description = "Regresa un plan si existe")
    @ApiResponse(responseCode = "200", description = "Plan encontrado")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Plan no encontrado")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Plan> getPlan(
            @Parameter(description = "ID del plan", example = "1", required = true)
            @PathVariable("id") int planId) {
        return planService.getPlan(planId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Crear un nuevo plan",
            description = "Registra un plan de suscripción y lo regresa con su ID generado",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            examples = @ExampleObject(
                                    name = "Ejemplo de plan",
                                    value = """
                                            {
                                                "name": "Premium",
                                                "description": "Acceso a contenido en 4K en hasta 4 pantallas simultáneas",
                                                "monthlyPrice": 249.99,
                                                "simultaneousScreens": 4,
                                                "videoQuality": "4K",
                                                "state": true
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Plan creado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos de plan inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Plan> save(@RequestBody Plan plan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planService.save(plan));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar plan por ID", description = "Elimina un plan si existe")
    @ApiResponse(responseCode = "200", description = "Plan eliminado correctamente")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Plan no encontrado")
    @ApiResponse(responseCode = "409", description = "No se puede eliminar: tiene suscripciones asociadas")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Boolean> delete(
            @Parameter(description = "ID del plan a eliminar", example = "1", required = true)
            @PathVariable("id") int planId) {
        if (planService.delete(planId)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}