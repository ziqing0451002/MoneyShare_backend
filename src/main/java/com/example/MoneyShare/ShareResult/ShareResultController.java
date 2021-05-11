package com.example.MoneyShare.ShareResult;

import com.example.MoneyShare.ShareList.ShareList;
import com.example.MoneyShare.ShareResult.ShareResultService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(path = "ShareResult")
public class ShareResultController {

    private ShareResultService shareResultService;

    public ShareResultController(ShareResultService shareResultService) {
        this.shareResultService = shareResultService;
    }

    @GetMapping(path = "getShareResult")
    public List<ShareResult> getShareResult(){
        return shareResultService.getShareResult();
    }

    @PostMapping(path = "addShareResult")
    public boolean addShareResult(@RequestBody ShareResult shareResult ){
        return shareResultService.addShareResult(shareResult);
    }

    @DeleteMapping(path = "deletShareResult/{shareListId}")
    public void deletShareResult(@PathVariable("shareListId") BigInteger shareListId , @RequestParam String resultCreater){
        shareResultService.deletShareResult(shareListId,resultCreater);
    }








}
