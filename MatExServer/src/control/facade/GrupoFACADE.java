/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.CursoDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author FernandoEnrique
 */
public class GrupoFACADE extends BaseFACADE<GrupoDTO, Integer>{
    
    public GrupoDTO obtenerGrupo(int idGrupo){
        return DAOServiceLocator.getGrupoDAO().obtener(idGrupo);
    }
    
    public List<CursoDTO> obtenerCursosDeGrupo(UsuarioDTO maestro) {
        return DAOServiceLocator.getGrupoDAO().obtenerCursos(maestro);
    }
    
    public List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso, UsuarioDTO maestro) {
        return DAOServiceLocator.getGrupoDAO().obtenerTodosPorCurso(curso, maestro);
    }
    
    public List<UsuarioDTO> obtenerAlumnosDeGrupo(GrupoDTO grupo) {
        return DAOServiceLocator.getGrupoDAO().obtenerSoloAlumnos(grupo);
    }
    
    public boolean verificarExistencia(GrupoDTO grupo) {
        return DAOServiceLocator.getGrupoDAO().existe(grupo);
    }
}