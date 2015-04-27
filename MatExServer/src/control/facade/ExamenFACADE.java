/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class ExamenFACADE extends BaseFACADE<ExamenDTO, Integer> {
    
    public ExamenDTO obtenerExamen(int idExamen) {
        return DAOServiceLocator.getExamenDAO().obtener(idExamen);
    }
    
    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso, boolean todos,
            UsuarioDTO maestro) {
        List<ExamenDTO> examenes;
        
        if(todos) {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerTodosPorCurso(curso);
        }
        else {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerPublicosPorCurso(curso, maestro);
        }
        
        return examenes;
    }
    
    public List<ExamenDTO> obtenerExamenesPorTitulo(String titulo, boolean todos,
            UsuarioDTO maestro) {
        List<ExamenDTO> examenes;
        
        if(todos) {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerTodosPorTitulo(titulo);
        }
        else {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerPublicosPorTitulo(titulo, maestro);
        }
        
        return examenes;
    }
    
    public List<ExamenDTO> obtenerExamenesPorCursoYTitulo(CursoDTO curso,
            String titulo, boolean todos, UsuarioDTO maestro) {
        List<ExamenDTO> examenes;
        
        if(todos) {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerTodosPorCursoYTitulo(curso, titulo);
        }
        else {
            examenes = DAOServiceLocator.getExamenDAO()
                    .obtenerPublicosPorCursoYTitulo(curso, titulo, maestro);
        }
        
        return examenes;
    }
}