package com.suw0n.tabmark.tag.ui;

import com.suw0n.tabmark.tag.application.command.CreateTagCommand;
import com.suw0n.tabmark.tag.application.command.CreateTagUseCase;
import com.suw0n.tabmark.tag.application.command.DeleteTagUseCase;
import com.suw0n.tabmark.tag.application.query.TagInfoResponse;
import com.suw0n.tabmark.tag.application.query.TagQueryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tag API")
@RestController
@RequestMapping(value = "/tag")
@RequiredArgsConstructor
public class TagController {

    private final CreateTagUseCase createTagUseCase;
    private final DeleteTagUseCase deleteTagUseCase;
    private final TagQueryUseCase tagQueryUseCase;

    @Operation(description = "Tag 추가")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody CreateTagCommand command) {
        return createTagUseCase.create(command);
    }

    @Operation(description = "Tag 삭제")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        deleteTagUseCase.delete(id);
    }

    @Operation(description = "내 Tag 조회")
    @GetMapping("/my")
    public List<TagInfoResponse> getByMember() {
        return tagQueryUseCase.getByMember();
    }

}