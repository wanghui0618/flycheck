package com.dhcc.piccbid.web.controller.flyTreatmentFeeCount;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonalInformationInquiryController {
    @RequestMapping("/flyTreatmentFeeCount/personalInformationInquiry")
    public String flyTreatmentFeeCount() {
        return "flyTreatmentFeeCount/personalInformationInquiry";
    }
    @RequestMapping("/flyTreatmentFeeCount/detailedInformation")
    public String detailedInformation() {
        return "flyTreatmentFeeCount/detailedInformation";
    }
}
