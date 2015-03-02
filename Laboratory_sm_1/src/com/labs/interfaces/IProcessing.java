/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labs.interfaces;

/**
 *
 * @author iura
 */
public interface IProcessing {
    
    void save();
    void rotate90DegreeLeft();
    void rotate90DegreeRight();
    void changeBrightness(int value);
    void changeContrast(int value);
    void flip();
    
}
