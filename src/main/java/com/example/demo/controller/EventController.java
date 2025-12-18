package com.example.demo.controller;


import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEvents(){
        return eventService.getEvents();
    }

    @PostMapping
    public void addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
