import java.util.Random;
import java.util.Scanner;
public class Principal{
    public static void main(String[] args){
        Scanner entrada= new Scanner(System.in);
        boolean usuario= true;
        while(usuario){
            System.out.println("--¿Deseas iniciar el juego?--");
            System.out.println("¿Si o no?");
            String opc= entrada.next();
            opc= opc.toLowerCase();
            try{
                if(opc.equals("si")){
                    Principal.miniJuego();
                    usuario= false;
                }
                else if(opc.equals("no")){
                    usuario= false;
                }
                else{
                    System.out.println("Tu respuesta no fue valida, responde solamente si o no");
                }
            }catch(Exception e){
                System.out.println("Tu respuesta no fue valida, responde solamente si o no");
            }
        }
    }

    public static int cantidadTanques(){
        Random claseRandom= new Random();
        boolean numero= true;
        int instanciaTanques= claseRandom.nextInt(4)+1;
        while(numero){
            if(instanciaTanques== 0){
                instanciaTanques= claseRandom.nextInt(4)+1;
            }
            else{
                numero= false;
            }
        }
        return instanciaTanques;
    }

    public static int cantidadMuertos(Tanques[][] tanques){
        int contadorMuertos= 0;
        if(tanques[0][0].getSalud()==0){
            contadorMuertos++;
        }
        try{
            if(tanques[0][1]!=null){
                if(tanques[0][1].getSalud()==0){
                    contadorMuertos++;
                }
            }
        }catch(Exception e){}
        try{
            if(tanques[1][0]!=null){
                if(tanques[1][0].getSalud()==0){
                    contadorMuertos++;
                }
            }
        }catch(Exception e){}
        try{
            if(tanques[1][1]!=null){
                if(tanques[1][1].getSalud()==0){
                    contadorMuertos++;
                }
            }
        }catch(Exception e){}
        return contadorMuertos;
    }
    
    public static void imprimirTablero(Tanques[][] tanques){
        System.out.println("-------------");
        System.out.print("|"+tanques[0][0].getNombre()+"-"+tanques[0][0].getSalud()+"|");
        if(tanques[0][1]==null){
            System.out.println("     |");
        }
        else{
            System.out.println(tanques[0][1].getNombre()+"-"+tanques[0][1].getSalud()+"|");
        }
        System.out.println("-------------");
        if(tanques[1][0]==null){
            System.out.print("|     |");
        }
        else{
            System.out.print("|"+tanques[1][0].getNombre()+"-"+tanques[1][0].getSalud()+"|");
        }
        if(tanques[1][1]==null){
            System.out.println("     |");
        }
        else{
            System.out.println(tanques[1][1].getNombre()+"-"+tanques[1][1].getSalud()+"|");
        }
        System.out.println("-------------");
    }

