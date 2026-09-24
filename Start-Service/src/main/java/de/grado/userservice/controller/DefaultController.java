package de.grado.userservice.controller;

import de.grado.userservice.forms.SearchBarForm;
import de.grado.userservice.model.Event;
import de.grado.userservice.service.EventCacheService;
import de.grado.userservice.service.EventService;
import de.grado.userservice.service.OrganizerCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DefaultController
{
    private final EventCacheService eventCacheService;
    private final EventService eventService;
    private final OrganizerCacheService organizerCacheService;

    @GetMapping("/")
    public String index(Model model)
    {
        if (!model.containsAttribute("searchForm")) {
            model.addAttribute("searchForm", new SearchBarForm());
        }
        if (!model.containsAttribute("eventList")) {
            model.addAttribute("eventList", eventCacheService.getEvents());
        }
        if (!model.containsAttribute("searchSubmitted")) {
            model.addAttribute("searchSubmitted", false);
        }
        return "index";
    }

    @PostMapping("/events/search")
    public String search(@ModelAttribute("searchForm") SearchBarForm searchForm, RedirectAttributes redirectAttributes)
    {
        List<Event> events = eventService.search(searchForm.getSearch());

        redirectAttributes.addFlashAttribute("searchForm", searchForm);
        redirectAttributes.addFlashAttribute("searchSubmitted", true);
        redirectAttributes.addFlashAttribute("searchResultCount", events.size());

        if (!events.isEmpty()) {
            redirectAttributes.addFlashAttribute("eventList", events);
        } else {
            redirectAttributes.addFlashAttribute("eventList", eventCacheService.getEvents());
        }
        return "redirect:/";
    }

    @GetMapping("/events")
    public String events(Model model)
    {
        model.addAttribute("eventList", eventCacheService.getEvents());
        return "events";
    }

    @GetMapping("/organizer")
    public String organizer(Model model)
    {
        model.addAttribute("organizerList", organizerCacheService.getOrganizers());
        return "organizer";
    }
}
