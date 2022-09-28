package pl.tsmiatacz.mockapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tsmiatacz.mockapi.data.ErrorData;
import pl.tsmiatacz.mockapi.data.HistoryData;
import pl.tsmiatacz.mockapi.service.HistoryService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class MainController {

    @Autowired
    private HistoryService historyService;

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
                .sorted(Comparator.comparing(HistoryData::localDateTime).reversed())
                .toList();
    }
}
