 #!/bin/bash
#creado  por el prebecario Dante Aleman

trap ctrl_c INT;
trap 'echo -e "\033[00;30m \n\nGracias por jugar \n"; exit 127' SIGINT

ROJO='\033[01;31m'
NEGRO='\033[00;30m'
NEGRITAS='\033[01;30m'
DEFAULT_COL='\033[01;30m'
NC="\E[m"
RED="\E[31m\E[1m"
AMARRILLO="\E[33m\E[1m"
VERDE="\E[37m\E[32m\E[1m"
AZUL="\E[34;44m"


arreglo=( "" "" "" "" "" "" "" "" "")

mensaje_triunfo () {
    echo -e "${VERDE}¡Felicidades $USUARIO_NO, tu Ganas!${NC}\n"
    break ;
}

reglas_para_ganar () {
      if [ $VALOR_CELDA == "${arreglo[0]}" ] && [ $VALOR_CELDA == "${arreglo[1]}" ] && [ $VALOR_CELDA == "${arreglo[2]}" ] ; then
        mensaje_triunfo
      elif [ $VALOR_CELDA == "${arreglo[3]}" ] && [ $VALOR_CELDA == "${arreglo[4]}" ] && [ $VALOR_CELDA == "${arreglo[5]}" ] ; then
        mensaje_triunfo
      elif [ $VALOR_CELDA == "${arreglo[6]}" ] && [ $VALOR_CELDA == "${arreglo[7]}" ] && [ $VALOR_CELDA == "${arreglo[8]}" ] ; then
        mensaje_triunfo
      elif [ $VALOR_CELDA == "${arreglo[0]}" ] && [ $VALOR_CELDA == "${arreglo[3]}" ] && [ $VALOR_CELDA == "${arreglo[6]}" ] ; then
        mensaje_triunfo;
      elif [ $VALOR_CELDA == "${arreglo[1]}" ] && [ $VALOR_CELDA == "${arreglo[4]}" ] && [ $VALOR_CELDA == "${arreglo[7]}" ] ; then
        mensaje_triunfo
      elif [ $VALOR_CELDA == "${arreglo[2]}" ] && [ $VALOR_CELDA == "${arreglo[5]}" ] && [ $VALOR_CELDA == "${arreglo[8]}" ] ; then
        mensaje_triunfo
      elif [ $VALOR_CELDA == "${arreglo[0]}" ] && [ $VALOR_CELDA == "${arreglo[4]}" ] && [ $VALOR_CELDA == "${arreglo[8]}" ] ; then
        mensaje_triunfo
      elif [ $VALOR_CELDA == "${arreglo[2]}" ] && [ $VALOR_CELDA == "${arreglo[4]}" ] && [ $VALOR_CELDA == "${arreglo[6]}" ] ; then
        mensaje_triunfo
      fi
}

empatar ()  {
   for k in `seq 0 $( expr ${#arreglo[@]} - 1) `
    do
     if [ ! -z ${arreglo[$k]} ] ; then
      tic_arreglo[$k]=$k
       if [ "9"  -eq ${#tic_arreglo[@]} ] ; then
        echo -e "${VERDE}\nEs un empate${NC}\n"
        exit
       fi
     fi
    done
  }

dibuja_tablero () {
   clear
   echo -e "${NC}"
   echo -e "\t ---GATO PREBE-SHELL---"
   echo -e "\t*************************"
   echo -e "\t*\t ${arreglo[0]:-0} | ${arreglo[1]:-1} | ${arreglo[2]:-2}\t *"
   echo -e "\t*\t____________\t *"
   echo -e "\t*\t ${arreglo[3]:-3} | ${arreglo[4]:-4} | ${arreglo[5]:-5}\t *"
   echo -e "\t*\t____________\t *"
   echo -e "\t*\t ${arreglo[6]:-6} | ${arreglo[7]:-7} | ${arreglo[8]:-8}\t *"
   echo -e "\t*************************\n"
 }

celda_vacia () {
   echo -e -n "${DEFAULT_COL}"
   read -e -p "$MENSAJE" col
   case "$col" in
    [0-8]) if [  -z ${arreglo[$col]}   ]; then
    CELL_IS=empty
   else
    CELL_IS=notempty
   fi;;
     *)  DEFAULT_COL=$ROJO
     MENSAJE="$USUARIO_NO Escribe un número entre 0 a 8 : "
     celda_vacia;;
   esac
   echo -e -n "${NEGRO}"
}


elegir () {
   celda_vacia
   if [ "$CELL_IS" == "empty" ]; then
    arreglo[$col]="$VALOR_CELDA"
   else
    DEFAULT_COL=${ROJO}
    MENSAJE="La caja no puede estar vacía, Re-Enter $USUARIO_NO : "
    elegir
   fi
}

asigna_nombres () {
   if [ -z $USUARIO1 ]; then
    read -e -p "Introduce el nombre del jugador 1 : " USUARIO1
   fi

   if [ -z $USUARIO2 ]; then
    read -e -p "Introduce el nombre del jugador 2 : " USUARIO2
   fi

   if [ -z $USUARIO1 ] ; then
     echo -e "El nombre del jugandor no puede estar vacío"
     asigna_nombres
   elif [ -z $USUARIO2 ]; then
     echo -e "El nombre del jugandor no puede estar vacío"
     asigna_nombres
   fi
}

# Programa Principal
dibuja_tablero
echo -e "${AMARRILLO}Bienvenido al juego ${RED}GATO${RED}${AMARRILLO} para la prebe_shell${AMARRILLO}"
echo -e "${AMARRILLO}La reglas son introducir un número en la caja entre 0 a 8"
read -n 1 -p "Para empezar a jugar presiona la letra 'y' : " y
echo -e "\n"

    if  [ "$y" == "Y" ]  ||  [ "$y" == "y" ]; then
     clear
     echo -e "${NEGRITAS}"
     asigna_nombres
    else
     echo -e "\nHasta luego."
     exit
    fi

 dibuja_tablero
  while :
   do
    ((i++))
    valor=`expr $i % 2`
    if  [ "$valor" == "0" ]; then
     USUARIO_NO=$USUARIO1
     MENSAJE="$USUARIO_NO Introduce tu elección : "
     VALOR_CELDA="${VERDE}X${NC}"
     elegir
    else
     USUARIO_NO=$USUARIO2
     MENSAJE="$USUARIO_NO Introduce tu elección : "
     VALOR_CELDA="${RED}O${NC}"
     elegir
   fi

    dibuja_tablero
    reglas_para_ganar
    empatar
done
