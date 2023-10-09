package com.suw0n.tabmark.tabmark.application.command;

public record CreateAttachmentCommand(
        long tabMarkId,
        long tagId) {}