package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import com.example.myapplication.Rejillas.Rejilla;
import com.example.myapplication.IA.IA;
import java.util.LinkedList;

public class Arbitro {
    Context contexto;
    private LinkedList<int[]> positions;
    private Rejilla rejilla;

    public void setPositions(LinkedList<int[]> positions2, Context context) {
        this.positions = positions2;
        this.contexto = context;
    }

    public void setRejilla(Rejilla rejilla2) {
        this.rejilla = rejilla2;
    }

    public boolean obtienePuntoPorIzquierda(int[] array, int[] filasColumas, LinkedList<int[]> positionsRejilla, int posicion) {
        int lineasPorFila = this.rejilla.getLineasPorFila();
        if (array[2] != 1) {
            if (array[3] == 0) {
                if (filasColumas[1] == 2) {
                    return esta(positionsRejilla, posicion - 1);
                }
                if (!esta(positionsRejilla, posicion - 1) || !esta(positionsRejilla, posicion - 2)) {
                    return false;
                }
                return true;
            } else if (array[4] == filasColumas[0] - 1) {
                if (filasColumas[1] == 2) {
                    return esta(positionsRejilla, (posicion - lineasPorFila) - 1);
                }
                if (!esta(positionsRejilla, (posicion - lineasPorFila) - 1) || !esta(positionsRejilla, posicion - 2)) {
                    return false;
                }
                return true;
            } else if (filasColumas[1] == 2) {
                return esta(positionsRejilla, posicion - 1) & esta(positionsRejilla, (posicion - lineasPorFila) - 1);
            } else {
                return esta(positionsRejilla, posicion - 1) & esta(positionsRejilla, (posicion - lineasPorFila) - 1) & esta(positionsRejilla, posicion - 2);
            }
        } else if (array[3] == 0) {
            return esta(positionsRejilla, posicion - 1);
        } else {
            if (array[4] == filasColumas[0] - 1) {
                return esta(positionsRejilla, (posicion - lineasPorFila) - 1);
            }
            if (!esta(positionsRejilla, posicion - 1) || !esta(positionsRejilla, (posicion - lineasPorFila) - 1)) {
                return false;
            }
            return true;
        }
    }

