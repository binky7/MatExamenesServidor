/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

/**
 *
 * @author Jesus Donaldo
 */
public class DAOServiceLocator {
    
    private static CursoDAO cursoDAO;
    private static ExamenDAO examenDAO;
    private static GrupoDAO grupoDAO;
    private static ReactivoDAO reactivoDAO;
    private static TemaDAO temaDAO;
    private static UsuarioDAO usuarioDAO;
    private static GenericDAO genericDAO;
    
    public static CursoDAO getCursoDAO() {
        if(cursoDAO == null) {
            cursoDAO = new CursoDAO();
        }
        
        return cursoDAO;
    }
    
    public static ExamenDAO getExamenDAO() {
        if(examenDAO == null) {
            examenDAO = new ExamenDAO();
        }
        
        return examenDAO;
    }
    
    public static GrupoDAO getGrupoDAO() {
        if(grupoDAO == null) {
            grupoDAO = new GrupoDAO();
        }
        
        return grupoDAO;
    }
    
    public static ReactivoDAO getReactivoDAO() {
        if(reactivoDAO == null) {
            reactivoDAO = new ReactivoDAO();
        }
        
        return reactivoDAO;
    }
    
    public static TemaDAO getTemaDAO() {
        if(temaDAO == null) {
            temaDAO = new TemaDAO();
        }
        
        return temaDAO;
    }
    
    public static UsuarioDAO getUsuarioDAO() {
        if(usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        
        return usuarioDAO;
    }
    
    public static GenericDAO getGenericDAO() {
        if(genericDAO == null) {
            genericDAO = new GenericDAO();
        }
        
        return genericDAO;
    }
}