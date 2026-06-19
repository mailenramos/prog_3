public class Backtracking{

    private Sala salaFinal;
    private Sala salaInicial;

    public void back(/*estado*/Sala[][]matriz, Sala salaActual, ArrayList<Sala> caminoActual, int costoAcumulado){

        if(esFinal())//tengo movimiento || estoy en la sala final  
            if(esSolucion()){
                if(costoAcumulado<mejorCosto){
                    this.mejorCosto=costoAcumulado;
                    this.mejorCamino.clear();
                    this.mejorCamino.addAll(caminoActual);
                }
            }
        else{

            if(salaActual).puedoIrDerecha(){
                //obtengo siguiente sala
                Sala siguiente = matriz[salaActual.getX()-1][salaActual.getY()];
                //hago cambio
                caminoActual.add(siguiente);
                costoAcumulado += siguiente.getCosto(); 
                //llamo a BackTracking
                back(matriz, siguiente, caminoActual, costoAcumulado);
            }
            if(salaActual.puedoIrIzquierda()){

            }
            if(salaActual.puedoIrAbajo()){

            }
            if(salaActual.puedoIrArriba()){

            }
        }
    }
}