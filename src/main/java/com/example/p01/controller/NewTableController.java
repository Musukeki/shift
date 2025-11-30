package com.example.p01.controller;


import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.p01.entity.NewTable;
import com.example.p01.service.ifs.NewTableService;


@RestController
@RequestMapping("/api/newtable")
@CrossOrigin(origins = "http://localhost:4200") 
public class NewTableController {

    private final NewTableService service;

    public NewTableController(NewTableService service) {
        this.service = service;
    }

    @GetMapping
    public List<NewTable> getAll() {
        return service.getAll();
    }

    @PostMapping
    public NewTable create(@RequestBody NewTable newRecord) {
        return service.create(newRecord);
    }
    
    @PostMapping("/ask")
    public NewTable ask(@RequestBody NewTable newRecord) {
        return service.askAI(newRecord);  
    }
}
