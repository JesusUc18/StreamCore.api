package com.mx.edu.tecdesoftware.StreamCore.api.web.controller;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Subscription;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping("/subscriptions")
@Tag(name = "Subscription", description = "Gestión de suscripciones y sus visualizaciones (Master-Detail)")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las suscripciones", description = "Regresa la lista completa de suscripciones")
    @ApiResponse(responseCode = "200", description = "Suscripciones obtenidas correctamente")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<List<Subscription>> getAll() {
        return new ResponseEntity<>(subscriptionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Obtener suscripciones por usuario", description = "Regresa todas las suscripciones de un usuario")
    @ApiResponse(responseCode = "200", description = "Suscripción(es) encontradas para el usuario")
    @ApiResponse(responseCode = "404", description = "El usuario no tiene suscripciones")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<List<Subscription>> getByUser(
            @Parameter(description = "ID del usuario", example = "USR001", required = true)
            @PathVariable("id") String userId) {
        return subscriptionService.getByUser(userId)
                .map(subscriptions -> new ResponseEntity<>(subscriptions, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener suscripción por ID", description = "Regresa una suscripción con sus visualizaciones")
    @ApiResponse(responseCode = "200", description = "Suscripción encontrada")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Suscripción no encontrada")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Subscription> getSubscription(
            @Parameter(description = "ID de la suscripción", example = "1", required = true)
            @PathVariable("id") int subscriptionId) {
        return subscriptionService.getSubscription(subscriptionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/state")
    @Operation(
            summary = "Actualizar el estado de una suscripción",
            description = "Cambia el estado de una suscripción (ej. ACTIVA, CANCELADA)",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(name = "Nuevo estado", value = "\"C\"")
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Estado actualizado correctamente")
    @ApiResponse(responseCode = "400", description = "Valor de estado inválido")
    @ApiResponse(responseCode = "404", description = "Suscripción no encontrada")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Subscription> updateState(
            @Parameter(description = "ID de la suscripción", example = "1", required = true)
            @PathVariable("id") int subscriptionId,
            @RequestBody String state) {
        return subscriptionService.updateState(subscriptionId, state)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    @Operation(
            summary = "Crear una nueva suscripción",
            description = "Registra una suscripción; si incluye 'viewings', estos se persisten automáticamente en cascada (relación Master-Detail)",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Ejemplo de suscripción",
                                    value = """
                                            {
                                                "userId": "USR001",
                                                "planId": 1,
                                                "startDate": "2026-07-01T00:00:00",
                                                "endDate": "2026-08-01T00:00:00",
                                                "paymentMethod": "Tarjeta de crédito",
                                                "comment": "Renovación automática activada",
                                                "state": "A",
                                                "viewings": [
                                                    {
                                                        "contentId": 3,
                                                        "viewedAt": "2026-07-05T20:30:00",
                                                        "minutesWatched": 45,
                                                        "active": true
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Suscripción (y sus visualizaciones) creada correctamente")
    @ApiResponse(responseCode = "400", description = "Datos de suscripción inválidos")
    @ApiResponse(responseCode = "409", description = "El usuario o el plan indicado no existen")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Subscription> save(@Valid @RequestBody Subscription subscription) {
        return new ResponseEntity<>(subscriptionService.save(subscription), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar suscripción por ID", description = "Elimina una suscripción y sus visualizaciones asociadas")
    @ApiResponse(responseCode = "200", description = "Suscripción eliminada correctamente")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Suscripción no encontrada")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Boolean> delete(
            @Parameter(description = "ID de la suscripción a eliminar", example = "1", required = true)
            @PathVariable("id") int subscriptionId) {
        if (subscriptionService.delete(subscriptionId)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}