package com.mx.edu.tecdesoftware.StreamCore.api.domain.repository;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Content;

import java.util.List;
import java.util.Optional;

public interface ContentRepository {

    List<Content> getAll();
    Optional<List<Content>> getByCategory(int categoryId);
    Optional<List<Content>> getByType(String type);
    Optional<Content> getContent(int contentId);
    Content save(Content content);
    void delete(int contentId);
}