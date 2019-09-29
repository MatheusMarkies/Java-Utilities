/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageUtilities;

import static ImageUtilities.SearchImage.*;


/**
 *
 * @author Matheus Markies
 */
class ImageComparador extends Thread{

    boolean IgnoreExtremes = true;
    
    @Override
    public void run() {
              int Red = 0;
                      int Green = 0;
                      int Blue = 0;
        for(int Length = 0;Length< ArrayImageBase.size();Length++){
                          for(int u = 0;u < 3;u++){
                          if(u == 0){
                          prop = (int) (ArrayImageBase.get(Length).getRed() * tolerance)/100;//procentegem de tolerancia de cor (x% de 255)
                           int f = 255-prop;
                           for(int h=0;h<f;h++){
                             if(ArrayImageBase.get(Length).getBlue() == cA.getBlue()){
                              Red ++;
                              break;
                             }
                           }
                          }
                          if(u == 1){
                          prop = (int) (ArrayImageBase.get(Length).getGreen()* tolerance)/100;//procentegem de tolerancia de cor (x% de 255)
                          int f = 255-prop;
                           for(int h=0;h<f;h++){
                             if(ArrayImageBase.get(Length).getGreen() == cA.getGreen()){
                              Green ++;
                              break;
                             }
                           }
                          }
                          if(u == 2){
                          prop = (int) (ArrayImageBase.get(Length).getBlue() * tolerance)/100;//procentegem de tolerancia de cor (x% de 255)
                          int f = 255-prop;
                           for(int h=0;h<f;h++){
                             if(ArrayImageBase.get(Length).getBlue() == cA.getBlue()){
                              Blue ++;
                              break;
                             }
                           }
                          }
                      }
                          
                      if(IgnoreExtremes){
                        if(cA.getRed() == 255 && cA.getGreen()== 255 && cA.getBlue() == 255 || cA.getRed() == 0 && cA.getGreen() == 0 && cA.getBlue() == 0){
                        break;
                        }
                      }else{
                        if(Red > 0 && Green > 0 && Blue>0){
                         //System.out.println("ArrayInd "+ ArrayImageBase.get(Length).getRed() +" "+ArrayImageBase.get(Length).getGreen()+" "+ArrayImageBase.get(Length).getBlue());
                         //System.out.println("CA "+ cA.getRed() +" "+cA.getGreen()+" "+cA.getBlue());
                         isPixel = true;
                         break;
                        }
                      }
                      
                      
                      }
    }
    
    
    
}
