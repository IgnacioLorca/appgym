package es.eoi.controller;



import es.eoi.dto.ClienteDto;
import es.eoi.dto.UsuarioDto;
import es.eoi.service.ClienteSrvc;
import es.eoi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class APPClienteController extends AbstractController<ClienteDto>{
    @Autowired
    private ClienteSrvc clienteSrvc;

    private UsuarioService usuarioSrvc;

    public APPClienteController(ClienteSrvc clienteSrvc, UsuarioService usuarioSrvc) {
        this.clienteSrvc = clienteSrvc;
        this.usuarioSrvc = usuarioSrvc;
    }


    @GetMapping("/listaclientes")
    public String vistaClientes( Model interfazConPantalla){
        Set<ClienteDto> clienteDto = this.clienteSrvc.buscarTodosSet();
        interfazConPantalla.addAttribute("listaclientes", clienteDto);
        return "cliente/listaclientes";
    }


    @GetMapping("/cliente/{id}")
    public String vistaDatosCliente(@PathVariable("id") Integer id, ModelMap interfazConPantalla){
        Optional<ClienteDto> clienteDto = this.clienteSrvc.encuentraPorId(id);
        if (clienteDto.isPresent()){
            List<UsuarioDto> listaUsuarios = this.usuarioSrvc.buscarTodos();
            ClienteDto attr = clienteDto.get();
            interfazConPantalla.addAttribute("datos",attr);
            interfazConPantalla.addAttribute("listausuariospagina", listaUsuarios);
            return "cliente/edit";
        } else{
            //Mostrar página usuario no existe
            return "cliente/detallesnoencontrado";
        }
    }


    @PostMapping("/cliente/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        Optional<ClienteDto> clienteDto = this.clienteSrvc.encuentraPorId(id);
        if (clienteDto.isPresent()){
            this.clienteSrvc.guardar(clienteDto.get());
            return String.format("redirect:/cliente/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "cliente/detallesnoencontrado";
        }
    }


    @PostMapping("/cliente/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id){
        Optional<ClienteDto> cllienteDto = this.clienteSrvc.encuentraPorId(id);
        if (cllienteDto.isPresent()){
            this.clienteSrvc.eliminarPorId(id);
            return "redirect:/cliente";
        } else{
            return "cliente/detallesnoencontrado";
        }
    }


    @GetMapping("/cliente/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        final ClienteDto clienteDto = new ClienteDto();
        List<ClienteDto> clienteList = this.clienteSrvc.buscarTodos();
        List<UsuarioDto> usuariosList = this.usuarioSrvc.buscarTodos();
        interfazConPantalla.addAttribute("datos", clienteDto);
        interfazConPantalla.addAttribute("listadatos", clienteList);
        interfazConPantalla.addAttribute("listausuariospagina", usuariosList);
        return "cliente/registro";
    }



    @PostMapping("/cliente/registro")
    public String guardarDatos(ClienteDto clienteDto) throws Exception {
        ClienteDto clienteGuardado =  this.clienteSrvc.guardar(clienteDto);
        Long id = clienteGuardado.getIdCliente();
        return String.format("redirect:/cliente/%s", id);
    }
}
