package de.grado.userservice.controller;

import de.grado.userservice.forms.SearchBarForm;
import de.grado.userservice.service.EventCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DefaultController
{
    private final EventCacheService eventCacheService;

    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("searchForm", new SearchBarForm());
        model.addAttribute("eventList", eventCacheService.getEvents());
        return "index";
    }

    @PostMapping("/events/search")
    public String search(@ModelAttribute("searchForm") SearchBarForm searchForm, Model model)
    {
        model.addAttribute("searchForm", searchForm);
        //TODO: Trigger Service who triggers Kafka
        return "index";
    }
}
