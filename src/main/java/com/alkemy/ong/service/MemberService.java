package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.models.request.MemberRequest;
import com.alkemy.ong.models.response.MemberBasicResponse;
import com.alkemy.ong.models.response.MemberResponse;
import com.alkemy.ong.models.response.PageMemberResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface MemberService {

    boolean itExists(Long id);
    public PageMemberResponse getMember(int offset, UriComponentsBuilder uriComponentsBuilder);
    public MemberResponse create(MemberRequest member);
    public MemberResponse update(Long id, MemberRequest member);
    public List<MemberBasicResponse> getMembers();
    public void delete(Long id)throws NotFoundException;

}
