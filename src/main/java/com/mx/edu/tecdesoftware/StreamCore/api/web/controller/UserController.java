package com.mx.edu.tecdesoftware.StreamCore.api.web.controller;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.User;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Gestión de usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los usuarios", description = "Regresa la lista completa de usuarios")
    @ApiResponse(responseCode = "200", description = "Usuarios obtenidos correctamente")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Regresa un usuario si existe")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<User> getUser(
            @Parameter(description = "ID del usuario", example = "USR001", required = true)
            @PathVariable("id") String userId) {
        return userService.getUser(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Registra un usuario",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Ejemplo de usuario",
                                    value = """
                                            {
                                                "userId": "USR001",
                                                "name": "Ana",
                                                "lastName": "García López",
                                                "phone": 5512345678,
                                                "address": "Av. Reforma 123, CDMX",
                                                "email": "ana.garcia@example.com"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos")
    @ApiResponse(responseCode = "409", description = "Ya existe un usuario con ese ID")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un usuario",
            description = "Actualiza los datos de un usuario existente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Ejemplo de actualización",
                                    value = """
                                            {
                                                "name": "Ana",
                                                "lastName": "García López",
                                                "phone": 5512345678,
                                                "address": "Av. Reforma 456, CDMX",
                                                "email": "ana.garcia@example.com"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<User> update(
            @Parameter(description = "ID del usuario a actualizar", example = "USR001", required = true)
            @PathVariable("id") String userId,
            @RequestBody User user) {
        return userService.update(userId, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario por ID", description = "Elimina un usuario si existe")
    @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @ApiResponse(responseCode = "409", description = "No se puede eliminar: tiene suscripciones asociadas")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<Boolean> delete(
            @Parameter(description = "ID del usuario a eliminar", example = "USR001", required = true)
            @PathVariable("id") String userId) {
        if (userService.delete(userId)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}