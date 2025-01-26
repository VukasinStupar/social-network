package com.example.isaProject.controller;


import com.example.isaProject.dto.ApplicationAnalyticsDTO;
import com.example.isaProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/analitics", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AnaliticsController {

    @Autowired
    private CommentService commentService;

    @GetMapping("applicationAnalytics")
    public ResponseEntity<ApplicationAnalyticsDTO> ApplicationAnalytics(){
        ApplicationAnalyticsDTO newAppAnalystics = commentService.applicationAnalytics();

        if(newAppAnalystics == null){
            return new ResponseEntity<ApplicationAnalyticsDTO>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ApplicationAnalyticsDTO>(newAppAnalystics, HttpStatus.OK);
    }
}
