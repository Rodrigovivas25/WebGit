#!bin/bash

rojo="\e[1;31m"
reset="\e[0m"

echo -n -e "$rojo La hora actual es: $reset";
cat /proc/driver/rtc | egrep -o -m 1 [0-9]{2}+":"+[0-9]{2}+":"+[0-9]{2}