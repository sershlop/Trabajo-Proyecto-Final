import javax.swing.*;

public class Menu{

   public static void main(String a[]){
   
   int menu = 0, borrar = 0, Imprimir = 0;
      
      do {
       try{
          menu = 0;
          menu = Integer.parseInt(JOptionPane.showInputDialog(" *** Menu *** \n" + "1 Capturar Informacion del Empleado \n" + "2 Modificar  \n" + "3 Borrar Informacion \n" + "4 Imprimir Informacion \n" + "5 Finalizar Programa \n" + "Seleccione una opcion [1 ~ 5]"));
          switch (menu){
           
           case 1:
                  RegistroEmpleados captura = new RegistroEmpleados("Empleados.txt");
                  captura.Captura();
                  break;
           case 2:
                 RegistroEmpleados modificar = new RegistroEmpleados("Empleados.txt");
                  modificar.modificarEmpleado();
                  break;
           case 3:
            borrar borrarEmp = new borrar("Empleados.txt");
               do{
               borrar = Integer.parseInt(JOptionPane.showInputDialog(" *** Menu *** \n" + "1 Borrado fisico \n" + "2 Borrado Logico \n" + "3 Volver \n" + "Selecione una opcion [1~3]"));
               switch (borrar) {
                  case 1:
                  
                  
                  borrarEmp.borrarFisico();
                     break;
                  case 2:
                       borrarEmp.borrarLogico();
                     break;
                  case 3:
                        borrar = 3;
                        break;
                  default:
                    JOptionPane.showMessageDialog(null, borrar +" No corresponde a una opcion, por favor intentelo de nuevo"); 
                    break;
                    }
                 }while (borrar !=3);  
                  break;
                  
           case 4:
              do{
              ReporteEmpleados reportes = new ReporteEmpleados("empleados.txt");
               Imprimir = Integer.parseInt(JOptionPane.showInputDialog(" *** Menu *** \n" + "1 Todo \n" + "2 Solo los activos \n" + "3 Volver \n" + "Selecione una opcion [1~3]"));
               switch (Imprimir) {
                  case 1:
                    reportes.imprimirTodo();
                      break;

                  case 2:
                     reportes.imprimirActivos();
                       break;
                    
                  case 3:
                        Imprimir = 3;
                        break;
                  default:
                    JOptionPane.showMessageDialog(null, Imprimir +" No corresponde a una opcion, por favor intentelo de nuevo"); 
                    break;
                    }
                 }while (Imprimir !=3);  
                  break;
           case 5:
            JOptionPane.showMessageDialog(null, "Vuelva Pronto"); 
                  break;
           default:
                  JOptionPane.showMessageDialog(null, menu +" No corresponde a una opcion, por favor intentelo de nuevo"); 
                  break;
                  }  
       }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Favor de Utilizar solo numeros al interactuar con el menu");
            menu = 0;
      }                                       
          }while (menu != 5);           
   
   }
}   