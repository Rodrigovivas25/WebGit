#!/bin/bash

rojoc="\e[1;31m"
cyan="\e[36m"
reset="\e[0m"

echo -e "$rojoc Ingresa una ruta para poder listarla en forma de arbol: $reset"
read ruta


ls $ruta &> /dev/null

if [ $? -eq 2 ]; then
	echo -e "$cyan Ruta no válida $reset"
	exit 2
fi


ls $ruta -R | grep ":$" | sed -e 's/:$//' -e 's/[^-][^\/]*\//--|/g' -e 's/^/ /' -e 's/-/|/'

exit 0