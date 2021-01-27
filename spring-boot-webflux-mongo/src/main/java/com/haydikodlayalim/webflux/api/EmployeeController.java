package com.haydikodlayalim.webflux.api;

import com.haydikodlayalim.webflux.entity.Employee;
import com.haydikodlayalim.webflux.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;  //constructor olarak ınject ettik.

    
    //Reactive Stream de bize sunulan Mono ve Flux tipleri vardır.
    
    @GetMapping("/{id}")
    public Mono<Employee> getById(@PathVariable String id) { //Mono:Bir tane kaydı sunmak için kullandığımız bir tip.Mono içerisinde bize suulan reactive stream fonksiyonları var.
        return employeeRepository.findById(id);
    }

    @GetMapping
    public Flux<Employee> getAll() { // Flux:Birden fazla kaydı sunmak için kullandığımız bir tip.Flux içerisinde bize suulan reactive stream fonksiyonları var.
        return employeeRepository.findAll();
    }
    
   // Mono ve Flux Subscriber interfacenin implmenatasyonları.Mesela Subscribe da onSubscribe()  metodunu implemente ederek.biz subscribe() metoduna subscribe oluruz. 
}
