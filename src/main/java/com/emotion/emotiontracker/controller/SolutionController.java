package com.emotion.emotiontracker.controller;

import com.emotion.emotiontracker.dto.GetSolutionsResponse;
import com.emotion.emotiontracker.dto.SolutionDto;
import com.emotion.emotiontracker.service.SolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solution")
public class SolutionController {

    private final SolutionService solutionService;

    @PostMapping("/add")
    public void addSolution(@RequestBody SolutionDto solutionDto) {
        solutionService.createSolution(solutionDto);
    }

    @GetMapping("/select")
    public GetSolutionsResponse selectSolutions() {
        List<String> solutionNames = solutionService.selectSolutions();
        GetSolutionsResponse getSolutionsResponse = new GetSolutionsResponse(solutionNames);
        return getSolutionsResponse;
    }

    @PostMapping("/update")
    public void updateSolution(@RequestBody SolutionDto solutionDto) {
        solutionService.modifySolution(solutionDto);
    }

    @DeleteMapping("/remove/{id}")
    public void removeSolution(@PathVariable String id) {
        solutionService.deleteSolution(id);
    }
}
