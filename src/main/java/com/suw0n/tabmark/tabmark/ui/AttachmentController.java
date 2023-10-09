package com.suw0n.tabmark.tabmark.ui;

import com.suw0n.tabmark.tabmark.application.command.CreateAttachmentCommand;
import com.suw0n.tabmark.tabmark.application.command.CreateAttachmentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Attachment API")
@RestController
@RequestMapping(value = "/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final CreateAttachmentUseCase createAttachmentUseCase;

    @Operation(description = "Attachment 추가")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody CreateAttachmentCommand command) {
        return createAttachmentUseCase.create(command);
    }

}