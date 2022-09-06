package com.example.Test.controllers;

import com.example.Test.models.Plane;
import com.example.Test.repositories.PlaneRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/plane")
public class PlaneController {


    @Autowired
    private PlaneRep PlaneRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Plane> plane = PlaneRep.findAll();
        model.addAttribute("plane",plane);
        return "plane/index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        return "plane/add-plane";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("name") String name,
            @RequestParam("year") String year,
            @RequestParam("price") Integer price,
            @RequestParam("kolvo") Integer kolvo,
            @RequestParam("engine") Integer engine,
            Model model)
    {
        Plane planeOne = new Plane(name,year,price,kolvo,engine);
        PlaneRep.save(planeOne);
        return "redirect:/plane/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("name") String name,
            Model model)
    {
        List<Plane> PlaneList = PlaneRep.findByName(name);
        model.addAttribute("plane",PlaneList);
        return "plane/index";

    }

}
