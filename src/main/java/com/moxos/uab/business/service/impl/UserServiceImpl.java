package com.moxos.uab.business.service.impl;

import com.moxos.uab.business.service.IUserService;
import com.moxos.uab.common.util.Utils;
import com.moxos.uab.domain.entity.siiga.*;
import com.moxos.uab.persistence.siiga.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private final ClientesDao clientesDao;
    private final RolesDao rolesDao;
    private final UniversidadesDao universidadesDao;
    private final FacultadesDao facultadesDao;
    private final ProgramasDao programasDao;
    private final DepartamentosDao departamentosDao;
    private final PlanesDao planesDao;

    public UserServiceImpl(ClientesDao clientesDao, RolesDao rolesDao, UniversidadesDao universidadesDao, FacultadesDao facultadesDao, ProgramasDao programasDao, DepartamentosDao departamentosDao, PlanesDao planesDao) {
        this.clientesDao = clientesDao;
        this.rolesDao = rolesDao;
        this.universidadesDao = universidadesDao;
        this.facultadesDao = facultadesDao;
        this.programasDao = programasDao;
        this.departamentosDao = departamentosDao;
        this.planesDao = planesDao;
    }

    @Override
    public Clientes getBuscarConexion(String correo) {
        return clientesDao.getBuscarConexion(correo);
    }

    @Override
    public List<Roles> getListarRolesCliente(Integer id_usuario) {
        return rolesDao.getListarRolesCliente(id_usuario);
    }

    @Override
    public Accesos getBuscarAcceso(Clientes cliente) {
        Accesos accesos = new Accesos();
        // Tal vez tiene permiso para toda la universidad
        if (cliente.getId_universidad() > 0) {
            Universidades universidad = new Universidades();
            universidad.setId_universidad(cliente.getId_universidad());
            universidad = universidadesDao.getUnvBuscarUniversidad(universidad);
            accesos.setId_acceso(universidad.getId_universidad());
            accesos.setAcceso(universidad.getUniversidad());
            accesos.setListaFacultades(facultadesDao.getUnvListarFacultades(universidad));
            accesos.setListaProgramas(programasDao.getUnvListarProgramas(universidad));
            accesos.setListaDepartamentos(departamentosDao.getUnvListarDepartamentos(universidad));
            accesos.setListaPlanes(planesDao.getUnvListarPlanes(universidad));
        }
        if (cliente.getId_facultad() > 0) {
            Facultades facultad = new Facultades();
            facultad.setId_facultad(cliente.getId_facultad());
            facultad = facultadesDao.getFclBuscarFacultad(facultad);
            accesos.setId_acceso(facultad.getId_facultad());
            accesos.setAcceso(facultad.getFacultad());
            accesos.setListaProgramas(programasDao.getFclListarProgramas(facultad));
            accesos.setListaDepartamentos(departamentosDao.getFclListarDepartamentos(facultad));
            accesos.setListaPlanes(planesDao.getFclListarPlanes(facultad));
        }
        if (cliente.getId_programa() > 0) {
            Programas programa = new Programas();
            programa.setId_programa(cliente.getId_programa());
            programa = programasDao.getPrgBuscarPrograma(programa);
            accesos.setId_acceso(programa.getId_programa());
            accesos.setAcceso(programa.getPrograma());
            accesos.setListaPlanes(planesDao.getPrgListarPlanes(programa));
        }
        if (cliente.getId_departamento() > 0) {
            Departamentos departamento = new Departamentos();
            departamento.setId_departamento(cliente.getId_departamento());
            departamento = departamentosDao.getDptBuscarDepartamento(departamento);
            accesos.setId_acceso(departamento.getId_departamento());
            accesos.setAcceso(departamento.getDepartamento());
        }
        return accesos;
    }

    @Override
    public void setParametrosClientesUsuario(Clientes cliente) {
        Roles rol = new Roles();
        rol.setId_usuario(cliente.getId_usuario());
        rol.setId_rol(cliente.getId_rol());
        rol = this.rolesDao.getBuscarRolCliente(rol);
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
        cliente.setAlmacen("");
        //Sacamos el listado de almacenes
        cliente.setAlmacenes(this.rolesDao.getListarAlmacenesCliente(rol));
        if (cliente.getAlmacenes().size() == 1) {
            Roles aux = cliente.getAlmacenes().getFirst();
            cliente.setId_almacen(aux.getId_almacen());
            cliente.setPermiso(aux.getPermiso());
            cliente.setAlmacen(aux.getAlmacen());
        }
    }

    @Override
    public String getImagen(String path) {
        String imagen;

        String scontentype = "image/png";
        String rootPath = path + File.separator + "docadjuntos" + File.separator + "nulo.png";
        File fnew = new File(rootPath);
        if (!fnew.exists()) {
            fnew = new File(path + File.separator + "docadjuntos" + File.separator + "nulo.png");
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage originalImage = ImageIO.read(fnew);
            ImageIO.write(Utils.resize(originalImage, 70, 70), "png", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] imageInByte = baos.toByteArray();
        imagen = "data:" + scontentype + ";base64," + Base64.getEncoder().encodeToString(imageInByte);
        return imagen;
    }
}
