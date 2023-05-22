import de.matthiassattel.Employee
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/employees")
class EmployeeController {

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: String):Mono<Employee> {
        return Mono.empty()
    }

}