package com.example.MoneyShare.ShareMember;

import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(path = "ShareMember")
public class ShareMemberController {
    private ShareMemberService shareMemberService;

    public ShareMemberController(ShareMemberService shareMemberService) {
        this.shareMemberService = shareMemberService;
    }

    @GetMapping(path = "getMemberList")
    public List<ShareMember> getMemberList(){
        return shareMemberService.getShareMember();
    }

    @PostMapping(path = "addshareMember")
    public void registerNewUser(@RequestBody ShareMember shareMember){
        shareMemberService.addShareMember(shareMember);
    }

    @DeleteMapping(path = "deletShareMember/{memberId}")
    public void deletShareMember(@PathVariable("memberId") BigInteger memberId ,
                               @RequestParam BigInteger shareListId){
        shareMemberService.deletShareMember(memberId,shareListId);
    }

    @PutMapping(path = "/upShareMemberInfo/{memberId}")
    public void upShareMemberInfo(@PathVariable("memberId")  BigInteger memberId,
                                  @RequestParam String memberName,
                                  @RequestParam BigInteger shareListId
    ){
        shareMemberService.upShareMemberInfo(memberId,memberName,shareListId);
    }


}
