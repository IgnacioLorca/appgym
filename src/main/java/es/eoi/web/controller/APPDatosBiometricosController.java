package es.eoi.web.controller;


import es.eoi.dto.DatosBiometricosDto;
import es.eoi.dto.UsuarioDto;
import es.eoi.service.DatosBiometricosSrvc;
import es.eoi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class APPDatosBiometricosController extends AbstractController<DatosBiometricosDto>{
    @Autowired
    private DatosBiometricosSrvc datosBiometricosSrvc;

    private UsuarioService usuarioSrvc;

    public APPDatosBiometricosController(DatosBiometricosSrvc datosBiometricosSrvc, UsuarioService usuarioSrvc) {
        this.datosBiometricosSrvc = datosBiometricosSrvc;
        this.usuarioSrvc = usuarioSrvc;
    }


    @GetMapping("/listadatosbio")
    public String vistaDBio( Model interfazConPantalla){
        Set<DatosBiometricosDto> datosBioDto = this.datosBiometricosSrvc.buscarTodosSet();
        interfazConPantalla.addAttribute("listadatosbiometricos", datosBioDto);
        return "listadatosbio";
    }

    @GetMapping("/datosbiometricos/{id}")
    public String vistaDatosDBio(@PathVariable("id") Integer id, ModelMap interfazConPantalla){
        Optional<DatosBiometricosDto> datosBioDto = this.datosBiometricosSrvc.encuentraPorId(id);
        if (datosBioDto.isPresent()){
            List<UsuarioDto> listaUsuarios = this.usuarioSrvc.buscarTodos();
            DatosBiometricosDto attr = datosBioDto.get();
            interfazConPantalla.addAttribute("datos",attr);
            interfazConPantalla.addAttribute("listausuariospagina", listaUsuarios);
            return "datosbiometricos/edit";
        } else{
            //Mostrar página usuario no existe
            return "datosbiometricos/detallesnoencontrado";
        }
    }
    @PostMapping("/datosbiometricos/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        Optional<DatosBiometricosDto> datosBioDto = this.datosBiometricosSrvc.encuentraPorId(id);
        if (datosBioDto.isPresent()){
            this.datosBiometricosSrvc.guardar(datosBioDto.get());
            return String.format("redirect:/datosbiometricos/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "catalogo/detallesnoencontrado";
        }
    }
    @PostMapping("/datosbiometricos/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id){
        Optional<DatosBiometricosDto> datosBioDto = this.datosBiometricosSrvc.encuentraPorId(id);
        if (datosBioDto.isPresent()){
            this.datosBiometricosSrvc.eliminarPorId(id);
            return "redirect:/datosbiometricos";
        } else{
            return "datosbiometricos/detallesnoencontrado";
        }
    }


    @GetMapping("/datosbiometricos/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        final DatosBiometricosDto datosBioDto = new DatosBiometricosDto();
        List<DatosBiometricosDto> datosBioList = this.datosBiometricosSrvc.buscarTodos();
        List<UsuarioDto> usuariosList = this.usuarioSrvc.buscarTodos();
        interfazConPantalla.addAttribute("datos", datosBioDto);
        interfazConPantalla.addAttribute("listadatos", datosBioList);
        interfazConPantalla.addAttribute("listausuariospagina", usuariosList);
        return "datosbiometricos/registro";
    }
    @PostMapping("/datosbiometricos/registro")
    public String guardarDatos(DatosBiometricosDto datosBioDto) throws Exception {
        DatosBiometricosDto datosBioGuardados =  this.datosBiometricosSrvc.guardar(datosBioDto);
        Long id = datosBioGuardados.getIdDatosBio();
        return String.format("redirect:/datosbiometricos/%s", id);
    }
}
