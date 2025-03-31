package ch.etmles.payroll;

import jakarta.annotation.security.PermitAll;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

@Controller
@RequestMapping("/uptime")
@PermitAll
public class UptimeController {

    private Instant uptime;

    @EventListener(ApplicationReadyEvent.class)
    private void OnApplicationReady() {
        this.uptime = Instant.now();
    }

    @GetMapping()
    public ResponseEntity<Object> getUptime() {
        if (uptime == null) return ResponseEntity.internalServerError().body(null);

        Duration duration = Duration.between(uptime, Instant.now());
        HashMap<String, String> data = new HashMap<>();
        data.put("uptime", String.format("%d hours, %d minutes, %d seconds",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart()));

        return ResponseEntity.ok().body(data);
    }
}
