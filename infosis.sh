#!bin/bash

verde="\e[32m"
azul="\e[34m"
reset="\e[0m"
mtotal=`cat /proc/meminfo | egrep "MemTotal" | egrep "[0-9].*" -o`
libre=`cat /proc/meminfo | egrep "MemFree" | egrep "[1-9].*" -o`
swap=`cat /proc/meminfo | egrep "SwapTotal" | egrep "[0-9].*" -o`
so=`cat /proc/sys/kernel/ostype`
ds=`cat /proc/version_signature`
kernel=`cat /proc/version | awk {'print $3'}`
procesador=`cat /proc/cpuinfo | egrep "model name"`
vel=`cat /proc/cpuinfo | egrep "cpu MHz"`
cache=`cat /proc/cpuinfo | egrep "cache size"`

echo -e "$azul --INFORMACION DEL SISTEMA-- $reset"

echo "Informacion sobre la memoria: "
echo -e "Memoria total: $verde $mtotal $reset"
echo -e "Memoria libre: $verde $libre $reset"
echo -e "Memoria swap: $verde $swap $reset"

echo -e "El tipo de sistema operativo es: $verde $so $reset" 

echo -e "La distribucion del sistema es: $verde $ds $reset"

echo -e "Kernel: $verde $kernel $reset"

echo -e "El modelo de procesador es: "
echo -e "$verde $procesador $reset"

echo -e "La velocidad del procesador es: "
echo -e "$verde $vel $reset"

echo -e "El tamaño de la memoria caché es: "
echo -e "$verde $cache $reset"