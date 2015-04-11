/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

/**
 *
 * @author Jesus Donaldo
 */
public class FACADEServiceLocator {
    
    private static CursoFACADE cursoFACADE;
    private static TemaFACADE temaFACADE;
    private static GenericFACADE genericFACADE;
    
    public static CursoFACADE getCursoFACADE() {
        if(cursoFACADE == null) {
            cursoFACADE = new CursoFACADE();
        }
        
        return cursoFACADE;
    }
    
    public static TemaFACADE getTemaFACADE() {
        if(temaFACADE == null) {
            temaFACADE = new TemaFACADE();
        }
        
        return temaFACADE;
    }
    
    public static GenericFACADE getGenericFACADE() {
        if(genericFACADE == null) {
            genericFACADE = new GenericFACADE();
        }
        
        return genericFACADE;
    }
}