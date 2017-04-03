#!/bin/bash

#	CHECA SI EL USUARIO Y LA CONTRASEÑA QUE SE PASAN COMO PARÁMETROS EXISTAN.
#	EN CUALQUIER CASO DE ERROR DEVUELVE UN EXIT STATUS DE 1

if [ $# -ne 2 ]; then
	echo "Too few arguments."
	exit 1
fi

usuario=$1
CONTRASENA=$2

#	id -u devuelve el id de grupo de un usuario si es que este existe. 
#	Su salida se redirige a /dev/null para desaparecerla.
id -u $usuario > /dev/null;

if [ $? -ne 0 ]; then
	exit 1;
else

	export CONTRASENA
    FPASS=`grep -w "$usuario" /etc/shadow | cut -d: -f2`
    export APASS=`echo $FPASS | cut -d'$' -f2`
    export BPASS=`echo $FPASS | cut -d'$' -f3`
    LPASS=$(perl -le 'print crypt("$ENV{CONTRASENA}","\$$ENV{APASS}\$$ENV{BPASS}\$")')

    if [ "$LPASS" == "$FPASS" ]; then
    	exit 0
    else
    	exit 1
    fi

fi
