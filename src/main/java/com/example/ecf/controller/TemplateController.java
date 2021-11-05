package com.example.ecf.controller;

import com.example.ecf.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateController {
    @Autowired
    private PatientService patientService;

    @GetMapping({"/", "", "/index"})
    public ModelAndView getHomePage(ModelAndView modelAndView) {
        modelAndView.addObject("patients",patientService.findAllPatients());
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
