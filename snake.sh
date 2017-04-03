#!/bin/bash

IFS=''
declare -i altura=$(($(tput lines)-5)) ancho=$(($(tput cols)-2))
declare -i cabeza_r cabeza_c cola_r cola_c
declare -i esta_viva
declare -i longitud
declare cuerpo
declare -i direccion cambio_dir
declare -i puntuacion=0

color_borde="\E[34;45m"
color_snake="\E[32;42m"
color_comida="\E[34;44m"
text_color="\E[31;43m"
sin_color="\E[0m"

SIG_UP=35
SIG_RIGHT=36
SIG_DOWN=37
SIG_LEFT=38
SIG_QUIT=39
SIG_DEAD=40

# direcciones del  arreglo:
# 0=arriba, 1=derecha, 2=abajo, 3=lizquierda
mover_r=([0]=-1 [1]=0 [2]=1 [3]=0)
mover_c=([0]=0 [1]=1 [2]=0 [3]=-1)

empezar_juego() {
    clear
    echo -ne "\033[?25l"
    stty -echo
    for ((i=0; i<altura; i++)); do
        for ((j=0; j<ancho; j++)); do
            eval "arr$i[$j]=' '"
        done
    done
}

mover_y_dibujar() {
    echo -ne "\E[${1};${2}H$3"
}

dibujar_tablero() {

    mover_y_dibujar 1 1 "$color_borde+$sin_color"
    for ((i=2; i<=ancho+1; i++)); do
        mover_y_dibujar 1 $i "$color_borde-$sin_color"
    done
    mover_y_dibujar 1 $((ancho + 2)) "$color_borde+$sin_color"
    echo

    for ((i=0; i<altura; i++)); do
        mover_y_dibujar $((i+2)) 1 "$color_borde|$sin_color"
        eval echo -en "\"\${arr$i[*]}\""
        echo -e "$color_borde|$sin_color"
    done

    mover_y_dibujar $((altura+2)) 1 "$color_borde+$sin_color"
    for ((i=2; i<=ancho+1; i++)); do
        mover_y_dibujar $((altura+2)) $i "$color_borde-$sin_color"
    done
    mover_y_dibujar $((altura+2)) $((ancho + 2)) "$color_borde+$sin_color"
    echo
}

