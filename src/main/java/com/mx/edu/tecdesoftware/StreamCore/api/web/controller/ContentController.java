package com.mx.edu.tecdesoftware.StreamCore.api.web.controller;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Content;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("")
    public ResponseEntity<List<Content>> getAll() {
        return ResponseEntity.ok(contentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getContent(@PathVariable("id") int contentId) {
        return contentService.getContent(contentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Content>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return contentService.getByCategory(categoryId)
                .filter(contents -> !contents.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Content>> getByType(@PathVariable("type") String type) {
        return contentService.getByType(type)
                .filter(contents -> !contents.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/state")
    public ResponseEntity<Content> updateState(@PathVariable("id") int contentId, @RequestBody Boolean state) {
        return contentService.updateState(contentId, state)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Content> save(@RequestBody Content content) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contentService.save(content));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int contentId) {
        if (contentService.delete(contentId)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}