#!bin/bash

echo "Bienvenido a la prebeshell."
echo "Si quieres saber sobre los comandos que puedes utilizar, teclea 'ayuda' (sin comillas): "
read opc

case $opc in
	'ayuda')
		bash $PWD/ayuda.sh
	;;
	'arbol')
		bash $PWD/arbol.sh
	;;
	'infosis')
		bash $PWD/infosis.sh
	;;
	'hora')
		bash $PWD/hora.sh
	;;
	'fecha')
		bash $PWD/fecha.sh
	;;
	'gato')
	
	;;
	'snake')
	
	;;
	'prebeplayer')
		bash $PWD/prebeplayer2.she
	;;

	'creditos')
		bash $PWD/creditos.sh
	;;
	*)
		echo "Comando no encontrado. Intenta de nuevo"
		;;

esac

	