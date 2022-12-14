package com.example.Test.controllers;


import com.example.Test.models.Plane;
import com.example.Test.repositories.PlaneRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public String addView(Model model, Plane plane)
    {
        model.addAttribute("plane",new Plane());
        return "plane/add-plane";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("plane")
            @Valid Plane newPlane,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "plane/add-plane";


        PlaneRep.save(newPlane);
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
    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Plane> plane = PlaneRep.findById(id);
        ArrayList<Plane> planeArrayList = new ArrayList<>();
        plane.ifPresent(planeArrayList::add);
        model.addAttribute("plane",planeArrayList);
        return "plane/info-plane";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Plane plane = PlaneRep.findById(id).orElseThrow();
        PlaneRep.delete(plane);
        return "redirect:/plane/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!PlaneRep.existsById(id))
        {
            return "redirect:/plane/";
        }
        Optional<Plane> plane = PlaneRep.findById(id);
        ArrayList<Plane> planeArrayList = new ArrayList<>();
        plane.ifPresent(planeArrayList::add);
        model.addAttribute("plane",planeArrayList.get(0));
        return "plane/edit-plane";
    }
    @PostMapping("/edit/{id}")
    public String editGames(
            @PathVariable("id") Long id,
            @ModelAttribute("plane") @Valid Plane newPlane,
            BindingResult bindingResult,
            Model model)
    {
        if(!PlaneRep.existsById(id))
        {
            return "redirect:/plane";
        }
        if(bindingResult.hasErrors())
            return "plane/edit-plane";
        newPlane.setId(id);
        PlaneRep.save(newPlane);
        return "redirect:/plane/";
    }
}
