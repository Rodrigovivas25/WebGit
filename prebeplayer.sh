#!/bin/bash
 
#Muestra el menu general
function _menuPrincipal()
{
    clear
    #cd /home/dan15/Descargas/musica
    echo -e "\e[1;33mBienvenido al PrebePlayer!!!\e[0m"
    echo -e -n "\e[1;32mEl directorio actual es:\e[0m"
    echo                             -e "\e[1;34m $PWD !!!\e[0m"
    echo -e "\e[1;36mOpciones:\e[0m"
    echo -e -n "\e[1;33m1)\e[0m"
    echo -e "\e[1;31m Mostrar Musica\e[0m"
    echo -e -n "\e[1;33m2)\e[0m"
    echo -e "\e[1;31m Informacion de Uso\e[0m"
    echo -e -n "\e[1;33m3)\e[0m"
    echo -e "\e[1;31m Salir\e[0m"
    echo
    echo -e -n "\e[1;32mIndica una opcion:\e[0m"
}
function _seleccionarCarpeta(){
    clear
                        
                        #cd /home/dan15/Descargas/musica
                        echo -e "\e[1;33mSelecciona la carpeta !!\e[0m"
                        echo -e -n "\e[1;32mEl directorio actual es:\e[0m"
                        echo                             -e "\e[1;34m $PWD !!!\e[0m"
                        OPCIONES="Mostrar/Elementos Seleccionar Regresar Ingresar/Ruta  Salir"
                        #Cambiamos el prompt para que sea más descriptivo
                        PS3="Elija una opción:"
                        #Leemos dos números
                        #read -p "Numero 1: " n1
                        #read -p"Numero 2: " n2
                        select opt in $OPCIONES
                        do

                           clear
                           echo -e -n "\e[1;32mEl directorio actual es:\e[0m"
                           echo                             -e "\e[1;34m $PWD !!!\e[0m"
                           echo
                           if [[ $opt = "Mostrar/Elementos" ]]
                           then
                              echo
                              echo -e "\e[1;36mTus elementos son:\e[0m"
                              ls
                              echo
                              _Opciones2
                              echo
                            elif [[ $opt = "Seleccionar" ]]
                            then
                              echo
                              echo -e "\e[1;36mTus Elementos son:\e[0m"
                              ls
                              echo
                              echo -e "\e[1;36mEscribe el nombre de la carpeta a Ingresar\e[0m"
                              read carpeta
                              cd $carpeta
                              ls
                              echo
                              _Opciones2
                              echo

                           elif [[ $opt = "Regresar" ]]
                           then
                              echo
                              cd ..
                              ls
                              echo
                              _Opciones2
                              echo

                           elif [[ $opt = "Ingresar/Ruta" ]]
                           then
                              echo
                              echo -e "\e[1;36mIngresa la ruta:\e[0m"
                              read ruta
                              cd $ruta
                              echo
                              _Opciones2

                           elif [[ $opt = "Salir" ]]
                           then
                              echo -e "\e[1;36mPresiona ENTER\e[0m"
                            break
                            else
                              echo
                              echo opción errónea
                            fi  
                        done
}

function _mostrarMusica()
{
    
    clear
    echo -e -n "\e[1;33m1)\e[0m"
    echo -e "\e[1;31m Reproducir Especificamente\e[0m"
    echo -e -n "\e[1;33m2)\e[0m"
    echo -e "\e[1;31m Reproducir Aleatoriamente\e[0m"
    echo -e -n "\e[1;33m3)\e[0m"
    echo -e "\e[1;31m Reproducir por Orden Alfabetico\e[0m"
    echo -e -n "\e[1;33m4)\e[0m"
    echo -e "\e[1;31m Reproducir por Artista\e[0m"
    echo -e -n "\e[1;33m5)\e[0m"
    echo -e "\e[1;31m Seleccionar Carpeta\e[0m"
    echo -e -n "\e[1;33m6)\e[0m"
    echo -e "\e[1;31m Salir\e[0m"
    echo
    echo -e -n "\e[1;32mIndica una opcion:\e[0m"
}
function _Opciones2(){
    echo "OPCIONES:"
    echo -e -n "\e[1;33m1)\e[0m"
    echo -e "\e[1;31m Mostrar/Elementos\e[0m"
    echo -e -n "\e[1;33m2)\e[0m"
    echo -e "\e[1;31m Seleccionar\e[0m" 
    echo -e -n "\e[1;33m3)\e[0m"
    echo -e "\e[1;31m Regresar\e[0m" 
    echo -e -n "\e[1;33m4)\e[0m"
    echo -e "\e[1;31m Ingresar/Ruta\e[0m"  
    echo -e -n "\e[1;33m5)\e[0m"
    echo -e "\e[1;31m Salir\e[0m"
}

