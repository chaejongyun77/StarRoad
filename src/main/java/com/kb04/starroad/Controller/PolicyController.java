package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.PolicyDto;
import com.kb04.starroad.Dto.ProductDto;
import com.kb04.starroad.Repository.PolicyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class PolicyController {

    private final PolicyRepository policyRepository;
    private static final int ITEMS_PER_PAGE = 3;
    private List<PolicyDto> policyDtos;

    public PolicyController(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @GetMapping("/starroad/policy")
    public ModelAndView policy(Model model, @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex) {

        policyDtos = policyRepository.findAll();
        int startIndex = (pageIndex - 1) * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, policyDtos.size());
        int totalCount = policyDtos.size();

        model.addAttribute("policyList", policyDtos.subList(startIndex, endIndex));
        model.addAttribute("pageEndIndex", Math.ceil(totalCount / (double) ITEMS_PER_PAGE));
        model.addAttribute("currentPage", pageIndex);

        ModelAndView mav = new ModelAndView("policy/policy");

        return mav;
    }

    /*
    @PostConstruct
    public void initializing() {
        for(int i=0; i<24; i++){
            PolicyDto dto = PolicyDto.builder()
                    .name("name" + i)
                    .explain("explain" + i)
                    .link("link" + i)
                    .tag("tag" + i)
                    .location("loca" + i)
                    .build();
            policyRepository.save(dto);
        }
    }
    */
}
