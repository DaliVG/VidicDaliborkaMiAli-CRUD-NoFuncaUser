package es.cifpcm.vidicdaliborkamiali.web;

import es.cifpcm.vidicdaliborkamiali.dao.MunicipioRepository;
import es.cifpcm.vidicdaliborkamiali.dao.ProvinciaRepository;
import es.cifpcm.vidicdaliborkamiali.model.Municipio;
import es.cifpcm.vidicdaliborkamiali.model.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProvinciaController {
    private final ProvinciaRepository provinciaRepository;

    public ProvinciaController(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    @GetMapping("/parameters/country/{id}")
    @ResponseBody
    public Provincia getProvincia(@PathVariable Integer id){
        return provinciaRepository.getReferenceById(id);
    }

}