    public boolean obtienePuntoPorArriba(int[] array, int[] filasColumas, LinkedList<int[]> positionsRejilla, int posicion) {
        int lineasPorFila = this.rejilla.getLineasPorFila();
        if (array[2] == 1) {
            if (array[3] == 0) {
                return esta(positionsRejilla, posicion + 1);
            }
            if (array[4] == filasColumas[1]) {
                return esta(positionsRejilla, posicion - 1);
            }
            return esta(positionsRejilla, posicion - 1) & esta(positionsRejilla, posicion + 1);
        } else if (array[3] == 0) {
            if (!esta(positionsRejilla, posicion + 1) || !esta(positionsRejilla, posicion - lineasPorFila)) {
                return false;
            }
            return true;
        } else if (array[4] == filasColumas[1]) {
            if (!esta(positionsRejilla, posicion - 1) || !esta(positionsRejilla, posicion - lineasPorFila)) {
                return false;
            }
            return true;
        } else if (!esta(positionsRejilla, posicion - 1) || !esta(positionsRejilla, posicion + 1) || !esta(positionsRejilla, posicion - lineasPorFila)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean obtienePuntoPorDerercha(int[] array, int[] filasColumas, LinkedList<int[]> positionsRejilla, int posicion) {
        int lineasPorFila = this.rejilla.getLineasPorFila();
        if (array[2] == filasColumas[1] - 1) {
            if (array[3] == 0) {
                return esta(positionsRejilla, posicion + 1);
            }
            if (array[4] == filasColumas[0] - 1) {
                return esta(positionsRejilla, (posicion - lineasPorFila) + 1);
            }
            if (!esta(positionsRejilla, posicion + 1) || !esta(positionsRejilla, (posicion - lineasPorFila) + 1)) {
                return false;
            }
            return true;
        } else if (array[3] == 0) {
            return esta(positionsRejilla, posicion + 1) & esta(positionsRejilla, posicion + 2);
        } else {
            if (array[4] != filasColumas[0] - 1) {
                return esta(positionsRejilla, posicion + 1) & esta(positionsRejilla, posicion + 2) & esta(positionsRejilla, (posicion - lineasPorFila) + 1);
            }
            if (!esta(positionsRejilla, posicion + 2) || !esta(positionsRejilla, (posicion - lineasPorFila) + 1)) {
                return false;
            }
            return true;
        }
    }

    public boolean obtienePuntoPorAbajo(int[] array, int[] filasColumas, LinkedList<int[]> positionsRejilla, int posicion) {
        int lineasPorFila = this.rejilla.getLineasPorFila();
        if (array[2] == filasColumas[0] - 2) {
            if (array[3] == 0) {
                return esta(positionsRejilla, posicion + lineasPorFila + 1);
            }
            if (array[4] == filasColumas[1]) {
                return esta(positionsRejilla, (posicion + lineasPorFila) - 1);
            }
            if (!esta(positionsRejilla, posicion + lineasPorFila + 1) || !esta(positionsRejilla, (posicion + lineasPorFila) - 1)) {
                return false;
            }
            return true;
        } else if (array[3] == 0 || array[4] == 0) {
            if (!esta(positionsRejilla, posicion + lineasPorFila) || !esta(positionsRejilla, posicion + lineasPorFila + 1)) {
                return false;
            }
            return true;
        } else if (array[3] == filasColumas[1] || array[4] == filasColumas[1]) {
            if (!esta(positionsRejilla, posicion + lineasPorFila) || !esta(positionsRejilla, (posicion + lineasPorFila) - 1)) {
                return false;
            }
            return true;
        } else if (!esta(positionsRejilla, posicion + lineasPorFila + 1) || !esta(positionsRejilla, posicion + lineasPorFila) || !esta(positionsRejilla, (posicion + lineasPorFila) - 1)) {
            return false;
        } else {
            return true;
        }
    }

    public void posicionesParaCompletarZD(int[] array, int[] filasColumas, IA ia, LinkedList<int[]> positionsRejilla, int posicion) {
        int i;
        int lineasPorFila = this.rejilla.getLineasPorFila();
        if (array[2] <= 1) {
            i = 1;
            if (array[2] == 1) {
                if (array[3] == 0) {
                    if (!esta(positionsRejilla, posicion - 1)) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    }
                } else if (array[4] != filasColumas[0] - 1) {
                    boolean esta1 = esta(positionsRejilla, (posicion - lineasPorFila) - 1);
                    boolean esta2 = esta(positionsRejilla, posicion - 1);
                    if (!esta1 || !esta2) {
                        if (esta1) {
                            ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        } else if (esta2) {
                            ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        } else if (filasColumas[1] != 2) {
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        }
                    }
                } else if (!esta(positionsRejilla, (posicion - lineasPorFila) - 1)) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(filasColumas[0] - 2), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                }
            }
        } else if (array[3] == 0) {
            if (filasColumas[1] == 2 && !esta(positionsRejilla, posicion - 1)) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
            }
            Log.i("arrayPosicion0", array[0] + " " + array[1] + " " + array[2] + " " + array[3] + " " + array[4] + " " + posicion);
            boolean esta12 = esta(positionsRejilla, posicion + -1);
            boolean esta22 = esta(positionsRejilla, posicion + -2);
            if (esta12 && esta22) {
                i = 1;
            } else if (esta12) {
                i = 1;
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 2), 0, Integer.valueOf(array[2] - 1), 0, 1));
            } else {
                i = 1;
                if (esta22) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                } else if (filasColumas[1] != 2) {
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 2), 0, Integer.valueOf(array[2] - 1), 0, 1));
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                }
            }
        } else {
            i = 1;
            if (array[4] == filasColumas[0] - 1) {
                if (filasColumas[1] == 2 && !esta(positionsRejilla, (posicion - lineasPorFila) - 1)) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(filasColumas[0] - 2), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                }
                boolean esta23 = esta(positionsRejilla, posicion - 2);
                boolean esta13 = esta(positionsRejilla, (posicion - lineasPorFila) - 1);
                if (!esta13 || !esta23) {
                    if (esta13) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 2), 0, Integer.valueOf(array[2] - 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                    } else if (esta23) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(filasColumas[0] - 2), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    } else if (filasColumas[1] != 2) {
                        ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(filasColumas[0] - 2), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 2), 0, Integer.valueOf(array[2] - 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                    }
                }
            } else {
                if (filasColumas[1] == 2) {
                    boolean esta14 = esta(positionsRejilla, (posicion - lineasPorFila) - 1);
                    boolean esta24 = esta(positionsRejilla, posicion - 1);
                    if (!esta14 || !esta24) {
                        if (esta14) {
                            ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        } else if (esta24) {
                            ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        } else {
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        }
                    }
                }
                Log.i("arrayPosicion1", array[0] + " " + array[1] + " " + array[2] + " " + array[3] + " " + array[4] + " " + posicion);
                boolean esta15 = esta(positionsRejilla, (posicion - lineasPorFila) - 1);
                boolean esta25 = esta(positionsRejilla, posicion + -1);
                boolean esta3 = esta(positionsRejilla, posicion + -2);
                if (!(esta15 & esta25) || !esta3) {
                    if (esta15 && esta25) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 2), 0, Integer.valueOf(array[2] - 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                    } else if (esta25 && esta3) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    } else if (esta3 && esta15) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    } else if (filasColumas[1] != 2) {
                        if (esta15) {
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 2), 0, Integer.valueOf(array[2] - 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        } else if (esta25) {
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 2), 0, Integer.valueOf(array[2] - 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                        } else if (esta3) {
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) - 1), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        }
                    }
                }
            }
        }
        if (array[2] == filasColumas[i] - i) {
            if (array[3] == 0) {
                if (!esta(positionsRejilla, posicion + 1)) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 1, 1, Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
                }
            } else if (array[4] != filasColumas[0] - i) {
                boolean esta26 = esta(positionsRejilla, posicion + 1);
                boolean esta16 = esta(positionsRejilla, (posicion - lineasPorFila) + i);
                if (esta16 && esta26) {
                    return;
                }
                if (esta16) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
                } else if (esta26) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) + i), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
                } else if (filasColumas[i] != 2) {
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) + i), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
                }
            } else if (!esta(positionsRejilla, (posicion - lineasPorFila) + i)) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) + i), 1, Integer.valueOf(filasColumas[0] - 2), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
            }
        } else if (array[3] == 0) {
            boolean esta17 = esta(positionsRejilla, posicion + 1);
            boolean esta27 = esta(positionsRejilla, posicion + 2);
            if (esta17 && esta27) {
                return;
            }
            if (esta17) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 2), 0, Integer.valueOf(array[2] + i), 0, 1));
            } else if (esta27) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 1, 1, Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
            } else if (filasColumas[i] != 2) {
                ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 1, 1, Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
                ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 2), 0, Integer.valueOf(array[2] + i), 0, 1));
            }
        } else if (array[4] == filasColumas[0] - i) {
            boolean esta18 = esta(positionsRejilla, (posicion - lineasPorFila) + i);
            boolean esta28 = esta(positionsRejilla, posicion + 2);
            if (esta18 && esta28) {
                return;
            }
            if (esta18) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 2), 0, Integer.valueOf(array[2] + i), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
            } else if (esta28) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) + i), 1, Integer.valueOf(filasColumas[0] - 2), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
            } else if (filasColumas[i] != 2) {
                ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 2), 0, Integer.valueOf(array[2] + i), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) + i), 1, Integer.valueOf(filasColumas[0] - 2), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
            }
        } else {
            boolean esta19 = esta(positionsRejilla, (posicion - lineasPorFila) + i);
            boolean esta29 = esta(positionsRejilla, posicion + 1);
            boolean esta32 = esta(positionsRejilla, posicion + 2);
            if ((esta19 & esta29) && esta32) {
                return;
            }
            if (esta19 && esta29) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 2), 0, Integer.valueOf(array[2] + i), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
            } else if (esta29 && esta32) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) + i), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
            } else if (esta32 && esta19) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
            } else if (filasColumas[i] == 2) {
            } else {
                if (esta19) {
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 2), 0, Integer.valueOf(array[2] + i), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
                } else if (esta29) {
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) + i), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 2), 0, Integer.valueOf(array[2] + i), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                } else if (esta32) {
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 1, Integer.valueOf(array[4]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion - lineasPorFila) + i), 1, Integer.valueOf(array[3]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + i)));
                }
            }
        }
    }

    public void posicionesCompltarCuadroAB(int[] array, int[] filasColumas, IA ia, LinkedList<int[]> positionsRejilla, int posicion) {
        char c;
        int lineasPorFila = this.rejilla.getLineasPorFila();
        if (array[2] == 1) {
            c = 1;
            if (array[3] == 0) {
                if (!esta(positionsRejilla, posicion + 1)) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 0, 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                }
            } else if (array[4] != filasColumas[1]) {
                boolean esta1 = esta(positionsRejilla, posicion - 1);
                boolean esta2 = esta(positionsRejilla, posicion + 1);
                if (!esta1 || !esta2) {
                    if (esta1) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 0, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    } else if (esta2) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 0, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    } else if (filasColumas[1] != 2) {
                        ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 0, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 0, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    }
                }
            } else if (!esta(positionsRejilla, posicion - 1)) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 0, Integer.valueOf(filasColumas[1] - 1), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
            }
        } else if (array[3] == 0) {
            boolean esta22 = esta(positionsRejilla, posicion - lineasPorFila);
            boolean esta12 = esta(positionsRejilla, posicion + 1);
            if (esta12 && esta22) {
                c = 1;
            } else if (esta12) {
                c = 1;
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - lineasPorFila), 1, Integer.valueOf(array[2] - 1), 0, 1));
            } else {
                c = 1;
                if (esta22) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 0, 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                } else if (filasColumas[1] != 2) {
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 0, 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - lineasPorFila), 1, Integer.valueOf(array[2] - 1), 0, 1));
                }
            }
        } else {
            c = 1;
            if (array[4] == filasColumas[1]) {
                boolean esta13 = esta(positionsRejilla, posicion - 1);
                boolean esta23 = esta(positionsRejilla, posicion - lineasPorFila);
                if (!esta13 || !esta23) {
                    if (esta13) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - lineasPorFila), 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                    } else if (esta23) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 0, Integer.valueOf(filasColumas[1] - 1), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    } else if (filasColumas[1] != 2) {
                        ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - lineasPorFila), 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                        ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 0, Integer.valueOf(filasColumas[1] - 1), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    }
                }
            } else {
                boolean esta14 = esta(positionsRejilla, posicion - 1);
                boolean esta3 = esta(positionsRejilla, posicion - lineasPorFila);
                boolean esta24 = esta(positionsRejilla, posicion + 1);
                if (!(esta14 & esta24) || !esta3) {
                    if (esta14 && esta24) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - lineasPorFila), 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                    } else if (esta24 && esta3) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 0, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    } else if (esta14 && esta3) {
                        ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 0, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                    } else if (filasColumas[1] != 2) {
                        if (esta14) {
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - lineasPorFila), 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 0, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        } else if (esta24) {
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 0, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - lineasPorFila), 1, Integer.valueOf(array[2] - 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                        } else if (esta3) {
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + 1), 0, Integer.valueOf(array[4]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                            ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion - 1), 0, Integer.valueOf(array[3]), Integer.valueOf(array[2] - 1), Integer.valueOf(array[2])));
                        }
                    }
                }
            }
        }
        if (array[2] == filasColumas[0] - 2) {
            if (array[3] == 0) {
                if (!esta(positionsRejilla, posicion + lineasPorFila + 1)) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila + 1), 0, 1, Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
                }
            } else if (array[4] != filasColumas[c]) {
                boolean esta15 = esta(positionsRejilla, (posicion + lineasPorFila) - 1);
                boolean esta25 = esta(positionsRejilla, posicion + lineasPorFila + 1);
                if (esta15 && esta25) {
                    return;
                }
                if (esta15) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila + 1), 0, Integer.valueOf(array[4]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
                } else if (esta25) {
                    ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion + lineasPorFila) - 1), 0, Integer.valueOf(array[3]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
                } else if (filasColumas[c] != 2) {
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila + 1), 0, Integer.valueOf(array[4]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion + lineasPorFila) - 1), 0, Integer.valueOf(array[3]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
                }
            } else if (!esta(positionsRejilla, (posicion + lineasPorFila) - 1)) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion + lineasPorFila) - 1), 0, Integer.valueOf(filasColumas[c] - 1), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
            }
        } else if (array[3] == 0) {
            boolean esta16 = esta(positionsRejilla, posicion + lineasPorFila + 1);
            boolean esta26 = esta(positionsRejilla, posicion + lineasPorFila);
            if (esta16 && esta26) {
                return;
            }
            if (esta16) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila), 1, Integer.valueOf(array[2] + 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
            } else if (esta26) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila + 1), 0, 1, Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
            } else if (filasColumas[c] != 2) {
                ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila), 1, Integer.valueOf(array[2] + 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila + 1), 0, 1, Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
            }
        } else if (array[4] == filasColumas[c]) {
            boolean esta17 = esta(positionsRejilla, (posicion + lineasPorFila) - 1);
            boolean esta27 = esta(positionsRejilla, posicion + lineasPorFila);
            if (esta17 && esta27) {
                return;
            }
            if (esta17) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila), 1, Integer.valueOf(array[2] + 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
            } else if (esta27) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion + lineasPorFila) - 1), 0, Integer.valueOf(filasColumas[c] - 1), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
            } else if (filasColumas[c] != 2) {
                ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila), 1, Integer.valueOf(array[2] + 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion + lineasPorFila) - 1), 0, Integer.valueOf(filasColumas[c] - 1), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
            }
        } else {
            boolean esta18 = esta(positionsRejilla, (posicion + lineasPorFila) - 1);
            boolean esta28 = esta(positionsRejilla, posicion + lineasPorFila + 1);
            boolean esta32 = esta(positionsRejilla, posicion + lineasPorFila);
            if (esta18 && esta28 && esta32) {
                return;
            }
            if (esta18 && esta28) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila), 1, Integer.valueOf(array[2] + 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
            } else if (esta32 && esta28) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf((posicion + lineasPorFila) - 1), 0, Integer.valueOf(array[3]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
            } else if (esta32 && esta18) {
                ia.llenarArrayPoscionesCompletarCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila + 1), 0, Integer.valueOf(array[4]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
            } else if (filasColumas[c] == 2) {
            } else {
                if (esta18) {
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila), 1, Integer.valueOf(array[2] + 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila + 1), 0, Integer.valueOf(array[4]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
                } else if (esta28) {
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion + lineasPorFila) - 1), 0, Integer.valueOf(array[3]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila), 1, Integer.valueOf(array[2] + 1), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                } else if (esta32) {
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf(posicion + lineasPorFila + 1), 0, Integer.valueOf(array[4]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
                    ia.llenarArrayPoscionesSemiCuadro(new Quadrupla<>(Integer.valueOf((posicion + lineasPorFila) - 1), 0, Integer.valueOf(array[3]), Integer.valueOf(array[2]), Integer.valueOf(array[2] + 1)));
                }
            }
        }
    }

    public boolean obtienePunto(Jugador jugador, int[] filasColumnas, int posicion, LinkedList<int[]> positionsRejilla) {
        boolean obtuvoPunto = false;
        int[] arrayPosicion = this.positions.getLast();
        if (arrayPosicion[1] == 0) {
            if (obtienePuntoPorIzquierda(arrayPosicion, filasColumnas, positionsRejilla, posicion)) {
                obtuvoPunto = true;
                jugador.aumentarPuntaje();
                this.positions.getLast()[5] = 1;
            }
            if (!obtienePuntoPorDerercha(arrayPosicion, filasColumnas, positionsRejilla, posicion)) {
                return obtuvoPunto;
            }
            jugador.aumentarPuntaje();
            this.positions.getLast()[7] = 3;
            return true;
        }
        if (obtienePuntoPorArriba(arrayPosicion, filasColumnas, positionsRejilla, posicion)) {
            obtuvoPunto = true;
            jugador.aumentarPuntaje();
            this.positions.getLast()[6] = 2;
        }
        if (!obtienePuntoPorAbajo(arrayPosicion, filasColumnas, positionsRejilla, posicion)) {
            return obtuvoPunto;
        }
        jugador.aumentarPuntaje();
        this.positions.getLast()[8] = 4;
        return true;
    }

    public void llenarArraysParaLaDificulatasdIA(int[] arrayPosiciones, IA ia, LinkedList<int[]> positionsRejilla, int posicion) {
        if (arrayPosiciones[1] == 0) {
            posicionesParaCompletarZD(arrayPosiciones, this.rejilla.obtenerFilasColumnas(), ia, positionsRejilla, posicion);
        } else {
            posicionesCompltarCuadroAB(arrayPosiciones, this.rejilla.obtenerFilasColumnas(), ia, positionsRejilla, posicion);
        }
    }

    public static int calcularPosicion(int tipoLinea, int linea, int endLine, Rejilla rejilla2) {
        int lineasPorFila = (rejilla2.obtenerFilasColumnas()[1] * 2) - 1;
        int posicion = 0;
        if (tipoLinea == 0) {
            for (int i = 0; i <= rejilla2.obtenerFilasColumnas()[0] - 1; i++) {
                if (endLine == i) {
                    for (int j = 0; j <= rejilla2.obtenerFilasColumnas()[1] - 1; j++) {
                        if (linea == j) {
                            posicion = (((i - 1) * lineasPorFila) + (j * 2)) - 1;
                        }
                    }
                }
            }
        } else {
            for (int i2 = 0; i2 <= rejilla2.obtenerFilasColumnas()[0] - 2; i2++) {
                if (linea == i2) {
                    for (int j2 = 0; j2 <= rejilla2.obtenerFilasColumnas()[1]; j2++) {
                        if (endLine == j2) {
                            posicion = (((i2 - 1) * lineasPorFila) + ((j2 * 2) - 1)) - 1;
                        }
                    }
                }
            }
        }
        return posicion;
    }

    public static boolean esta(LinkedList<int[]> posicionesRejilla, int posicion) {
        return posicionesRejilla.get(posicion)[0] == 1;
    }
}