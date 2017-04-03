#!/bin/bash
#Prebeshell

cyanc="\e[1;36m"
reset="\e[0m"

trap 'echo "Ctrl+C está desactivado." > /dev/null' SIGINT
trap 'echo "No puedes salir pulsando Ctrl+Z." > /dev/null' SIGTSTP

clear
echo -n -e "La PREBESHELL debe ejecutarse con permisos de \e[91msuperusuario\e[0m para poder funcionar. \nSi no es superusuario la prebeshell se cerrará."
sleep 5

clear
if [ $(whoami) != "root" ]; then
	echo -e "\e[91mNecesitas ejecutar la prebeshell como superusuario.\e[0m"
	exit 1
fi

usuario=""
password=""
intentos=5

#	Comprueba usuarios

while [ $intentos -ne 0 ]; do
	clear
	echo -n -e "\e[32mNombre de usuario: \e[0m"
	read usuario

	echo -n -e "\e[32mContraseña: \e[0m"
	read -s password

	bash $PWD/LogIn.sh $usuario $password

	if [ $? -eq 0 ]; then
		intentos=3
		break
	else
		intentos=$((intentos-1))
		echo -e "Usuario o contraseña no válidos."
		sleep 2
	fi
done


clear
#prompt="[\e[1;36m"$HOSTNAME"\e[0m]@\e[91m"$usuario"\e[0m [$cyanc"$PWD"$reset] : " 
opc=""
while [ "$opc" != "salir" ]; do
	prompt="[\e[1;36m"$HOSTNAME"\e[0m]@\e[91m"$usuario"\e[0m [$cyanc"$PWD"$reset] : " 
	echo -ne $prompt
	read -e opc

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
		bash $PWD/gato.sh	
	;;
	'snake')
		bash $PWD/snake.sh
	;;
	'prebeplayer')
		bash $PWD/prebeplayer2.she
	;;
	'creditos')
		bash $PWD/creditos.sh
	;;
	'salir')
		echo 
	;;
	*)
		$opc
	;;

	esac

done


	
