package com.suw0n.tabmark.tabmark.application.command;

public record CreateTabMarkCommand(
        String title,
        String link) {}