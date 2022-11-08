package pl.tsmiatacz.mockapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tsmiatacz.mockapi.data.ErrorData;
import pl.tsmiatacz.mockapi.data.HistoryData;
import pl.tsmiatacz.mockapi.data.ObjectData;
import pl.tsmiatacz.mockapi.service.HistoryService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class MainController {

    private final HistoryService historyService;

    public MainController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/isAlive")
    public ResponseEntity<String> isAlive() {
        return ResponseEntity.ok("I'm alive!");
    }

    @GetMapping("/error/{code}")
    public ResponseEntity<ErrorData> respond(@PathVariable int code) {
        return ResponseEntity.status(code).body(new ErrorData(code, "Responded with: " + code));
    }

    @PostMapping("/webhook/{key}")
    public void webhook(@PathVariable String key, @RequestBody String body) {
        historyService.add(new HistoryData("/v1/webhook/" + key, LocalDateTime.now(), body));
    }

    @GetMapping("/webhooks")
    public List<HistoryData> webhooks() {
        return historyService.list().stream()
                .sorted(Comparator.comparing(HistoryData::timestamp).reversed())
                .toList();
    }

    @PostMapping("/object")
    public ObjectData postForObject() {
        return new ObjectData(123L);
    }

    @PostMapping("/array")
    public List<ObjectData> postForArray() {
        return List.of(
                new ObjectData(21L),
                new ObjectData(37L));
    }

    @PutMapping("/object")
    public ObjectData purForObject() {
        return new ObjectData(123L);
    }

    @PutMapping("/array")
    public List<ObjectData> purForArray() {
        return List.of(
                new ObjectData(21L),
                new ObjectData(37L));
    }

    @GetMapping("/object")
    public ObjectData getObject() {
        return new ObjectData(123L);
    }

    @GetMapping("/array")
    public List<ObjectData> getArray() {
        return List.of(
                new ObjectData(21L),
                new ObjectData(37L));
    }
}
