package com.nandan.auth.strategy;

public interface AuthStrategy {
    String authenticateAndIssueToken(String username, String password);
}