    public static void miniJuego(){
        Random claseRandom= new Random();
        Scanner entrada= new Scanner(System.in);
        Tanques[][] tanques= new Tanques[2][2];
        int cantidadTanques= Principal.cantidadTanques();
        int columnas= 0;
        int filas= 0;
        for(int i=0; i<cantidadTanques; i++){
            int opcTanque= claseRandom.nextInt(2);
            if (i==2) {
                columnas=0;
                filas=1;
            }
            if(opcTanque==0){
                TanquesNormales tn= new TanquesNormales();
                tanques[filas][columnas]= tn;
                columnas++;
            }
            else if(opcTanque==1){
                TanquesAliens ta= new TanquesAliens();
                tanques[filas][columnas]= ta;
                columnas++;
            }
        }
        
        Principal.imprimirTablero(tanques);

        boolean juego= true;
        int conteoDisparos= 0;
        while(juego){
            System.out.println("--Opciones--");
            System.out.println("- Opcion 1: Disparar una bala"); // quita 5 de sangre al tanque que el usuario escoja
            System.out.println("- Opcion 2: Activar bomba atomica"); // mata un tanque en una posicion aleatoria
            System.out.println("- Opcion 3: Activar Tanque mutante"); // duplicca la salud del tanque con menos salud
            System.out.println("- Opcion 4: La frase de la abuela"); // mostar frase de cuando la abuela jugaba tanques
            System.out.println("- Opcion 5: Conteo de disparos"); //cantidad de disparos realizados
            System.out.println("- Opcion 6: Leer salud"); // salud de cada Tanque
            int opcion= entrada.nextInt();

            switch(opcion){
                case 1:{
                        boolean opcion1= true;
                        while(opcion1){
                            System.out.println("--¿A cual tanque le deseas disparar?--");
                            System.out.println("¿Fila 1 o 2?");
                            int filita= entrada.nextInt();
                            System.out.println("¿Columna 1 o 2?");
                            int columnita= entrada.nextInt();

                            try{
                                if(tanques[filita-1][columnita-1].getSalud()!=0){
                                    tanques[filita-1][columnita-1].setSalud(tanques[filita-1][columnita-1].getSalud()-5);
                                    conteoDisparos++;
                                    opcion1= false;
                                }
                                else{
                                    System.out.println("Este tanque ya esta destruido");
                                }
                            }catch (Exception e){
                                System.out.println("EN ESTA POSICION NO HAY TANQUES");
                            }
                        }

                        Principal.imprimirTablero(tanques);
                        break;
                    }
                case 2:{
                        boolean opcion2= true;
                        while(opcion2){
                            int fTanque= claseRandom.nextInt(2);
                            int cTanque= claseRandom.nextInt(2);
                            try{
                                if(tanques[fTanque][cTanque].getSalud()!=0){
                                    tanques[fTanque][cTanque].setSalud(0);
                                    opcion2= false;
                                }
                            }catch (Exception e){}
                        }

                        Principal.imprimirTablero(tanques);
                        break;
                    }
                case 3:{
                        Tanques t= new TanquesNormales();
                        int menor= 0;
                        try{
                            if(tanques[0][0].getSalud()>0){
                                menor= tanques[0][0].getSalud();
                                t= tanques[0][0];
                            }
                        }catch(Exception e){}
                        try{
                            if(tanques[0][1].getSalud()>0){
                                if(menor>tanques[0][1].getSalud()){
                                    menor= tanques[0][1].getSalud();
                                    t= tanques[0][1];
                                }
                            }
                        }catch(Exception e){}
                        try{
                            if(tanques[1][0].getSalud()>0){
                                if(menor>tanques[1][0].getSalud()){
                                    menor= tanques[1][0].getSalud();
                                    t= tanques[1][0];
                                }
                            }
                        }catch(Exception e){}
                        try{
                            if(tanques[1][1].getSalud()>0){
                                if(menor>tanques[1][1].getSalud()){
                                    menor= tanques[1][1].getSalud();
                                    t= tanques[1][1];
                                }
                            }
                        }catch(Exception e){}
                        t.setSalud(t.getSalud()*2);

                        Principal.imprimirTablero(tanques);
                        break;
                    }
                case 4:{
                        System.out.println("--La frase de la Abuela--");
                        System.out.println("Flashback de la segunda guerra mundial :)");
                        break;
                    }
                case 5:{
                        System.out.println("La cantidad de disparos realizados hasta el momento es: "+conteoDisparos);
                        break;
                    }
                case 6:{
                        System.out.println("La salud actual del tanque en la posicion (1,1) es: "+tanques[0][0].getSalud());
                        try{
                            System.out.println("La salud actual del tanque en la posicion (1,2) es: "+tanques[0][1].getSalud());
                        }catch(Exception e){
                            System.out.println("La salud actual del tanque en la posicion (1,2) es: en esta posicion no hay tanques");
                        }
                        try{
                            System.out.println("La salud actual del tanque en la posicion (2,1) es: "+tanques[1][0].getSalud());
                        }catch(Exception e){
                            System.out.println("La salud actual del tanque en la posicion (2,1) es: en esta posicion no hay tanques");
                        }
                        try{
                            System.out.println("La salud actual del tanque en la posicion (2,2) es: "+tanques[1][1].getSalud());
                        }catch(Exception e){
                            System.out.println("La salud actual del tanque en la posicion (2,2) es: en esta posicion no hay tanques");
                        }
                        break;
                    }
            }

            if(Principal.cantidadMuertos(tanques)==cantidadTanques){
                juego= false;
            }
        }
    }
}
