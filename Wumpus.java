package wumpus;

import java.util.Scanner;
import java.util.Random;

public class Wumpus {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //int var = sc.nextInt();
        int numfilas = 8;
        int numcolumans = 15;
        int jugador = 1; //El jugador inicia en la posicion 0 de la grilla

        Random rand = new Random();
        int cantWumpus = 10; //Cantidad de Wumpus
        char grilla[][] = new char[numfilas][numcolumans];
        for (int i = 0; i < numfilas; i++) {
            for (int j = 0; j < numcolumans; j++) {
                grilla[i][j] = '-';
            }
        }
        for (int i = 0; i < 10; i++) {
            int wumpus_i = rand.nextInt(numfilas);
            int wumpus_j = rand.nextInt(numcolumans);
            int pozo_i = rand.nextInt(numfilas);
            int pozo_j = rand.nextInt(numcolumans);
            int oro_i = rand.nextInt(numfilas);
            int oro_j = rand.nextInt(numcolumans);
            grilla[wumpus_i][wumpus_j] = 'W';
            grilla[pozo_i][pozo_j] = 'P';
            grilla[oro_i][oro_j] = 'O';
        }
        grilla[0][0] = '0';
        System.out.println("    Juego del Wumpus");
        System.out.println("       Version 1.0");
        System.out.println("  * * * * * * * * * * * ");
        for (int i = 0; i < numfilas; i++) {
            System.out.print("  *  ");
            for (int j = 0; j < numcolumans; j++) {
                System.out.print(grilla[i][j]);
            }
            System.out.print("  *  ");
            System.out.println();
        }
        System.out.println("  * * * * * * * * * * * ");

