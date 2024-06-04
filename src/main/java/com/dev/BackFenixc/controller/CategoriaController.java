package com.dev.BackFenixc.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/categoria")
public class CategoriaController {

    ArrayList<String> categorias = new ArrayList<>(Arrays.asList("ARTESANIAS", "ALIMENTOS", "ROPA", "OTROS"));

    @GetMapping("/listar")
    public List<String> getAll() {
        return categorias;
    }

    @PostMapping("/add/{cate}")
    public String add(@PathVariable("cate") String cate){
        boolean eliminado = categorias.add(cate);

        // Verificar si la eliminación fue exitosa
        if (eliminado) {
            return "Categoria agregada correctamente";
        } else {
            return " Hubo un error intentalo nuevamente";
        }


    }

    @PostMapping("/delete/{cate}")
    public String delete(@PathVariable("cate") String cate){
        boolean eliminado = categorias.remove(cate);

        // Verificar si la eliminación fue exitosa
        if (eliminado) {
            return "Categoria  eliminada correctamente";
        } else {
            return " Hubo un error intentalo nuevamente";
        }


    }




}
