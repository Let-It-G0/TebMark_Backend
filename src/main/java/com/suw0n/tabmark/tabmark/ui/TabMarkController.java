package com.suw0n.tabmark.tabmark.ui;

import com.suw0n.tabmark.common.dtos.request.PagingRequest;
import com.suw0n.tabmark.common.dtos.response.PageDataResponse;
import com.suw0n.tabmark.tabmark.application.command.CreateTabMarkCommand;
import com.suw0n.tabmark.tabmark.application.command.CreateTabMarkUseCase;
import com.suw0n.tabmark.tabmark.application.command.DeleteTabMarkUseCase;
import com.suw0n.tabmark.tabmark.application.query.response.TabMarkInfoResponse;
import com.suw0n.tabmark.tabmark.application.query.TabMarkQueryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TabMark API")
@RestController
@RequestMapping(value = "/tabmark")
@RequiredArgsConstructor
public class TabMarkController {

    private final CreateTabMarkUseCase createTabMarkUseCase;
    private final DeleteTabMarkUseCase deleteTabMarkUseCase;
    private final TabMarkQueryUseCase tabMarkQueryUseCase;

    @Operation(description = "TabMark 추가")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody CreateTabMarkCommand command) {
        return createTabMarkUseCase.create(command);
    }

    @Operation(description = "TabMark 삭제")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        deleteTabMarkUseCase.delete(id);
    }

    @Operation(description = "최근 추가된 TabMark 조회")
    @GetMapping("/recent")
    public PageDataResponse<List<TabMarkInfoResponse>> getAll(@ModelAttribute PagingRequest request) {
        return tabMarkQueryUseCase.getRecent(request);
    }

    @Operation(description = "Title로 TabMark 검색")
    @GetMapping
    public PageDataResponse<List<TabMarkInfoResponse>> searchByTitle(@RequestParam(name = "title") String title, @ModelAttribute PagingRequest request) {
        return tabMarkQueryUseCase.searchByTitle(title, request);
    }

    @Operation(description = "Tag로 TabMark 조회")
    @GetMapping("/by/{tagId}")
    public PageDataResponse<List<TabMarkInfoResponse>> getByTag(@ModelAttribute PagingRequest request, @PathVariable long tagId) {
        return tabMarkQueryUseCase.getByTag(request, tagId);
    }

    @Operation(description = "Untagged TabMark 조회")
    @GetMapping("/untagged")
    public PageDataResponse<List<TabMarkInfoResponse>> getUntagged(@ModelAttribute PagingRequest request) {
        return tabMarkQueryUseCase.getUntagged(request);
    }

}