function _informaciondeUso()
{
    clear
    echo -e "\e[1;33mInformacion de uso para el PrebePlayer\e[0m"
    echo
    echo -e "\e[1;31m[h]-Ayuda Durante Reproduccion \e[0m"
    echo -e "\e[1;31m[s]-Pausar/Play\e[0m"
    echo -e "\e[1;31m[f]-Saltar a la siguiente canción\e[0m"
    echo -e "\e[1;31m[b]-Retroceder una canción \e[0m"
    echo -e "\e[1;31m[q]-Salir del reproductor \e[0m"
    echo -e "\e[1;31m[+]-Aumentar Volumen \e[0m"
    echo -e "\e[1;31m[-]-Disminuir Volumen \e[0m"
    echo -e "\e[1;31m[l]-Cola de Reproduccion \e[0m"
    echo -e "\e[1;31m[.][:]-Adelantar \e[0m"
    echo -e "\e[1;31m[,][;]-Retroceder \e[0m"
    echo -e "\e[1;31m[r]-Observar Tiempo de Reproduccion \e[0m"
    echo -e "\e[1;31m[c]-Velocidad rapida de reproductor \e[0m"
    echo -e "\e[1;31m[x]-Velocidad lenta de Reproduccion \e[0m"
    echo -e "\e[1;31m[w]-Velocidad normal de Reproduccion \e[0m"
    echo -e "\e[1;33mRegresar al menu: 1 \e[0m"
}


opc=0
until [ $opc -eq 3 ]
do
    case $opc in
        1)
            opc1=0
            until [ $opc1 -eq 6 ]
            do
                case $opc1 in
                    1)
                        #cd /home/dan15/Descargas/musica/Todas
                        echo -e -n "\e[1;32mEl directorio actual es:\e[0m"
                        echo                             -e "\e[1;34m $PWD !!!\e[0m"

                        echo -e "\e[1;36mTu musica es:\e[0m"
                        ls
                        echo -e -n "\e[1;32mEscribe el nombre de la cancion:\e[0m"
                        read  c1
                        echo -e -n "\e[1;32m$c1\e[0m"
                        mpg123 -C $c1.mp3

                        ;;
                    2)
                        #cd /home/dan15/Descargas/musica/Todas
                        echo -e -n "\e[1;32mEl directorio actual es:\e[0m"
                        echo                             -e "\e[1;34m $PWD !!!\e[0m"
                        echo -e "\e[1;36mReproduciendo Aleatoriamente:\e[0m"
                        ls
                        mpg123 -ZC *.mp3
                        ;;
                    3)
                        #cd /home/dan15/Descargas/musica/Todas
                        echo -e -n "\e[1;32mEl directorio actual es:\e[0m"
                        echo                             -e "\e[1;34m $PWD !!!\e[0m"
                        echo -e "\e[1;36mReproduciendo en orden Alfabetico\e[0m"
                        echo -e "\e[1;36mTu musica es:\e[0m"
                        ls
                        echo 
                        mpg123 -vC *.mp3
                        ;;
                    4)
                        cd /home/dan15/Descargas/musica/Artistas
                        echo "Mostrar por Artista"
                        echo -e -n "\e[1;32mEl directorio actual es:\e[0m"
                        echo                             -e "\e[1;34m $PWD !!!\e[0m"
                        echo -e "\e[1;36mEstos son los artistas que tienes:\e[0m"
                        ls
                        echo -e -n "\e[1;32m¿Que artista deseas escuchar?\e[0m"
                        read Ar1
                        cd $Ar1
                        mpg123 -vC *.mp3

                        ;;
                    5)
                        _seleccionarCarpeta

                        ;;
                    *)
                        _mostrarMusica
                        ;;
                esac
                read opc1
            done
            _menuPrincipal
            ;;
        2)
            opc2=0
            until [ $opc2 -eq 1 ]
            do
                case $opc2 in
                    *)
                        _informaciondeUso
                        ;;
                esac
                read opc2
            done
            _menuPrincipal
            ;;
        *)
            _menuPrincipal
            ;;
    esac
    read opc
done