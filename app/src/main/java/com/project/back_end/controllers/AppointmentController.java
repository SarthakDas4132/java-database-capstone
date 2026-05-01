import com.project.back_end.services.AppointmentService;
import com.project.back_end.services.MainService;
import com.project.back_end.models.Appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MainService service;

    @GetMapping("/{date}/{name}/{token}")
    public Object getAppointments(@PathVariable String date,
                                 @PathVariable String name,
                                 @PathVariable String token) {

        if (!service.validateToken(token, "doctor").getStatusCode().is2xxSuccessful()) {
            return "Unauthorized";
        }

        return appointmentService.getAppointment(name, LocalDate.parse(date), token);
    }

    @PostMapping("/{token}")
    public Object book(@RequestBody Appointment appointment, @PathVariable String token) {

        if (!service.validateToken(token, "patient").getStatusCode().is2xxSuccessful()) {
            return "Unauthorized";
        }

        int res = appointmentService.bookAppointment(appointment);

        return res == 1 ? "Booked" : "Error";
    }

    @DeleteMapping("/{id}/{token}")
    public Object cancel(@PathVariable long id, @PathVariable String token) {

        if (!service.validateToken(token, "patient").getStatusCode().is2xxSuccessful()) {
            return "Unauthorized";
        }

        return appointmentService.cancelAppointment(id, token);
    }
}