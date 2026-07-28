package com.mx.edu.tecdesoftware.StreamCore.api.persistence.repository;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Content;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.ContentRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud.ContenidoCrudRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud.SuscripcionContenidoCrudRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Contenido;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class ContenidoRepository implements ContentRepository {

    @Autowired
    private ContenidoCrudRepository contenidoCrudRepository;

    @Autowired
    private SuscripcionContenidoCrudRepository suscripcionContenidoCrudRepository;

    @Autowired
    private ContentMapper mapper;

    @Override
    public List<Content> getAll() {
        return mapper.toContents((List<Contenido>) contenidoCrudRepository.findAll());
    }

    @Override
    public Optional<List<Content>> getByCategory(int categoryId) {
        List<Contenido> contenidos =
                contenidoCrudRepository.findByIdCategoriaOrderByTituloAsc(Integer.valueOf(categoryId));

        return Optional.of(mapper.toContents(contenidos));
    }

    @Override
    public Optional<List<Content>> getByType(String type) {
        return contenidoCrudRepository.findByTipoAndEstado(type, true)
                .map(contenidos -> mapper.toContents(contenidos));
    }

    @Override
    public Optional<Content> getContent(int contentId) {
        return contenidoCrudRepository.findById(contentId)
                .map(contenido -> mapper.toContent(contenido));
    }

    @Override
    public Content save(Content content) {
        Contenido contenido = mapper.toContenido(content);
        contenido.setIdContenido(null);
        return mapper.toContent(contenidoCrudRepository.save(contenido));
    }

    @Override
    @Transactional
    public void delete(int contentId) {
        contenidoCrudRepository.findById(contentId)
                .orElseThrow(() -> new NoSuchElementException("Contenido no encontrado"));

        suscripcionContenidoCrudRepository.deleteByIdContenido(contentId);
        contenidoCrudRepository.deleteById(contentId);
    }
}