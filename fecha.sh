#!bin/bash

azul="\e[34m"
reset="\e[0m"
echo -n -e "$azul La fecha actual es: $reset"
cat /proc/driver/rtc | egrep -o -m 1 [0-9]{4}+"-"+[0-9]{2}+"-"+[0-9]{2}