package com.example.MoneyShare.ShareMember;

import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "ShareMember")
public class ShareMemberController {
    private ShareMemberService shareMemberService;

    public ShareMemberController(ShareMemberService shareMemberService) {
        this.shareMemberService = shareMemberService;
    }

    @GetMapping(path = "getMember")
    public List<ShareMember> getMember(){
        return shareMemberService.getShareMember();
    }

    @GetMapping(path = "getMemberById")
    public List<ShareMember> getMemberById(@RequestParam BigInteger shareListId){
        return shareMemberService.getShareMemberById(shareListId);
    }

    @PostMapping(path = "addshareMember")
    public boolean addShareMember(@RequestBody ShareMember shareMember){
        return shareMemberService.addShareMember(shareMember);
    }

    @PostMapping(path = "addshareMemberLoop")
    public boolean addShareMemberLoop(@RequestParam String memberList,@RequestParam BigInteger shareListId,@RequestParam String listCreater){
        return shareMemberService.addreShareMemberLoop(memberList,shareListId,listCreater);
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

    @GetMapping(path = "getResult/{shareListId}")
    public void resultCalculate(@PathVariable("shareListId")  BigInteger shareListId){
        shareMemberService.resultCalculate(shareListId);
    }


}
