package es.cifpcm.vidicdaliborkamiali.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import es.cifpcm.vidicdaliborkamiali.dao.MunicipioRepository;
import es.cifpcm.vidicdaliborkamiali.dao.ProductsRepository;
import es.cifpcm.vidicdaliborkamiali.dao.ProvinciaRepository;
import es.cifpcm.vidicdaliborkamiali.model.Municipio;
import es.cifpcm.vidicdaliborkamiali.model.Products;
import es.cifpcm.vidicdaliborkamiali.model.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Controller
public class MunicipioController {
    @Autowired
    ProvinciaRepository provinciaRepository;
    @Autowired
    MunicipioRepository municipioRepository;

    @RequestMapping(value = "/products/municipiosFiltrados/{id}", produces = "application/json")

    @ResponseBody // para que me devuelva algo que NO es una vista

    public String municipio(@PathVariable  Integer id, Model model) throws JsonProcessingException {

        Provincia provChoose = provinciaRepository.findById(id).orElse(null);
        List<Municipio> filteredMunicipios = new ArrayList<>();
        filteredMunicipios = municipioRepository.findAll().stream()
                .filter(municipio-> (municipio.getIdProvincia()).equals(provChoose))
                .toList();

        model.addAttribute("municipios", filteredMunicipios);

        Gson gson = new Gson();

        return gson.toJson((filteredMunicipios));
    }


}
