package com.nandan.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureTestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminAccess() {
        return ResponseEntity.ok("Hello, Admin! You have access.");
    }

    @GetMapping("/editor")
    @PreAuthorize("hasAnyRole('EDITOR', 'ADMIN')")
    public ResponseEntity<String> editorAccess() {
        return ResponseEntity.ok("Hello, Editor! You're authorized.");
    }

    @GetMapping("/viewer")
    @PreAuthorize("hasAnyRole('VIEWER', 'ADMIN')")
    public ResponseEntity<String> viewerAccess() {
        return ResponseEntity.ok("Hello, Viewer! You're authorized.");
    }
}
