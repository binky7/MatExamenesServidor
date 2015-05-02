/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class ExamenAsignadoFACADE extends BaseFACADE<ExamenAsignadoDTO,
        ExamenAsignadoPK> {
    
    public boolean asignarExamenes(List<ExamenAsignadoDTO> examenes) {
        return DAOServiceLocator.getExamenAsignadoDAO().insertar(examenes);
    }
    
    public List<ExamenAsignadoDTO> obtenerExamenesAsignados(UsuarioDTO alumno) {
        return DAOServiceLocator.getExamenAsignadoDAO().obtenerAsignados(alumno);
    }
    
    public List<ExamenAsignadoDTO> obtenerExamenesContestados(UsuarioDTO alumno,
            CursoDTO curso) {
        
        return DAOServiceLocator.getExamenAsignadoDAO()
                .obtenerContestados(alumno, curso);
    }
    
    public ExamenAsignadoDTO obtenerExamenContestado(ExamenAsignadoPK id) {
        return DAOServiceLocator.getExamenAsignadoDAO().obtenerContestado(id);
    }
}