crear_snake() {
    esta_viva=0
    longitud=10
    direccion=3
    cambio_dir=-1

    cabeza_r=$((altura/2-2))
    cabeza_c=$((ancho/2))

    cuerpo=''
    for ((i=0; i<longitud-1; i++)); do
        cuerpo="1$cuerpo"
    done

    local p=$((${mover_r[1]} * (longitud-1)))
    local q=$((${mover_c[1]} * (longitud-1)))

    cola_r=$((cabeza_r+p))
    cola_c=$((cabeza_c+q))

    eval "arr$cabeza_r[$cabeza_c]=\"${color_snake}o$sin_color\""

    anterior_r=$cabeza_r
    anterior_c=$cabeza_c

    b=$cuerpo
    while [ -n "$b" ]; do

        local p=${mover_r[$(echo $b | grep -o ^[0-3])]}
        local q=${mover_c[$(echo $b | grep -o ^[0-3])]}
        nuevo_r=$((anterior_r+p))
        nuevo_c=$((anterior_c+q))
        eval "arr$nuevo_r[$nuevo_c]=\"${color_snake}o$sin_color\""
        anterior_r=$nuevo_r
        anterior_c=$nuevo_c
        b=${b#[0-3]}
    done
}

esta_muerta() {
    if [ "$1" -lt 0 ] || [ "$1" -ge "$altura" ] || \
        [ "$2" -lt 0 ] || [ "$2" -ge "$ancho" ]; then
        return 0
    fi
    eval "local pos=\${arr$1[$2]}"
    if [ "$pos" == "${color_snake}o$sin_color" ]; then
        return 0
    fi
    return 1
}

dar_comida() {
    local comida_r=$((RANDOM % altura))
    local comida_c=$((RANDOM % ancho))
    eval "local pos=\${arr$comida_r[$comida_c]}"
    while [ "$pos" != ' ' ]; do
        comida_r=$((RANDOM % altura))
        comida_c=$((RANDOM % ancho))
        eval "pos=\${arr$comida_r[$comida_c]}"
    done
    eval "arr$comida_r[$comida_c]=\"$color_comida@$sin_color\""
}

mover_snake() {
    local nuevocabeza_r=$((cabeza_r + mover_r[direccion]))
    local nuevocabeza_c=$((cabeza_c + mover_c[direccion]))
    eval "local pos=\${arr$nuevocabeza_r[$nuevocabeza_c]}"
    if $(esta_muerta $nuevocabeza_r $nuevocabeza_c); then
        esta_viva=1
        return
    fi
    if [ "$pos" == "$color_comida@$sin_color" ]; then
        longitud+=1
        eval "arr$nuevocabeza_r[$nuevocabeza_c]=\"${color_snake}o$sin_color\""
        cuerpo="$(((direccion+2)%4))$cuerpo"
        cabeza_r=$nuevocabeza_r
        cabeza_c=$nuevocabeza_c
        puntuacion+=1
        dar_comida;
        return
    fi
    cabeza_r=$nuevocabeza_r
    cabeza_c=$nuevocabeza_c
    local d=$(echo $cuerpo | grep -o '[0-3]$')
    cuerpo="$(((direccion+2)%4))${cuerpo%[0-3]}"
    eval "arr$cola_r[$cola_c]=' '"
    eval "arr$cabeza_r[$cabeza_c]=\"${color_snake}o$sin_color\""
    # nueva cola
    local p=${mover_r[(d+2)%4]}
    local q=${mover_c[(d+2)%4]}
    cola_r=$((cola_r+p))
    cola_c=$((cola_c+q))
}
cambiar_direccion() {
    if [ $(((direccion+2)%4)) -ne $1 ]; then
        direccion=$1
    fi
    cambio_dir=-1
}

leer_movimiento() {
    trap "" SIGINT SIGQUIT
    trap "return;" $SIG_DEAD
    while true; do
        read -s -n 1 key
        case "$key" in
            [qQ]) kill -$SIG_QUIT $game_pid
                  return
                  ;;
            [kK]) kill -$SIG_UP $game_pid
                  ;;
            [lL]) kill -$SIG_RIGHT $game_pid
                  ;;
            [jJ]) kill -$SIG_DOWN $game_pid
                  ;;
            [hH]) kill -$SIG_LEFT $game_pid
                  ;;
       esac
    done
}

jugar() {
    trap "cambio_dir=0;" $SIG_UP
    trap "cambio_dir=1;" $SIG_RIGHT
    trap "cambio_dir=2;" $SIG_DOWN
    trap "cambio_dir=3;" $SIG_LEFT
    trap "exit 1;" $SIG_QUIT
    while [ "$esta_viva" -eq 0 ]; do
        echo -e "\n${text_color}      Tu puntuacion: $puntuacion $sin_color"
        if [ "$cambio_dir" -ne -1 ]; then
            cambiar_direccion $cambio_dir
        fi
        mover_snake
        dibujar_tablero
        sleep 0.03
    done

    echo -e "${text_color}Lo siento, haz perdido$sin_color"
    kill -$SIG_DEAD $$
}

limpiar_juego() {
    stty echo
    echo -e "\033[?25h"
}

imprime_intruciones () {
  echo -e "           ######  ######  ####### ######  #######
          #     # #     # #       #     # #
          #     # #     # #       #     # #
          ######  ######  #####   ######  #####
          #       #   #   #       #     # #
          #       #    #  #       #     # #
          #       #     # ####### ######  #######

           #####  #     #    #    #    #  #######
          #     # ##    #   # #   #   #   #
          #       # #   #  #   #  #  #    #
           #####  #  #  # #     # ###     #####
                # #   # # ####### #  #    #
          #     # #    ## #     # #   #   #
           #####  #     # #     # #    #  #######
 "
 echo -e "Movimientos:\n\tIZQUIERDA-[H] ARRIBA-[K] ABAJO-[J] DERECHA-[L] "
 echo -e "Oprime [ENTER] para empezar el juego"
 read a
}

imprime_intruciones
empezar_juego
crear_snake
dar_comida
dibujar_tablero
jugar &
game_pid=$!
leer_movimiento
limpiar_juego
exit 0
