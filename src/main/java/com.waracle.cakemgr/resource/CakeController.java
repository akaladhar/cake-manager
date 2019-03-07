package com.waracle.cakemgr.resource;


import com.waracle.cakemgr.service.CakeManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class CakeController {

    @Autowired
    private CakeManagerService cakeManagerService;

    @RequestMapping(method = RequestMethod.GET)
    public String getListOfCakes(Model model) {
        model.addAttribute("cakes", cakeManagerService.getAllCakes());
        return "cakes";
    }

    @RequestMapping(value = "/addnew", method = RequestMethod.GET)
    public String addNewCakeForm() {
        return "test";
    }

}
