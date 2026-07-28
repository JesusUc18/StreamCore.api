package com.mx.edu.tecdesoftware.StreamCore.api.domain.service;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Content;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public List<Content> getAll() {
        return contentRepository.getAll();
    }

    public Optional<Content> getContent(int contentId) {
        return contentRepository.getContent(contentId);
    }

    public Optional<List<Content>> getByCategory(int categoryId) {
        return contentRepository.getByCategory(categoryId);
    }

    public Optional<List<Content>> getByType(String type) {
        return contentRepository.getByType(type);
    }

    public Content save(Content content) {
        return contentRepository.save(content);
    }

    public boolean delete(int contentId) {
        return getContent(contentId).map(content -> {
            contentRepository.delete(contentId);
            return true;
        }).orElse(false);
    }

    public Optional<Content> updateState(int contentId, boolean state) {
        return contentRepository.updateState(contentId, state);
    }
}