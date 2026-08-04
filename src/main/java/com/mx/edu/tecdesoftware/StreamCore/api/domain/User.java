package com.mx.edu.tecdesoftware.StreamCore.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    @NotBlank(message = "el userId es obligatorio")
    private String userId;

    @NotBlank(message = "el nombre es obligatorio")
    private String name;

    @NotBlank(message = "los apellidos son obligatorios")
    private String lastName;

    private Long phone;

    private String address;

    @NotBlank(message = "el correo es obligatorio")
    @Email(message = "el correo no tiene un formato válido")
    private String email;

    // write-only: se acepta en el JSON de entrada pero nunca se regresa en las respuestas
    @NotBlank(message = "la contraseña es obligatoria")
    @Size(min = 4, message = "la contraseña debe tener al menos 4 caracteres")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}