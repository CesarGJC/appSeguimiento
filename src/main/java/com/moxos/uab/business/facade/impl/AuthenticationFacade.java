package com.moxos.uab.business.facade.impl;

import com.moxos.uab.business.facade.IAuthenticationFacade;
import com.moxos.uab.business.service.IEnlacesService;
import com.moxos.uab.business.service.IRolesService;
import com.moxos.uab.business.service.IUserService;
import com.moxos.uab.domain.entity.siiga.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthenticationFacade implements IAuthenticationFacade {

    private final IUserService userService;
    private final IRolesService userRolesService;
    private final IEnlacesService enlacesService;

    public AuthenticationFacade(IUserService userService, IRolesService userRolesService, IEnlacesService enlacesService) {
        this.userService = userService;
        this.userRolesService = userRolesService;
        this.enlacesService = enlacesService;
    }

    @Override
    public Clientes getBuscarConexion(String correo) {
        return userService.getBuscarConexion(correo);
    }

    @Override
    public List<Roles> getListarRolesCliente(Integer id_usuario) {
        return userService.getListarRolesCliente(id_usuario);
    }

    @Override
    public Accesos asignarAccesos(Clientes cliente) {
        return userService.getBuscarAcceso(cliente);
    }

    @Override
    public void setParametrosClientesUsuario(Clientes cliente) {
        userService.setParametrosClientesUsuario(cliente);
    }

    @Override
    public String getImagen(String path) {
        return userService.getImagen(path);
    }

    @Override
    public Clientes getBuscarRolCliente(Integer id_rol, Clientes cliente) {
        Roles rol = new Roles();
        rol.setId_rol(id_rol);
        rol.setId_usuario(cliente.getId_usuario());
        rol = userRolesService.getBuscarRolCliente(rol);
        cliente.setId_rol(rol.getId_rol());
        cliente.setRol(rol.getRol());
        cliente.setId_universidad(rol.getId_universidad());
        cliente.setId_facultad(rol.getId_facultad());
        cliente.setId_programa(rol.getId_programa());
        cliente.setId_departamento(rol.getId_departamento());
        cliente.setId_ubicacion_organica(rol.getId_ubicacion_organica());
        cliente.setId_almacen(rol.getId_almacen());
        cliente.setFiltro(rol.getFiltro());
        cliente.setPermiso(rol.getPermiso());
        cliente.setAlmacenes(userRolesService.getListarAlmacenesCliente(rol));
        if (!cliente.getAlmacenes().isEmpty()) {
            Roles aux = cliente.getAlmacenes().getFirst();
            cliente.setId_almacen(aux.getId_almacen());
            cliente.setPermiso(aux.getPermiso());
            cliente.setAlmacen(aux.getAlmacen());
        }
        if (cliente.getAlmacenes().isEmpty()) {
            cliente.setAlmacen("");
        }
        return cliente;
    }

    public String getMenuSistema(Clientes cliente) {
        List<TreeMenuEntity> enlaces = new ArrayList<TreeMenuEntity>();
        Enlaces enlace = new Enlaces();
        enlace.setId_rol(cliente.getId_rol());
        List<Enlaces> listaEnlaces = enlacesService.getListarEnlaces(enlace);
        // int fil = listaEnlaces.size();
        for (Enlaces listaEnlace : listaEnlaces) {
            TreeMenuEntity menues = new TreeMenuEntity();
            Enlaces aux = listaEnlace;
            menues.setIdObject(aux.getId_enlace());
            menues.setIdNivel(aux.getNivel());
            menues.setIdParent(aux.getId_enlace_padre());
            menues.setController(aux.getRuta());
            menues.setAction(aux.getImagen());
            menues.setNameModule(aux.getEnlace());
            enlaces.add(menues);
        }
        return getMenus(TreeMenuEntity.GenerarMenu(enlaces));
    }

    private String getMenus(List<MenuEntity> menu) {
        StringBuilder li = new StringBuilder();
        for (MenuEntity menuEntity : menu) {
            if (menuEntity.getNivel() == 0 && menuEntity.getMenus().isEmpty()) {
                li.append("<li> <a class='app-menu__item active' href='#'> <i class='app-menu__icon fas fa-chart-network'></i><span class='app-menu__label'>").append(menuEntity.getModule()).append("</span>");
            } else {
                if (menuEntity.getNivel() == 0 && !menuEntity.getMenus().isEmpty()) {
                    li.append("<li class='treeview'> <a class='app-menu__item'  href='#' data-toggle='treeview' > <i class='app-menu__icon fa fa-wpforms'></i><span class='app-menu__label'>").append(menuEntity.getModule()).append("</span><i class='treeview-indicator fa fa-angle-right'></i>");
                }
            }
            if (menuEntity.getNivel() != 0 && !menuEntity.getMenus().isEmpty()) {
                li.append("<li class='treeview'> <a class='app-menu__subitem' href='#' data-toggle='treeview-sub'><span class='app-menu__label'> " + recortarPalabra(menuEntity.getModule(), 22) + "</span><i class='treeview-indicator fa fa-angle-right'></i>");
            } else {
                if (menuEntity.getNivel() != 0 && menuEntity.getMenus().isEmpty()) {
                    li.append("<li> <a class='treeview-item' href='#' data-url='.").append(menuEntity.getController().replace(".uab", "").replace(".fautapo", "")).append("'> <i class='icon fa fa-circle-o'></i>").append(menuEntity.getModule());
                }
            }
            if (menuEntity.getNivel() == 0 && !menuEntity.getMenus().isEmpty()) {
                li.append("</a>");
                li.append(" <ul class='treeview-menu'>");
                li.append(getMenus(menuEntity.getMenus())).append("</ul></li>");
            } else if (menuEntity.getNivel() != 0 && !menuEntity.getMenus().isEmpty()) {
                li.append("</a>");
                li.append(" <ul class='treeview-menu treeview-menu-sub treeview-menu-open'>");
                li.append(getMenus(menuEntity.getMenus())).append("</ul></li>");
            } else {
                li.append("</a></li>");
            }
        }
        return li.toString().replaceAll(".NINGUNO", "");
    }

    private String recortarPalabra(String palabra, int longitudMaxima) {
        if (palabra.length() > longitudMaxima) {
            return palabra.substring(0, longitudMaxima) + "...";
        } else {
            return palabra;
        }
    }
}