        int m = 0;
        char movimiento;
        int i = 0, j = 0;
        int cant_Oro = 0;
        int score = 0; //Punto sumandos por el jugador
        while (m == 0) {
            System.out.println("Usted se encuentra en el cuarto: " + jugador);
            //System.out.println("i j" + i + j);

            if (i == 0 && j == 0)/////////////////////////
            {
                grilla[i][j] = '-';
                System.out.println("Ud puede moverse o disparar hacia abajo(D) o a la derecha(R)");
                System.out.println("Moverse (M) Disparar (D)");
                movimiento = sc.next().charAt(0);
                if (movimiento == 'M') {
                    System.out.println("Hacia donde quiere moverce");
                    char direccion = sc.next().charAt(0);
                    if (direccion == 'D') {
                        i = i + 1;
                        jugador = jugador + numcolumans;
                    } else if (direccion == 'R') {
                        j = j + 1;
                        jugador++;
                    }

                    if (grilla[i][j] == 'O') {
                        System.out.println("Usted encontro un lingote de Oro(Digite R para recoger), (I para ignorar)");
                        char dato = sc.next().charAt(0);
                        if (dato == 'R') {
                            cant_Oro++;
                            score = score + 100;
                            m = 0;
                        }
                    }
                    m = valor(grilla, i, j, m, cant_Oro, score);
                    grilla[i][j] = '0';

                    if (grilla[i][j + 1] == 'P' || grilla[i + 1][j] == 'P') {
                        System.out.println("******Breeze******");
                    } else if (grilla[i][j + 1] == 'W' || grilla[i + 1][j] == 'W') {
                        System.out.println("******Stench******");
                    }
                } else if (movimiento == 'D') {
                    System.out.println("Disparar: (1)Abajo, (2)Derecha");
                    int disp = sc.nextInt(); //Ver a donde quiere disparar el jugador
                    if (disp == 1) {
                        if (grilla[i + 1][j] == 'W') {
                            score = score + 500;
                            grilla[i + 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 2) {
                        if (grilla[i][j + 1] == 'W') {
                            score = score + 500;
                            grilla[i][j + 1] = '-';
                            grilla[i][j] = '0';
                        }
                    }
                }
            } else if (i == 0 && j > 0 && j < numcolumans - 1) {
                grilla[i][j] = '-';
                System.out.println("Ud puede moverse o disparar hacia abajo(D), izquierda(L) o a la derecha(R)");
                System.out.println("Moverse (M) Disparar (D)");
                movimiento = sc.next().charAt(0);
                if (movimiento == 'M') {
                    System.out.println("Hacia donde quiere moverce");
                    char direccion = sc.next().charAt(0);
                    if (direccion == 'D') {
                        i = i + 1;
                        jugador = jugador + numcolumans;
                    } else if (direccion == 'R') {
                        j = j + 1;
                        jugador = jugador + 1;
                    } else if (direccion == 'L') {
                        j = j - 1;
                        jugador = jugador - 1;
                    }
                    if (grilla[i][j] == 'O') {
                        System.out.println("Usted encontro un lingote de Oro(Digite R para recoger), (I para ignorar)");
                        char dato = sc.next().charAt(0);
                        if (dato == 'R') {
                            cant_Oro++;
                            score = score + 100;
                            m = 0;
                        }
                    }
                    m = valor(grilla, i, j, m, cant_Oro, score);
                    grilla[i][j] = '0';
                    if (grilla[i][j + 1] == 'P' || grilla[i][j - 1] == 'P' || grilla[i + 1][j] == 'P') {
                        System.out.println("******Breeze******");
                    } else if (grilla[i][j + 1] == 'W' || grilla[i][j - 1] == 'W' || grilla[i + 1][j] == 'W') {
                        System.out.println("******Stench******");
                    }
                } else if (movimiento == 'D') {
                    System.out.println("Disparar: (1)Abajo, (2)Derecha, (3)Izquierda");
                    int disp = sc.nextInt(); //Ver a donde quiere disparar el jugador
                    if (disp == 1) {
                        if (grilla[i + 1][j] == 'W') {
                            score = score + 500;
                            grilla[i + 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 2) {
                        if (grilla[i][j + 1] == 'W') {
                            score = score + 500;
                            grilla[i][j + 1] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 3) {
                        if (grilla[i][j - 1] == 'W') {
                            score = score + 500;
                            grilla[i][j + 1] = '-';
                            grilla[i][j] = '0';
                        }
                    }
                }

            } else if (i == 0 && j == numcolumans - 1)//////////////////////////////
            {
                grilla[i][j] = '-';
                System.out.println("Ud puede moverse o disparar hacia abajo(D) o a la izquierda(L)");
                System.out.println("Moverse (M) Disparar (D)");
                movimiento = sc.next().charAt(0);
                if (movimiento == 'M') {
                    System.out.println("Hacia donde quiere moverce");
                    char direccion = sc.next().charAt(0);
                    if (direccion == 'D') {
                        i = i + 1;
                        jugador = jugador + numcolumans;
                    } else if (direccion == 'L') {
                        j = j - 1;
                        jugador = jugador - 1;
                    }
                    if (grilla[i][j] == 'O') {
                        System.out.println("Usted encontro un lingote de Oro(Digite R para recoger), (I para ignorar)");
                        char dato = sc.next().charAt(0);
                        if (dato == 'R') {
                            cant_Oro++;
                            score = score + 100;
                            m = 0;
                        }
                    }
                    m = valor(grilla, i, j, m, cant_Oro, score);
                    grilla[i][j] = '0';
                    if (grilla[i][j - 1] == 'P' || grilla[i + 1][j] == 'P') {
                        System.out.println("******Breeze******");
                    } else if (grilla[i][j - 1] == 'W' || grilla[i + 1][j] == 'W') {
                        System.out.println("******Stench******");
                    }
                } else if (movimiento == 'D') {
                    System.out.println("Disparar: (1)Abajo, (2)Izquierda");
                    int disp = sc.nextInt(); //Ver a donde quiere disparar el jugador
                    if (disp == 1) {
                        if (grilla[i + 1][j] == 'W') {
                            score = score + 500;
                            grilla[i + 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 2) {
                        if (grilla[i][j - 1] == 'W') {
                            score = score + 500;
                            grilla[i][j - 1] = '-';
                            grilla[i][j] = '0';
                        }
                    }
                }

            } else if (i != 0 && j == 0 && i != numfilas - 1) {
                grilla[i][j] = '-';
                System.out.println("Ud puede moverse o disparar hacia abajo(D), arriba(U) o a la derecha(R)");
                System.out.println("Moverse (M) Disparar (D)");
                movimiento = sc.next().charAt(0);
                if (movimiento == 'M') {
                    System.out.println("Hacia donde quiere moverce");
                    char direccion = sc.next().charAt(0);
                    if (direccion == 'D') {
                        i = i + 1;
                        jugador = jugador + numcolumans;
                    } else if (direccion == 'R') {
                        j = j + 1;
                        jugador = jugador + 1;
                    } else if (direccion == 'U') {
                        i = i - 1;
                        jugador = jugador - numcolumans;
                    }
                    if (grilla[i][j] == 'O') {
                        System.out.println("Usted encontro un lingote de Oro(Digite R para recoger), (I para ignorar)");
                        char dato = sc.next().charAt(0);
                        if (dato == 'R') {
                            cant_Oro++;
                            score = score + 100;
                            m = 0;
                        }
                    }
                    m = valor(grilla, i, j, m, cant_Oro, score);
                    grilla[i][j] = '0';
                    if (grilla[i][j + 1] == 'P' || grilla[i - 1][j] == 'P' || grilla[i + 1][j] == 'P') {
                        System.out.println("******Breeze******");
                    } else if (grilla[i][j + 1] == 'W' || grilla[i - 1][j] == 'W' || grilla[i + 1][j] == 'W') {
                        System.out.println("******Stench******");
                    }
                } else if (movimiento == 'D') {
                    System.out.println("Disparar: (1)Abajo, (2)Derecha, (3)Arriba");
                    int disp = sc.nextInt(); //Ver a donde quiere disparar el jugador
                    if (disp == 1) {
                        if (grilla[i + 1][j] == 'W') {
                            score = score + 500;
                            grilla[i + 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 2) {
                        if (grilla[i][j + 1] == 'W') {
                            score = score + 500;
                            grilla[i][j + 1] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 3) {
                        if (grilla[i - 1][j] == 'W') {
                            score = score + 500;
                            grilla[i - 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    }
                }

            } else if (j == 0 && i == numfilas - 1)//////////////////////////
            {
                grilla[i][j] = '-';
                System.out.println("Ud puede moverse o disparar hacia arriba(U) o a la derecha(R)");
                System.out.println("Moverse (M) Disparar (D)");
                movimiento = sc.next().charAt(0);
                if (movimiento == 'M') {
                    System.out.println("Hacia donde quiere moverce");
                    char direccion = sc.next().charAt(0);
                    if (direccion == 'U') {
                        i = i - 1;
                        jugador = jugador - numcolumans;
                    } else if (direccion == 'R') {
                        j = j + 1;
                        jugador = jugador + 1;
                    }
                    if (grilla[i][j] == 'O') {
                        System.out.println("Usted encontro un lingote de Oro(Digite R para recoger), (I para ignorar)");
                        char dato = sc.next().charAt(0);
                        if (dato == 'R') {
                            cant_Oro++;
                            score = score + 100;
                            m = 0;
                        }
                    }
                    m = valor(grilla, i, j, m, cant_Oro, score);
                    grilla[i][j] = '0';
                    if (grilla[i + 1][j] == 'P' || grilla[i][j + 1] == 'P') {
                        System.out.println("******Breeze******");
                    } else if (grilla[i + 1][j] == 'W' || grilla[i][j + 1] == 'W') {
                        System.out.println("******Stench******");
                    }
                } else if (movimiento == 'D') {
                    System.out.println("Disparar: (1)Arriba, (2)Derecha");
                    int disp = sc.nextInt(); //Ver a donde quiere disparar el jugador
                    if (disp == 1) {
                        if (grilla[i - 1][j] == 'W') {
                            score = score + 500;
                            grilla[i - 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 2) {
                        if (grilla[i][j + 1] == 'W') {
                            score = score + 500;
                            grilla[i][j + 1] = '-';
                            grilla[i][j] = '0';
                        }
                    }
                }

            } else if (i == numfilas - 1 && j == numcolumans - 1)/////////////////////////////
            {
                grilla[i][j] = '-';
                System.out.println("Ud puede moverse o disparar hacia arriba(U) o a la izquierda(L)");
                System.out.println("Moverse (M) Disparar (D)");
                movimiento = sc.next().charAt(0);
                if (movimiento == 'M') {
                    System.out.println("Hacia donde quiere moverce");
                    char direccion = sc.next().charAt(0);
                    if (direccion == 'U') {
                        i = i - 1;
                        jugador = jugador - numcolumans;
                    } else if (direccion == 'L') {
                        j = j - 1;
                        jugador = jugador - 1;
                    }
                    if (grilla[i][j] == 'O') {
                        System.out.println("Usted encontro un lingote de Oro(Digite R para recoger), (I para ignorar)");
                        char dato = sc.next().charAt(0);
                        if (dato == 'R') {
                            cant_Oro++;
                            score = score + 100;
                            m = 0;
                        }
                    }
                    m = valor(grilla, i, j, m, cant_Oro, score);
                    grilla[i][j] = '0';
                    if (grilla[i][j - 1] == 'P' || grilla[i - 1][j] == 'P') {
                        System.out.println("******Breeze******");
                    } else if (grilla[i][j - 1] == 'W' || grilla[i - 1][j] == 'W') {
                        System.out.println("******Stench******");
                    }
                } else if (movimiento == 'D') {
                    System.out.println("Disparar: (1)Arriba, (2)Izquierda");
                    int disp = sc.nextInt(); //Ver a donde quiere disparar el jugador
                    if (disp == 1) {
                        if (grilla[i - 1][j] == 'W') {
                            score = score + 500;
                            grilla[i - 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 2) {
                        if (grilla[i][j - 1] == 'W') {
                            score = score + 500;
                            grilla[i][j - 1] = '-';
                            grilla[i][j] = '0';
                        }
                    }
                }

            } else if (i == numfilas - 1 && j != 0 && j != numcolumans - 1) {
                grilla[i][j] = '-';
                System.out.println("Ud puede moverse o disparar hacia arriba(U), izquierda(L) o a la derecha(R)");
                System.out.println("Moverse (M) Disparar (D)");
                movimiento = sc.next().charAt(0);
                if (movimiento == 'M') {
                    System.out.println("Hacia donde quiere moverce");
                    char direccion = sc.next().charAt(0);
                    if (direccion == 'U') {
                        i = i - 1;
                        jugador = jugador - numcolumans;
                    } else if (direccion == 'R') {
                        j = j + 1;
                        jugador = jugador + 1;
                    } else if (direccion == 'L') {
                        j = j - 1;
                        jugador = jugador - 1;
                    }
                    if (grilla[i][j] == 'O') {
                        System.out.println("Usted encontro un lingote de Oro(Digite R para recoger), (I para ignorar)");
                        char dato = sc.next().charAt(0);
                        if (dato == 'R') {
                            cant_Oro++;
                            score = score + 100;
                            m = 0;
                        }
                    }
                    m = valor(grilla, i, j, m, cant_Oro, score);
                    grilla[i][j] = '0';
                    if (grilla[i][j + 1] == 'P' || grilla[i - 1][j] == 'P' || grilla[i][j - 1] == 'P') {
                        System.out.println("******Breeze******");
                    } else if (grilla[i][j + 1] == 'W' || grilla[i - 1][j] == 'W' || grilla[i][j - 1] == 'W') {
                        System.out.println("******Stench******");
                    }
                } else if (movimiento == 'D') {
                    System.out.println("Disparar: (1)Arriba, (2)Derecha, (3)Izquierda");
                    int disp = sc.nextInt(); //Ver a donde quiere disparar el jugador
                    if (disp == 1) {
                        if (grilla[i - 1][j] == 'W') {
                            score = score + 500;
                            grilla[i - 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 2) {
                        if (grilla[i][j + 1] == 'W') {
                            score = score + 500;
                            grilla[i][j + 1] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 3) {
                        if (grilla[i][j - 1] == 'W') {
                            score = score + 500;
                            grilla[i][j - 1] = '-';
                            grilla[i][j] = '0';
                        }
                    }
                }

            } else if (j == numcolumans - 1 && i != 0 && i != numfilas - 1) {
                grilla[i][j] = '-';
                System.out.println("Ud puede moverse o disparar hacia abajo(D), arriba(U) o a la izquierda(L)");
                System.out.println("Moverse (M) Disparar (D)");
                movimiento = sc.next().charAt(0);
                if (movimiento == 'M') {
                    System.out.println("Hacia donde quiere moverce");
                    char direccion = sc.next().charAt(0);
                    if (direccion == 'D') {
                        i = i + 1;
                        jugador = jugador + numcolumans;
                    } else if (direccion == 'U') {
                        i = i - 1;
                        jugador = jugador - numcolumans;
                    } else if (direccion == 'L') {
                        j = j - 1;
                        jugador = jugador - 1;
                    }
                    if (grilla[i][j] == 'O') {
                        System.out.println("Usted encontro un lingote de Oro(Digite R para recoger), (I para ignorar)");
                        char dato = sc.next().charAt(0);
                        if (dato == 'R') {
                            cant_Oro++;
                            score = score + 100;
                            m = 0;
                        }
                    }
                    m = valor(grilla, i, j, m, cant_Oro, score);
                    grilla[i][j] = '0';
                    if (grilla[i + 1][j] == 'P' || grilla[i - 1][j] == 'P' || grilla[i][j - 1] == 'P') {
                        System.out.println("******Breeze******");
                    } else if (grilla[i + 1][j] == 'W' || grilla[i - 1][j] == 'W' || grilla[i][j - 1] == 'W') {
                        System.out.println("******Stench******");
                    }
                } else if (movimiento == 'D') {
                    System.out.println("Disparar: (1)Abajo, (2)Arriba, (3)Izquierda");
                    int disp = sc.nextInt(); //Ver a donde quiere disparar el jugador
                    if (disp == 1) {
                        if (grilla[i + 1][j] == 'W') {
                            score = score + 500;
                            grilla[i + 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 2) {
                        if (grilla[i - 1][j] == 'W') {
                            score = score + 500;
                            grilla[i - 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 3) {
                        if (grilla[i][j - 1] == 'W') {
                            score = score + 500;
                            grilla[i][j - 1] = '-';
                            grilla[i][j] = '0';
                        }
                    }
                }

            } else if (i != 0 && j != 0 && i != numfilas - 1 && j != numcolumans - 1) {
                grilla[i][j] = '-';
                System.out.println("Ud puede moverse o disparar hacia arriba(U), abajo(D), izquierda(L) y derecha(R)");
                System.out.println("Moverse (M) Disparar (D)");
                movimiento = sc.next().charAt(0);
                if (movimiento == 'M') {
                    System.out.println("Hacia donde quiere moverce");
                    char direccion = sc.next().charAt(0);
                    if (direccion == 'D') {
                        i = i + 1;
                        jugador = jugador + numcolumans;
                    } else if (direccion == 'R') {
                        j = j + 1;
                        jugador = jugador + 1;
                    } else if (direccion == 'L') {
                        j = j - 1;
                        jugador = jugador - 1;
                    } else if (direccion == 'U') {
                        i = i - 1;
                        jugador = jugador - numcolumans;
                    }
                    if (grilla[i][j] == 'O') {
                        System.out.println("Usted encontro un lingote de Oro(Digite R para recoger), (I para ignorar)");
                        char dato = sc.next().charAt(0);
                        if (dato == 'R') {
                            cant_Oro++;
                            score = score + 100;
                            m = 0;
                        }
                    }
                    m = valor(grilla, i, j, m, cant_Oro, score);
                    grilla[i][j] = '0';
                    if (i > 0 && i != numfilas - 1) {
                        if (grilla[i + 1][j] == 'P' || grilla[i - 1][j] == 'P' || grilla[i][j - 1] == 'P' || grilla[i][j + 1] == 'P') {
                            System.out.println("******Breeze******");
                        } else if (grilla[i + 1][j] == 'W' || grilla[i - 1][j] == 'W' || grilla[i][j - 1] == 'W' || grilla[i][j + 1] == 'W') {
                            System.out.println("******Stench******");
                        }
                    } else if (i == 0) {
                        if (grilla[i + 1][j] == 'P' || grilla[i][j - 1] == 'P' || grilla[i][j + 1] == 'P') {
                            System.out.println("******Breeze******");
                        } else if (grilla[i + 1][j] == 'W' || grilla[i][j - 1] == 'W' || grilla[i][j + 1] == 'W') {
                            System.out.println("******Stench******");
                        }
                    } else if (i == numfilas) {
                        if (grilla[i - 1][j] == 'P' || grilla[i][j - 1] == 'P' || grilla[i][j + 1] == 'P') {
                            System.out.println("******Breeze******");
                        } else if (grilla[i - 1][j] == 'W' || grilla[i][j - 1] == 'W' || grilla[i][j + 1] == 'W') {
                            System.out.println("******Stench******");
                        }
                    }
                } else if (movimiento == 'D') {
                    System.out.println("Disparar: (1)Abajo, (2)Derecha, (3)Izquierda, (4)Arriba");
                    int disp = sc.nextInt(); //Ver a donde quiere disparar el jugador
                    if (disp == 1) {
                        if (grilla[i + 1][j] == 'W') {
                            score = score + 500;
                            grilla[i + 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 2) {
                        if (grilla[i][j + 1] == 'W') {
                            score = score + 500;
                            grilla[i][j + 1] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 3) {
                        if (grilla[i][j - 1] == 'W') {
                            score = score + 500;
                            grilla[i][j - 1] = '-';
                            grilla[i][j] = '0';
                        }
                    } else if (disp == 4) {
                        if (grilla[i - 1][j] == 'W') {
                            score = score + 500;
                            grilla[i - 1][j] = '-';
                            grilla[i][j] = '0';
                        }
                    }
                }

            } else {
                System.out.println("Error mi estimado");
            }
            System.out.println("  * * * * * * * * * * * ");
        for (int x = 0; x < numfilas; x++) {
            System.out.print("  *  ");
            for (int y = 0; y < numcolumans; y++) {
                System.out.print(grilla[x][y]);
            }
            System.out.print("  *  ");
            System.out.println();
        }
        System.out.println("  * * * * * * * * * * * ");
        }

    }

    public static int valor(char[][] grilla, int i, int j, int m, int cant_Oro, int score) {
        Scanner sc = new Scanner(System.in);
        if (grilla[i][j] == 'W') {
            System.out.println("Usted ha perdido, se ha encontrado con el WUMPUS!!");
            System.out.println("Usted obtuvo un puntaje de: " + score);
            m = m + 1;
        } else if (grilla[i][j] == 'P') {
            System.out.println("Usted ha perdido, se ha caido al POZO!!!");
            System.out.println("Usted obtuvo un puntaje de: " + score);
            m = m + 1;
        }
        return m;
    }
}
