#!bin/bash


amarillo="\e[1;33m"
rojo="\e[1;31m"
cyanc="\e[1;36m"
reset="\e[0m"

echo -e "$cyanc Introduce el nombre de un archivo que quieras buscar: $reset"
read arch
echo -e "$cyanc Introduce la ruta en la que quieres buscar ese arhchivo: $reset" 
read rut

if ls $rut | egrep -ow $arch
then
	echo -e "$rojo Sí existe el archivo en la ruta especificada $reset"
else
	echo -e "$amarillo No hay ningun archivo con ese nombre $reset"
fi

