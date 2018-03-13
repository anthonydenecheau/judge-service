package com.scc.enci.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scc.enci.services.JudgeService;
import com.scc.enci.template.BreedObject;
import com.scc.enci.template.JudgeObject;
import com.scc.enci.template.ResponseObjectList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value="v1/judges")
@Api(value="judge selection", description="Return judge data")
public class JudgeServiceController {
   
	@Autowired
    private JudgeService judgeService;
   
    @ApiOperation(value = "View French judges information by kind of show",response = ResponseObjectList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved french judges"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })    
    @RequestMapping(value="/getFrenchJudges/{show}",method = RequestMethod.GET)
    public ResponseObjectList<JudgeObject> getFrenchJudges( 
    		@ApiParam(value = "Show type code", required = true) @PathVariable("show") String show) {
        return judgeService.getFrenchJudges(show);
    }    

    @ApiOperation(value = "Count French judges",response = int.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved french judges"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })    
    @RequestMapping(value="/getFrenchJudgesCount",method = RequestMethod.GET)
    public int getFrenchJudgesCount() {
    	return judgeService.getFrenchJudgesCount();
    }

    @ApiOperation(value = "View International judges information",response = ResponseObjectList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved international judges"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })    
    @RequestMapping(value="/getInternationalJudges",method = RequestMethod.GET)
    public ResponseObjectList<JudgeObject> getInternationalJudges() {
        return judgeService.getInternationalJudges();
    }    
    
    @ApiOperation(value = "View French judge information by id",response = JudgeObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved french judge"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })    
    @RequestMapping(value="/getSingleFrenchJudge/{id}",method = RequestMethod.GET)
    public JudgeObject getFrenchJudgeById( 
    		@ApiParam(value = "Judge id", required = true) @PathVariable("id") int id) {
    	return judgeService.getJudgeById(id);
    }

    @ApiOperation(value = "View International judge information by id",response = JudgeObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved international judge"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })    
    @RequestMapping(value="/getSingleInternationalJudges/{id}",method = RequestMethod.GET)
    public JudgeObject getInternationalJudgeById( 
    		@ApiParam(value = "Judge id", required = true) @PathVariable("id") int id) {
    	return judgeService.getJudgeById(id);
    }

    @ApiOperation(value = "View breeds that the judge is enabled to",response = ResponseObjectList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved breeds"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })    
    @RequestMapping(value="/getEnabledBreeds/{id}/{show}",method = RequestMethod.GET)
    public ResponseObjectList<BreedObject> getEnabledBreeds( 
    		@ApiParam(value = "Judge id", required = true) @PathVariable("id") int id
    		, @ApiParam(value = "Show type code", required = true) @PathVariable("show") String show) {
        return judgeService.getBreedsByIdJudge(id, show);
    }
}
