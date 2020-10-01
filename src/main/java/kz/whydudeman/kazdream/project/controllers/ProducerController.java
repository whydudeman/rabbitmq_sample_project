package kz.whydudeman.kazdream.project.controllers;

import kz.whydudeman.kazdream.project.forms.DefaultIdResponse;
import kz.whydudeman.kazdream.project.forms.request.ProducerForm;
import kz.whydudeman.kazdream.project.models.Producer;
import kz.whydudeman.kazdream.project.services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "producer")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @GetMapping(value = "all")
    public List<Producer> getAllProducers() {
        return producerService.getAll();
    }

    @GetMapping(value = "{id}")
    public Producer getProducerById(@PathVariable Long id) {
        return producerService.getProducerById(id);
    }

    @PostMapping
    public DefaultIdResponse createProducer(@RequestBody ProducerForm producerForm) {
        return producerService.createProducer(producerForm);
    }

    @PutMapping(value = "{id}")
    public DefaultIdResponse updateProducer(@PathVariable Long id, @RequestBody ProducerForm producerForm) {
        return producerService.updateProducer(id, producerForm);
    }

    @DeleteMapping(value = "{id}")
    public DefaultIdResponse deleteProducer(@PathVariable Long id){
        return producerService.deleteProducer(id);
    }
}
