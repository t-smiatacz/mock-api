package pl.tsmiatacz.mockerror.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tsmiatacz.mockerror.data.ResponseData;

@RestController
@RequestMapping("/v1")
public class ErrorController {

    @GetMapping("/{code}")
    public ResponseEntity<ResponseData> respond(@PathVariable int code) {
        return ResponseEntity.status(code).body(new ResponseData(code, "Responded with: " + code));
    }

}
