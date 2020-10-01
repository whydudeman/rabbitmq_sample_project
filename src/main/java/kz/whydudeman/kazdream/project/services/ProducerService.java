package kz.whydudeman.kazdream.project.services;

import kz.whydudeman.kazdream.project.forms.DefaultIdResponse;
import kz.whydudeman.kazdream.project.forms.request.ProducerForm;
import kz.whydudeman.kazdream.project.models.Producer;
import kz.whydudeman.kazdream.project.repositories.ProducerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProducerService {
    @Autowired
    private ProducerRepo producerRepo;

    @Autowired
    private RabbitMQService rabbitMQService;

    public List<Producer> getAll() {
        return producerRepo.findAll();
    }

    public Producer getProducerById(Long id) {
        return getProducerByIdOrThrow(id);

    }

    public DefaultIdResponse createProducer(ProducerForm form) {
        Producer producer = new Producer();
        producer.setName(form.name);
        producer.setDescription(form.description);
        producer = producerRepo.save(producer);
        rabbitMQService.send(producer);
        return new DefaultIdResponse(HttpStatus.CREATED, producer.getId());
    }

    public DefaultIdResponse updateProducer(Long id, ProducerForm form) {
        Producer producer = getProducerByIdOrThrow(id);
        producer.setName(form.name);
        producer.setDescription(form.description);
        producer = producerRepo.save(producer);
        return new DefaultIdResponse(HttpStatus.CREATED, producer.getId());
    }

    public DefaultIdResponse deleteProducer(Long id) {
        if (!producerRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PRODUCER_NOT_FOUND");
        }
        producerRepo.deleteById(id);
        return new DefaultIdResponse(HttpStatus.OK, id);
    }

    private Producer getProducerByIdOrThrow(Long id) {
        return producerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "PRODUCER_NOT_FOUND"));
    }

}
