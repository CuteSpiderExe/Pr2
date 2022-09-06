package com.example.Test.controllers;

import com.example.Test.models.Dota;
import com.example.Test.repositories.DotaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/dota")
public class DotaController {


    @Autowired
    private DotaRep DotaRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Dota> plane = DotaRep.findAll();
        model.addAttribute("dota",plane);
        return "dota/index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        return "dota/add-dota";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("name") String name,
            @RequestParam("tip") String tip,
            @RequestParam("hp") Integer hp,
            @RequestParam("mana") Integer mana,
            @RequestParam("damage") Integer damage,
            Model model)
    {
        Dota dotaOne = new Dota(name,tip,hp,mana,damage);
        DotaRep.save(dotaOne);
        return "redirect:/dota/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("name") String name,
            Model model)
    {
        List<Dota> DotaList = DotaRep.findByNameContains(name);
        model.addAttribute("dota",DotaList);
        return "dota/index";

    }

}
