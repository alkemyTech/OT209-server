package com.alkemy.ong.controller;

import com.alkemy.ong.auth.config.SwaggerConfig;
import com.alkemy.ong.models.request.MemberRequest;
import com.alkemy.ong.models.response.MemberBasicResponse;
import com.alkemy.ong.models.response.MemberResponse;
import javax.validation.Valid;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.MemberService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@CrossOrigin("*")
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.MEMBER_CONTROLLER})

public class MemberController {

    private static final String MEMBER_PATH = "/members";
    @Autowired
    private final MemberService memberService;

    @PostMapping()
    @ApiOperation(value = "Create Member", notes = "Allows user to insert Members")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Members created!")
    })
    public ResponseEntity<MemberResponse> create(@Valid @RequestBody @ApiParam(
            name = "New Members",
            value = "Member to save",
            required = true) MemberRequest memberRequest) {

        boolean responseName = (memberRequest.getName()).matches("/^[a-zA-Z\\u00C0-\\u017F]+$/");

        MemberResponse response = null;
        if (responseName) {
            response = memberService.create(memberRequest);
        } else {
            throw new IllegalArgumentException("Ilegal arguments");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiOperation(value = "Get Members", notes = "Returns all details of Members")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Return the requested news"),
        @ApiResponse(code = 404, message = "No news with requested ID is found"),})
    public ResponseEntity<List<MemberBasicResponse>> getAll() {
        List<MemberBasicResponse> response = memberService.getMembers();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Member By ID", notes = "Allows user to update an existing members by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "News updated!"),
        @ApiResponse(code = 404, message = "No news with requested ID is found"),})
    public ResponseEntity<MemberResponse> update(@PathVariable @ApiParam(
            name = "ID",
            type = "Long",
            value = "ID of the member requested",
            example = "1",
            required = true) Long id, @Valid @RequestBody MemberRequest memberRequest) {
        MemberResponse response = memberService.update(id, memberRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Soft Delete member By ID", notes = "Allows user to delete member by ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "News soft deleted!"),
        @ApiResponse(code = 404, message = "No news with requested ID is found"),})
    public ResponseEntity<Void> delete(@PathVariable @ApiParam(
            name = "ID",
            type = "Long",
            value = "ID of the member requested",
            example = "1",
            required = true) Long id) throws NotFoundException {
        if (memberService.itExists(id)) {
            memberService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
