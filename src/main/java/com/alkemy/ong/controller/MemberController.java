package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.request.MemberRequest;
import com.alkemy.ong.models.response.*;

import javax.validation.Valid;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/members")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MemberController {

    private static final String MEMBERS_PATH = "/members";
    @Autowired
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<PageMemberResponse> getAll(
            @RequestParam(value = "page") int offset,
            UriComponentsBuilder uriComponentsBuilder
    ){

        PageMemberResponse response = memberService.getMember(offset, uriComponentsBuilder.path(MEMBERS_PATH));
        return ResponseEntity.ok(response);
    }
    @PostMapping("/create")
    public ResponseEntity<MemberResponse> create(@Valid @RequestBody MemberRequest memberRequest) {

        boolean responseName = (memberRequest.getName()).matches("(^[[a-zA-Z]+(\\-|\\ )?]+)$");
        MemberResponse response=null;
        if(responseName) {
            response = memberService.create(memberRequest);
        }else {
            throw new IllegalArgumentException("Ilegal arguments");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

    @GetMapping("/list")
    public ResponseEntity<List<MemberBasicResponse>> getAll(){
        List<MemberBasicResponse> response = memberService.getMembers();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MemberResponse> update (@PathVariable Long id, @Valid @RequestBody MemberRequest memberRequest){
        MemberResponse response = memberService.update(id, memberRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping(value = "/delete/{id}", produces = {"application/json"})
    public ResponseEntity<Void> delete(@PathVariable Long id)throws NotFoundException {
        if(memberService.itExists(id)){
            memberService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
