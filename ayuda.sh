#!bin/bash

amarillo="\e[1;33m"
cyan="\e[36m"
cyanc="\e[1;36m"
reset="\e[0m"


echo -e "$cyan --BIENVENIDO AL MENÚ DE AYUDA--$reset"
echo
echo -e "$cyanc En la prebeshell es posible utilizar los siguientes comandos: $reset"
echo
echo -e "\t$cyan *arbol - Muestra de manera gráfica la jerarquia de un directorio $reset"
echo -e "\t$cyanc *infosis - Muestra toda la informacion de tu sistema $reset"
echo -e "\t$cyan *prebeplayer - Inicia un reproductor mp3$reset"
echo -e "\t$cyanc *fecha - Muestra la fecha actual$reset"
echo -e "\t$cyan *hora - Muestra la hora actual$reset"
echo -e "\t$cyanc *buscar - Busca un archivo dentro de algun directorio en especifico$reset"
echo -e "\t$cyan *gato - Inicializa un juego de gato$reset"
echo -e "\t$cyanc *snake - Inicializa el juego de Snake$reset"
echo -e "\t$cyan *creditos - Muestra los creditos de los programadores de la Shell$reset"
echo -e "\t$cyan *salir - Teclee salir para salir de la prebeshell"
echo 
echo -e "--ESPECIFICACIONES ESPECIALES--"
echo -e "$amarillo **El prebeplayer se meterá a la carpeta donde se encuentra el usuario. $reset"
echo -e -n "$amarillo Para arreglar eso, es necesario seleccionar la opción 5.Seleccionar Carpeta y especificar $reset"
echo -e "$amarillo la ruta donde se encuentra la música. Inmediatamente de poner la ruta selecciónar Salir $reset"
echo -e "$amarillo **No es necesario poner la extensión en el Prebeplayer, el script pone automáticamente el .mp3 $reset"
echo -e "$amarillo **Cada que se pida una ruta, es recomendable iniciar con /home/usario/ $reset"