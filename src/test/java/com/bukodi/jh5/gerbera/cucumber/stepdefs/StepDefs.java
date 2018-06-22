package com.bukodi.jh5.gerbera.cucumber.stepdefs;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = JHipster5TestApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
