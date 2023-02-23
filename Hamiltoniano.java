/* trabajo 1 grafos 
   Es un grafo hamiltoniano y mostrar un camino o ciclo 
   Josue Jonathan Perez Valenzuela
   */
class Hamiltoniano{
    int camino[];
     /* Aqui resolveremos el problema del camino hamiltoniano utilizando backtraking
       y tambien usaremos el metodo ya implementado "hamcaminoUtil",
       devolveremos false si no es un grafo de hamiltoniano,
       si lo es devolveremos true e imprimira el camino o camino
       OJO: puede haber mas de unsa sol */
    public boolean hamcamino(int grafo[][]){
        int V = grafo.length;
        camino = new int[V];
        //creamos un camino lleno de -1
        for (int i = 0; i < V; i++)
            camino[i] = -1;
 
        /* pondremos el vertice 0 como el primer vertice del camino , ya que si existe
           un camino hamiltoniano este puede empezar desde cualquier vertice ya que 
           es un grafo no dirigido*/
        camino[0] = 0;
        if (hamcaminoUtil(grafo, camino, 1, V) == false)
        {
            System.out.println("\nNo es un grafo de hamilton");
            return false;
        }

        return true;
    }
    
    /* Metodo que devolvera true si existe un camino o ciclo "y lo imprime" o false en caso de 
       que no sea un grafo de hamilton*/
    private boolean hamcaminoUtil(int grafo[][], int camino[], int pos, int V)
    {
        /* base: si todos los vertices ya estan en el camino */
        if (pos == V)
        {
            // hay una arista desde el ultimo vertice incluido hasta el primero??, esto convertira el camino en un ciclo
            if (grafo[camino[pos - 1]][camino[0]] == 1){
                System.out.println("\nExiste una solucion: Esto" +
                                   " es un ciclo hamiltoniano");
                imprimirSol(camino, V);
                // Volveremos a imprimir el primer vertice para que sea un ciclo
                System.out.print(" " + camino[0] + " ");
                return true;
            }else{
                // si no hay una coneccion entre el ultimo y el primero solo sera un camino
                System.out.println("\nExiste una solucion: Esto" +
                                   " es un camino hamiltoniano");
                imprimirSol(camino, V);
                return true;
            }
        }
 
        /* probaremos diferentes vertices para el camino hamiltoniano, no empezaremos 
         * de 0 ya que ese fue nuestro punto de partida
           */
        for (int v = 1; v < V; v++)
        {
            /* comprobamos si este vertice puede ser agragado en el camino hamiltoniano */
            if (esValido(v, grafo, camino, pos)){
                camino[pos] = v;
 
                /* construiremos el resto del camino */
                if (hamcaminoUtil(grafo, camino, pos + 1, V) == true)
                    return true;
 
                /* si en algun momento al agregar el vertice v no llegamos a una sol
                   debemos eliminarlo 
                   */
                camino[pos] = -1;
            }
        }
 
        /* Si no podemos agregar ningun vertice al camino hamiltoniano que tenemos
           hasta ahora devolvemos false ya que no recorrimos todos los vertices */
        return false;
    }
    
    /* funcion para verificar si el vertice "V" puede ser agregado en la "pos" en el 
       camino hamiltoniano que tenemos hasta el momento*/
    private boolean esValido(int v, int grafo[][], int camino[], int pos){
        /* verifico si mi nuevo vertice es un adyacente del ultimo añadido */
        if (grafo[camino[pos - 1]][v] == 0)
            return false;
 
        /* verifico si el vertice ya a sido incluido en el camino, ya que no podemos 
           repetir vertices */
        for (int i = 0; i < pos; i++)
            if (camino[i] == v)
                return false;
 
        return true;
    }
 
    /* Metodo para imprimir las sol */
    private void imprimirSol(int camino[], int V)
    {
        for (int i = 0; i < V; i++)
            System.out.print(" " + camino[i] + " ");
    }
 
    // pruebas
    public static void main()
    {
        Hamiltoniano hamilton =
                                new Hamiltoniano();
        /* creamos un grafo, que si tiene un ciclo {0,1,2,4,3,0}
           (0)--(1)--(2)
            |   / \   |
            |  /   \  |
            | /     \ |
           (3)-------(4)    */
        int grafo1[][] = {{0, 1, 0, 1, 0},
                          {1, 0, 1, 1, 1},
                          {0, 1, 0, 0, 1},
                          {1, 1, 0, 0, 1},
                          {0, 1, 1, 1, 0},};
 
        // imprimimos la sol
        hamilton.hamcamino(grafo1);
 
        /* creamos un grafo, que no tiene un ciclo pero si un camino {0,3,1,2,4}
           (0)--(1)--(2)
            |   / \   |
            |  /   \  |
            | /     \ |
           (3)       (4)    */
        int grafo2[][] = {{0, 1, 0, 1, 0},
                          {1, 0, 1, 1, 1},
                          {0, 1, 0, 0, 1},
                          {1, 1, 0, 0, 0},
                          {0, 1, 1, 0, 0},};
        // imprimimos la sol
        hamilton.hamcamino(grafo2);
        /* creamos un grafo, que si tiene un camino {0,3,2,1,4} pero no un ciclo
           (0)-------(1)
            |       / |
            |      /  |
            |     /   |
            |   (2)   |
            |   /     |
            |  /      |
           (3)       (4)    */
        int grafo3[][] = {{0,1,0,1,0},
                          {1,0,1,0,1},
                          {0,1,0,1,0},
                          {1,0,1,0,0},
                          {0,1,0,0,0},};
        hamilton.hamcamino(grafo3);
        /* creamos un grafo, que si tiene un ciclo {0,1,4,2,3,0} 
           (0)-------(1)
            |       / |
            |      /  |
            |     /   |
            |   (2)   |
            |   /  \  |
            |  /    \ |
           (3)       (4)    */
        int grafo4[][] = {{0,1,0,1,0},
                          {1,0,1,0,1},
                          {0,1,0,1,1},
                          {1,0,1,0,0},
                          {0,1,1,0,0},};
        hamilton.hamcamino(grafo4);
        /* creamos un grafo, que no es hamiltoniano 
           (0)-------(1)
             \        |
                \     |
                    \ |
           (3)-------(2)
                      |
                      |
                     (4)
        */
        int grafo5[][] = {{0,1,1,0,0},
                          {1,0,1,0,0},
                          {1,1,0,1,1},
                          {0,0,1,0,0},
                          {0,0,1,0,0},};
        hamilton.hamcamino(grafo5);
        
        int grafo6[][] = {{0,1,1,0,0,0,0,0,0,0,1},
                          {1,0,1,0,1,0,0,1,0,0,0},
                          {1,1,0,1,0,0,1,0,0,0,0},
                          {0,0,1,0,0,1,1,0,0,0,0},
                          {0,1,0,0,0,0,1,0,1,1,0},
                          {0,0,0,1,0,0,0,1,1,0,1},
                          {0,0,1,1,1,0,0,1,0,0,0},
                          {0,1,0,0,0,1,1,0,0,1,1},
                          {0,0,0,0,1,1,0,0,0,1,0},
                          {0,0,0,0,1,0,0,1,1,0,1},
                          {1,0,0,0,0,1,0,1,0,1,0},};
                          
        hamilton.hamcamino(grafo6);
    }
}