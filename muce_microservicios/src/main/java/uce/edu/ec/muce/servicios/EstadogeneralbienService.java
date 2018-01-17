package uce.edu.ec.muce.servicios;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import uce.edu.ec.muce.intefaces.EstadogeneralbienRepositorio;
import uce.edu.ec.muce.modelos.Estadogeneralbien;



@Controller
@RequestMapping("/estadogeneralbien")
public class EstadogeneralbienService extends AbstracService<EstadogeneralbienRepositorio, Estadogeneralbien> {	
}